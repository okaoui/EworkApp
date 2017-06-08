package com.developeinjava.android.ework.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.developeinjava.android.ework.R;
import com.developeinjava.jaxws.ServiceException_Exception;
import com.developeinjava.jaxws.SolutionService;
import com.developeinjava.jaxws.SolutionServiceService;

import java.net.URL;

import javax.xml.namespace.QName;

public class ManageSolutionsActivity extends AppCompatActivity {

    private static final QName SERVICE_NAME = new QName("http://jaxws.developeinjava.com", "SolutionServiceImplService");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_solutions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Start counting off the main UI thread
        GetPendingSolutionsTask tsk1 = new GetPendingSolutionsTask();
        GetDeclinedSolutionsTask tsk2 = new GetDeclinedSolutionsTask();
        GetAcceptedSolutionsTask tsk3 = new GetAcceptedSolutionsTask();
        tsk1.execute();
        tsk2.execute();
        tsk3.execute();
    }

    private class GetPendingSolutionsTask extends AsyncTask<Long, Integer, Integer> {

        GetPendingSolutionsTask() {}

        @Override
        protected Integer doInBackground(Long... params) {
            URL wsdlURL = SolutionServiceService.WSDL_LOCATION;

            SolutionServiceService ss = new SolutionServiceService(wsdlURL, SERVICE_NAME);
            SolutionService port = ss.getSolutionServicePort();

            long pid = 3;
            int total = 0;
            try {
                total = port.getTotalPendingSolutionsForProblem(pid);
                System.out.println("getTotalPendingSolutionsForProblem.result=" + total);

            } catch (ServiceException_Exception e) {
                System.out.println("Expected exception: ServiceException has occurred.");
                System.out.println(e.toString());
            }

            return total;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

    private class GetAcceptedSolutionsTask extends AsyncTask<Long, Integer, Integer> {

        GetAcceptedSolutionsTask() {}

        @Override
        protected Integer doInBackground(Long... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }

    private class GetDeclinedSolutionsTask extends AsyncTask<Long, Integer, Integer> {

        GetDeclinedSolutionsTask() {}

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected Integer doInBackground(Long... params) {
            return null;
        }
    }

}
