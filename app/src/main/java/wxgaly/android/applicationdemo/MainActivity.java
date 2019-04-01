package wxgaly.android.applicationdemo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import wxgaly.android.annotation.BindView;
import wxgaly.android.annotation.OnClick;
import wxgaly.android.annotationapi.api.ActivityViewBinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityViewBinder.bind(this);
        textView.setText("这是annotation processor生成的文字");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityViewBinder.unBind(this);
    }

    @OnClick(R.id.btn)
    public void login() {
        Toast.makeText(MainActivity.this, "这是annotation processor设置的OnClick", Toast.LENGTH_SHORT).show();
    }

}
