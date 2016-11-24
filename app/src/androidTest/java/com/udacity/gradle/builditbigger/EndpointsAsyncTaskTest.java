package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void testVerifyJokeResponse() throws InterruptedException {
        //We're using signal to ensure our unit test waits for the Async response before finishing.
        final CountDownLatch signal = new CountDownLatch(1);

        //Create and fire the async task.
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.execute(new EndpointsAsyncTask.IEndPointListener() {
            @Override
            public void onResponseReceived(String joke) {
                assertNotNull(joke);
                assertTrue(joke.length() > 0);
                assertEquals("Knock Knock. Who's there? Scott. Scott who? Scott-Nothing-To-Do-With-You", joke);
                //Instruct unit test we're done.
                signal.countDown();
            }
        });
        signal.await();
    }
}