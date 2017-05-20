package task.task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andre on 17.05.2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static String email = "";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создаем таблицу с полями
        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "email text,"
                + "password text, "
                + "flag integer"+ ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        DBHelper.email = email;
    }
}
