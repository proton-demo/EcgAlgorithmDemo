package com.proton.algorithm.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.proton.ecg.algorithm.callback.EcgAlgorithmListener;
import com.proton.ecg.algorithm.interfaces.IEcgAlgorithm;
import com.proton.ecg.algorithm.interfaces.impl.EcgCardAlgorithm;
import com.proton.ecg.algorithm.interfaces.impl.EcgPatchAlgorithm;

public class MainActivity extends AppCompatActivity {
    enum DeviceType {
        patch,//心电贴
        card//心电卡
    }

    private static final String TAG = "MainActivity";
    private DeviceType deviceType = DeviceType.card;
    IEcgAlgorithm ecgAlgorithm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAlgorithm();
    }
    private void testAlgorithm() {
        if (deviceType == DeviceType.card) {
            ecgAlgorithm = new EcgCardAlgorithm(new EcgAlgorithmListener() {
                @Override
                public void receiveEcgFilterData(byte[] ecgData) {
                    super.receiveEcgFilterData(ecgData);
                }

                @Override
                public void signalInterference(int signalQualityIndex) {
                    super.signalInterference(signalQualityIndex);
                }

                @Override
                public void receiverHeartRate(int rate) {
                    super.receiverHeartRate(rate);
                }
            });
        } else {
            ecgAlgorithm = new EcgPatchAlgorithm(new EcgAlgorithmListener() {
                @Override
                public void signalInterference(int signalQualityIndex) {
                    super.signalInterference(signalQualityIndex);
                }

                @Override
                public void receiverHeartRate(int rate) {
                    super.receiverHeartRate(rate);
                }

                @Override
                public void receiveEcgFilterData(byte[] ecgData) {
                    super.receiveEcgFilterData(ecgData);
                }
            });
        }
        ecgAlgorithm.processEcgData(new byte[]{});//此处传入蓝牙sdk接收的数据即可
        //重置算法
        ecgAlgorithm.reset();//一般可在蓝牙sdk的onDisconnect回调里面重置
    }
}