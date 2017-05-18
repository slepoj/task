package task.task;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.content.Context;
import android.database.Cursor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Screen2Activity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private Button singUp;
    private TextInputLayout textInputLayoutEmailUp;
    private TextInputLayout textInputLayoutPasswordUp;
    private TextInputLayout textInputLayoutRepPasswordUp;
    private EditText emailUp;
    private EditText passwordUp;
    private EditText repPasswordUp;
    DBHelper dbHelper;
    Toast toast;

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

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String email = emailUp.getText().toString();
        String password = passwordUp.getText().toString();
        String reppassword = repPasswordUp.getText().toString();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.singUp:
                if (isEmailValid(emailUp.getText())) {
                    if (password.equals(reppassword)) {
                        Log.d(LOG_TAG, "--- Insert in mytable: ---");
                        cv.put("email",email);
                        cv.put("password",HexMd5.md5Custom(password));
                        cv.put("flag",0);
                        Intent intent = new Intent(this, Screen1Activity.class);
                        startActivity(intent);
                    } else{
                        toast = Toast.makeText(Screen2Activity.this, "not-real email", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    toast = Toast.makeText(Screen2Activity.this, "passwords do not match", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;

            default:
                break;
        }
        dbHelper.close();
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
