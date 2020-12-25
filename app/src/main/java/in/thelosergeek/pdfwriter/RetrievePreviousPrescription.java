package in.thelosergeek.pdfwriter;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class RetrievePreviousPrescription extends AppCompatActivity {

    DataTable dataTable;
    DatabaseClass databaseClass;
    SQLiteDatabase sqLiteDatabasel;

    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat timepatternFormat = new SimpleDateFormat("hh:mm a");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_previous_prescription);
        dataTable = findViewById(R.id.data_table);
        databaseClass = new DatabaseClass(this);
        sqLiteDatabasel = databaseClass.getWritableDatabase();
        DataTableHeader dataTableHeader = new DataTableHeader.Builder()
                .item("Invoice No.", 5)
                .item("Patient Name", 5)
                .item("Date", 5)
                .item("Time", 5)
                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        String[] columns = {"invoiceNo", "Name", "address", "date"};
        Cursor cursor = sqLiteDatabasel.query("myTable", columns, null, null, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            DataTableRow row = new DataTableRow.Builder()
                    .value(String.valueOf(cursor.getInt(0)))
                    .value(cursor.getString(1))
                    .value(simpleDateFormat.format(cursor.getLong(3)))
                    .value(timepatternFormat.format(cursor.getLong(3)))
                    .build();
            rows.add(row);
        }
        dataTable.setHeader(dataTableHeader);
        dataTable.setRows(rows);
        dataTable.inflate(this);
    }

}