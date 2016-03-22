package com.yneed.yneed;

/**
 * Created by 咸味 on 2016/3/22.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.yneed.yneed.CircleProgressBar;
public class course_yanjiusheng extends Activity{

    private CircleProgressBar mCircleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coures_yanjiusheng);

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initViews() {

        mCircleBar = (CircleProgressBar) findViewById(R.id.circleProgressbar);

        mCircleBar.setProgress(80);

    }



}
