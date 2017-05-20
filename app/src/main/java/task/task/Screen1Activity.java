package task.task;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int emailColIndex = c.getColumnIndex("email");
            int flagColIndex = c.getColumnIndex("flag");
            do {
                if (c.getInt(flagColIndex) == 1) {
                    DBHelper.setEmail(c.getString(emailColIndex));
                    Intent intent = new Intent(this, Screen3Activity.class);
                    startActivity(intent);
                    break;
                }
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        }
        c.close();
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

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        String em = email.getText().toString();
        String pass = password.getText().toString();
        boolean error = true;

        switch (v.getId()){
            case R.id.button:
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    int emailColIndex = c.getColumnIndex("email");
                    int passwordColIndex = c.getColumnIndex("password");
                    do {
                        if (em.equals(c.getString(emailColIndex))){
                            if (HexMd5.md5Custom(pass).equals(c.getString(passwordColIndex))){
                                DBHelper.setEmail(em);
                                // подготовим значения для обновления
                                cv.put("flag", 1);
                                // обновляем по email
                                int updCount = db.update("mytable", cv, "email = ?",
                                        new String[] { c.getString(emailColIndex) });
                                Intent intent = new Intent(this, Screen3Activity.class);
                                startActivity(intent);
                                error = false;
                                break;
                            } else {
                                Toast toast =Toast.makeText(Screen1Activity.this, "Password is incorrect", Toast.LENGTH_LONG);
                                toast.show();
                                error = false;
                                break;
                            }
                        }
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else{
                    Toast toast =Toast.makeText(Screen1Activity.this, "BD is null", Toast.LENGTH_LONG);
                    toast.show();
                    error = false;
                }
                if (error){
                    Toast toast =Toast.makeText(Screen1Activity.this, "This email is not registered", Toast.LENGTH_LONG);
                    toast.show();
                    error = true;
                }
                c.close();
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, Screen2Activity.class);
                startActivity(intent2);
                break;
        }
    }
}
