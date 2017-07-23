package com.haz.myapplication;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class databasehelper extends SQLiteOpenHelper {

	// Direktori default
	String DB_PATH = null;

	// Variabel Nama Database
	// Hanya mengganti di bagian ini saja
	private static String DB_NAME = "vb";

	// Variabel SQLiteDatabase
	private SQLiteDatabase myDataBase;

	// Variabel Context Untuk menggabungkan Dengan Sctivity
	private final Context myContext;

	/**
	 * Constructor Untuk Mengakses Asset Pada Project
	 * 
	 * @param context
	 */
	public databasehelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
	}

	/**
	 * Membuat database kosong pada system dan menulis kembali sesuai dengan
	 * database kita yang berada pada Asset
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// Tidak mengerjakan Jika database Sudah Ada
		} else {

			// Memanggil Method dan Database Yang Telah Di Buat Di Direktori
			// Default System
			// Dari Method ini kita Dapat Menulis Database Kosong Dengan
			// Database Kamu Dari Aplikasi

			this.getReadableDatabase();

			try {

				close();
				// openDataBase();
				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Cek jika database Tersedia untuk proses apakah Sudah Ada atau Belum
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// Database Belum Tersedia

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copy Database Dari Asset Ke Database Kosong Yang Telah Dibuat.
	 * */
	private void copyDataBase() throws IOException {

		// Membuka Database Untuk proses Input Stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Alamat untuk Membuat Database Kosong
		String outFileName = DB_PATH + DB_NAME;

		// Membuka Database Kosong Sebagai Output Stream untuk Copy
		OutputStream myOutput = new FileOutputStream(outFileName);

		// Proses transfer byte dari input stream ke output stream
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Tutup Stream
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}
	public void delete() throws IOException {
		SQLiteDatabase checkDB = null;
		String myPath = DB_PATH + DB_NAME;
		checkDB = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	public void openDataBase() throws SQLException {

		// Open Database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}