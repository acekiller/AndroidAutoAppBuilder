package pl.radomski.autobuilder.db;

import pl.radomski.autobuilder.db.TableBuilder.SQLiteDataType;
import pl.radomski.autobuilder.fragment.footer.standard.FooterData;
import pl.radomski.autobuilder.fragment.header.standard.HeaderData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "viewsDB";
	private static final int DATABASE_VERSION = 1;

	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(new TableBuilder(HeaderData.TABLE_NAME)
				.addColumn(HeaderData.COLUMN_NAME_ID, SQLiteDataType.INTEGER_PRIMARY_KEY)
				.addColumn(HeaderData.COLUMN_NAME_TITLE, SQLiteDataType.TEXT).createQuery());

		db.execSQL(new TableBuilder(FooterData.TABLE_NAME)
				.addColumn(FooterData.COLUMN_NAME_ID, SQLiteDataType.INTEGER_PRIMARY_KEY)
				.addColumn(FooterData.COLUMN_NAME_TITLE, SQLiteDataType.TEXT).createQuery());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
