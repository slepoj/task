package task.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button goOut;
    private TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

        goOut = (Button) findViewById(R.id.exit);
        information = (TextView) findViewById(R.id.info);
        information.setText("You e-mail is\n"+DBHelper.getEmail()+"\n"+"Have fun :)");

        goOut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                Intent intent = new Intent(this, Screen1Activity.class);
                startActivity(intent);
                break;
        }
    }
}
