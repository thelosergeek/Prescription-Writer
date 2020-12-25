package in.thelosergeek.pdfwriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseClass extends SQLiteOpenHelper {
    public DatabaseClass(@Nullable Context context) {
        super(context, "MyDatabase", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table myTable(invoiceNo INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, address STRING, date INTEGER, symptoms STRING, prescription SRTING);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String address, Long date, String symptoms, String prescription) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);
        contentValues.put("address", address);
        contentValues.put("date", date);
        contentValues.put("symptoms",symptoms);
        contentValues.put("prescription",prescription);
        sqLiteDatabase.insert("myTable",null,contentValues);

    }
}
