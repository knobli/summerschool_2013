package ch.zhaw.lab4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MeSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_PROFILES = "profiles";
	public static final String PROFIL_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_STATE = "state";
	public static final String COLUMN_PHONE = "phone";
	
	private static final String DATABASE_NAME = "profiles.db";
	private static final int DATABASE_VERSION = 1;	

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PROFILES + "(" + PROFIL_ID
			+ " integer primary key autoincrement, " 
			+ COLUMN_NAME + " text not null" +
			", " + COLUMN_ADDRESS + " text " +
			", " + COLUMN_CITY + " text " +
			", " + COLUMN_STATE + " text " +
			", " + COLUMN_PHONE + " text " +
			");";

	public MeSQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MeSQLiteHelper.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
		onCreate(db);
	}

}
