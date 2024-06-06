package com.pingpetrus.simpleasynctask;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class TaskViewModel extends ViewModel {
    private MutableLiveData<String> mResult = new MutableLiveData<>();

    public LiveData<String> getResult() {
        return mResult;
    }

    public void startTask() {
        new SimpleAsyncTask(mResult).execute();
    }

    private static class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
        private MutableLiveData<String> mResult;

        SimpleAsyncTask(MutableLiveData<String> result) {
            mResult = result;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Random r = new Random();
            int n = r.nextInt(11);
            int s = n * 200;

            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
        }

        @Override
        protected void onPostExecute(String result) {
            mResult.setValue(result);
        }
    }
}
