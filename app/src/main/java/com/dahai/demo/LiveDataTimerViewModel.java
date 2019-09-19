package com.dahai.demo;

import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class LiveDataTimerViewModel extends ViewModel {

    private static final int ONE_SECOND = 1_000_0;

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private long mInitialTime;

    private long l = 1L;

    public LiveDataTimerViewModel() {
        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;

                mElapsedTime.postValue(l);
                Log.e("HHH", "run: " + newValue );
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }
}