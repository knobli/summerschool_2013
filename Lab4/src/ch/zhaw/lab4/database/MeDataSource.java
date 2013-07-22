package ch.zhaw.lab4.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import ch.zhaw.lab4.model.Profil;

public class MeDataSource {
	private SQLiteDatabase db;
	private MeSQLiteHelper dbHelper;
	private String[] allColumns = { MeSQLiteHelper.PROFIL_ID, MeSQLiteHelper.COLUMN_NAME, MeSQLiteHelper.COLUMN_ADDRESS, MeSQLiteHelper.COLUMN_CITY, MeSQLiteHelper.COLUMN_STATE, MeSQLiteHelper.COLUMN_PHONE };
	
	public MeDataSource(Context context){
		dbHelper = new MeSQLiteHelper(context);
	}
	
	public void open() throws SQLException{
		db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public Profil createProfil(Profil profil){
		ContentValues values = new ContentValues();
		values.put(MeSQLiteHelper.COLUMN_NAME, profil.getName());
		values.put(MeSQLiteHelper.COLUMN_ADDRESS, profil.getAddress());
		values.put(MeSQLiteHelper.COLUMN_CITY, profil.getCity());
		values.put(MeSQLiteHelper.COLUMN_STATE, profil.getState());
		values.put(MeSQLiteHelper.COLUMN_PHONE, profil.getPhone());
		Long insertId = db.insert(MeSQLiteHelper.TABLE_PROFILES, null, values);
		Cursor cursor = db.query(MeSQLiteHelper.TABLE_PROFILES,allColumns, MeSQLiteHelper.PROFIL_ID + " = ?", new String[]{insertId.toString()},null, null, null);
		cursor.moveToFirst();
		Profil newProfil = cursorToProfil(cursor);
		cursor.close();
		return newProfil;
	}
	
	public Profil getProfil(){
		Profil profil;
		Long insertId = 1l;
		Cursor cursor = db.query(MeSQLiteHelper.TABLE_PROFILES, allColumns, MeSQLiteHelper.PROFIL_ID + " = ?", new String[]{insertId.toString()}, null, null, null);
		cursor.moveToFirst();
		if (cursor.getCount() > 0){
			profil = cursorToProfil(cursor);
		} else {
			profil = null;
		}
		cursor.close();
		return profil;
	}
	
	public void updateProfil(Profil profil){
		Long insertId = 1l;
		ContentValues values = new ContentValues();
		values.put(MeSQLiteHelper.COLUMN_NAME, profil.getName());
		values.put(MeSQLiteHelper.COLUMN_ADDRESS, profil.getAddress());
		values.put(MeSQLiteHelper.COLUMN_CITY, profil.getCity());
		values.put(MeSQLiteHelper.COLUMN_STATE, profil.getState());
		values.put(MeSQLiteHelper.COLUMN_PHONE, profil.getPhone());
		db.update(MeSQLiteHelper.TABLE_PROFILES, values, MeSQLiteHelper.PROFIL_ID + " = ?", new String[]{insertId.toString()});
	}
	
	public Profil cursorToProfil(Cursor cursor){
		Profil profil = new Profil();
		profil.setProfilId(cursor.getLong(0));
		profil.setName(cursor.getString(1));
		profil.setAddress(cursor.getString(2));
		profil.setCity(cursor.getString(3));
		profil.setState(cursor.getString(4));
		profil.setPhone(cursor.getString(5));
		return profil;
	}
	
}
