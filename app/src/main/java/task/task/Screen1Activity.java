package task.task;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button in;
    private Button up;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayout6);
        email = (EditText) textInputLayoutEmail.findViewById(R.id.email);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayout5);
        password = (EditText) textInputLayoutPassword.findViewById(R.id.password);
        in = (Button) findViewById(R.id.button);
        up = (Button) findViewById(R.id.button2);

        in.setOnClickListener(this);
        up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                //sometext.setText("button 1");
                Toast toast =Toast.makeText(Screen1Activity.this, "11", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(this, Screen3Activity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                //sometext.setText("button 2");
                Intent intent2 = new Intent(this, Screen2Activity.class);
                startActivity(intent2);
                break;

        }
    }
}
