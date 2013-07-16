package code.three;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import code.three.test1.Test1;
import code.three.test2.Test2;
import code.three.test3.Test3;
import code.three.test4.Test4;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.test_1:
            {
                Intent intent = new Intent(MainActivity.this, Test1.class);
                startActivity(intent);
                break;
            }
            case R.id.test_2:
            {
                Intent intent = new Intent(MainActivity.this, Test2.class);
                startActivity(intent);
                break;
            }
            case R.id.test_3:
            {
                Intent intent = new Intent(MainActivity.this, Test3.class);
                startActivity(intent);
                break;
            }
            case R.id.test_4:
            {
                Intent intent = new Intent(MainActivity.this, Test4.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_1).setOnClickListener(this);
        findViewById(R.id.test_2).setOnClickListener(this);
        findViewById(R.id.test_3).setOnClickListener(this);
        findViewById(R.id.test_4).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}