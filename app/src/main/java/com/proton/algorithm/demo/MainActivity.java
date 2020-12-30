package com.proton.algorithm.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.proton.ecg.algorithm.callback.EcgPatchAlgorithmListener;
import com.proton.ecg.algorithm.interfaces.IEcgAlgorithm;
import com.proton.ecg.algorithm.interfaces.impl.EcgPatchAlgorithm;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAlgorithm();
    }

    private void testAlgorithm() {
        IEcgAlgorithm ecgAlgorithm = new EcgPatchAlgorithm(new EcgPatchAlgorithmListener() {
            @Override
            public void receiveEcgFilterData(byte[] ecgData) {
            }

            @Override
            public void receiverHeartRate(int rate) {
                Log.e(TAG, "心率: " + rate);
            }

            @Override
            public void signalInterference(int signalQualityIndex) {
                //信号相关
                Log.e(TAG, "signalInterference: " + signalQualityIndex);
            }
        });

        ecgAlgorithm.processEcgData(new byte[]{});

        //重置算法
        ecgAlgorithm.reset();
    }
}