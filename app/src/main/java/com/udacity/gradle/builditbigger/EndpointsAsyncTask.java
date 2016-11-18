package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.cloudjokes.myApi.MyApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, EndpointsAsyncTask.IEndPointListener>, Void, String> {

    public interface IEndPointListener{
        void onResponseReceived(String joke);
    }

    private static final String TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private Context mContext;
    private IEndPointListener mListener;

    @Override
    protected String doInBackground(Pair<Context, IEndPointListener>... params) {
        Log.d(TAG, "doInBackground start");
        if(myApiService == null) {  // Only do this once
            Log.d(TAG, "Create api");
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setApplicationName("gcjoker")
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = params[0].first;
        mListener = params[0].second;
        String name = "Kermit";

        try {
            Log.d(TAG, "Execute");
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute: " + result);
        if(mListener != null){
            mListener.onResponseReceived(result);
        }
    }
}