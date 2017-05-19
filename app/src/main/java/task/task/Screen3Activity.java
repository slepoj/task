package task.task;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

        goOut = (Button) findViewById(R.id.exit);
        information = (TextView) findViewById(R.id.info);
        information.setText("You e-mail is\n"+DBHelper.getEmail()+"\n"+"Have fun :)");

        dbHelper = new DBHelper(this);
        goOut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        switch (v.getId()) {
            case R.id.exit:
                // подготовим значения для обновления
                cv.put("flag", 0);
                // обновляем по id
                int updCount = db.update("mytable", cv, "email = ?",
                        new String[] { DBHelper.getEmail() });
                Intent intent = new Intent(this, Screen1Activity.class);
                startActivity(intent);
                break;
        }
    }
}
