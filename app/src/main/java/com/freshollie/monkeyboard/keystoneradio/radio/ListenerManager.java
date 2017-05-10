package com.freshollie.monkeyboard.keystoneradio.radio;

import android.os.Handler;

import java.util.ArrayList;

/**
 * Created by Freshollie on 12/01/2017.
 *
 */

public class ListenerManager {

    /**
     * Manager used to notify given listeners when attributes about the connected radio change
     */

    public interface DataListener {
        void onProgramTextChanged(String programText);
        void onPlayStatusChanged(int playStatus);
        void onSignalQualityChanged(int signalStrength);
        void onProgramDataRateChanged(int dataRate);
        void onRadioVolumeChanged(int volume);
        void onStereoStateChanged(int stereoState);
    }

    public interface ConnectionStateChangeListener {
        void onStart();
        void onFail();
        void onStop();
    }

    private Handler mainHandler;
    private ArrayList<ConnectionStateChangeListener> connectionStateChangeListeners;
    private ArrayList<DataListener> dataListeners;

    public ListenerManager(Handler handler) {
        mainHandler = handler;
        unregisterAll();
    }

    void informConnectionStop() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (ConnectionStateChangeListener listener : new ArrayList<>(connectionStateChangeListeners)) {
                    listener.onStop();
                }
            }
        });
    }

    void informConnectionFail() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (ConnectionStateChangeListener listener: new ArrayList<>(connectionStateChangeListeners)) {
                    listener.onFail();
                }
            }
        });
    }

    void informConnectionStart() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (ConnectionStateChangeListener listener : new ArrayList<>(connectionStateChangeListeners)) {
                    listener.onStart();
                }
            }
        });
    }

    public void registerConnectionStateChangedListener(ConnectionStateChangeListener listener) {
        connectionStateChangeListeners.add(listener);
    }

    public void unregisterConnectionStateChangedListener(ConnectionStateChangeListener listener) {
        connectionStateChangeListeners.remove(listener);
    }

    private void unregisterAll() {
        connectionStateChangeListeners = new ArrayList<>();
        dataListeners = new ArrayList<>();
    }

    public void registerDataListener(DataListener dataListener) {
        dataListeners.add(dataListener);
    }

    public void unregisterDataListener(DataListener dataListener) {
        dataListeners.remove(dataListener);
    }

    void informProgramTextChanged(final String programText){
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onProgramTextChanged(programText);
                }
            }
        });
    }

    void informSignalQualityChanged(final int signalStrength) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onSignalQualityChanged(signalStrength);
                }
            }
        });
    }

    void informPlayStatusChanged(final int playStatus) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onPlayStatusChanged(playStatus);
                }
            }
        });
    }

    void informProgramDataRateChanged(final int dataRate) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onProgramDataRateChanged(dataRate);
                }
            }
        });
    }

    void informVolumeChanged(final int volume) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onRadioVolumeChanged(volume);
                }
            }
        });
    }

    void informStereoStateChanged(final int stereoState) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                for (DataListener dataListener: new ArrayList<>(dataListeners)) {
                    dataListener.onStereoStateChanged(stereoState);
                }
            }
        });
    }
}
