package COM.TQC.GDD02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnFocusChangeListener;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    private int mCounter = 0;
    private TextView textView2;
    private Button button;
    private Button button2;
    private String PREFS_NAME = "MyPrefsFile";
    private EditText editText;
    private String mName; //姓名的變數
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // (1)啟動時執行多執行緒
        //  ???
        functionThreadStart();

        //(3)按下「取得COUNTER的值」時，會讀取多執行緒中的Counter變數，並顯示在「textView2」上。
        textView2 = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText(String.valueOf(mCounter));
            }
        });
        // ???

        // (4)按下「button2儲存」時，會透過Preferences儲存姓名
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ???
                sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
                sharedPreferences.edit().putString("name", editText.getText().toString()).commit();
            }
        });


        // (5)啟動時會讀取Preferences所儲存的姓名，並顯示在「editText姓名」上。
        editText = (EditText) findViewById(R.id.editText);
        // ???
        editText.setText(getSharedPreferences("name", MODE_PRIVATE).getString("name", ""));
    }

    // 多執行緒的處理函數，會在背景的情況下，做mCounter++
    //(2) 該執行緒會透過無限循環的在背景執行，並透過Thread.sleep();每秒鐘把Counter++;累加。　
    private void functionThreadStart() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // ???
                        Thread.sleep(1000);
                        mCounter++;
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}
