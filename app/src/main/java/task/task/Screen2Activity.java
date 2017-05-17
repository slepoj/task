package task.task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Screen2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button singUp;
    private TextInputLayout textInputLayoutEmailUp;
    private TextInputLayout textInputLayoutPasswordUp;
    private TextInputLayout textInputLayoutRepPasswordUp;
    private EditText emailUp;
    private EditText passwordUp;
    private EditText repPasswordUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        textInputLayoutEmailUp = (TextInputLayout) findViewById(R.id.textInputLayout3);
        emailUp = (EditText) textInputLayoutEmailUp.findViewById(R.id.emailUp);
        textInputLayoutPasswordUp = (TextInputLayout) findViewById(R.id.textInputLayout);
        passwordUp = (EditText) textInputLayoutPasswordUp.findViewById(R.id.passwordUp);
        textInputLayoutRepPasswordUp = (TextInputLayout) findViewById(R.id.textInputLayout2);
        repPasswordUp = (EditText) textInputLayoutRepPasswordUp.findViewById(R.id.repPasswordUp);
        singUp = (Button) findViewById(R.id.singUp);

        singUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.singUp:
                //sometext.setText("button 1");
                Toast toast =Toast.makeText(Screen2Activity.this, "11", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(this, Screen1Activity.class);
                startActivity(intent);
                break;
            default: break;
        }
    }
}
