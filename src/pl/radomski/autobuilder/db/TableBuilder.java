package pl.radomski.autobuilder.db;

import java.util.ArrayList;
import java.util.List;

/**
 * creates SQL query to create table.
 */
public class TableBuilder {

	/**
	 * The Enum SQLiteDataType.
	 */
	public enum SQLiteDataType {

		/** The integer. */
		INTEGER("INTEGER"),
		/** The real. */
		REAL("REAL"),
		/** The text. */
		TEXT("TEXT"),
		/** The blob. */
		BLOB("BLOB"),
		/** The numeric. */
		NUMERIC("NUMERIC"),
		/** The integer primary key. */
		INTEGER_PRIMARY_KEY("INTEGER PRIMARY KEY");

		/** The string. */
		private String string;

		/**
		 * Instantiates a new sQ lite data type.
		 * 
		 * @param string
		 *            the string
		 */
		SQLiteDataType(String string) {
			this.string = string;
		}

		/**
		 * Gets the sql type.
		 * 
		 * @return the sql type
		 */
		public String getSqlType() {
			return string;
		}
	}

	/** The table name. */
	private String tableName;

	/** columns list. */
	private List<String> columns;

	/**
	 * Instantiates a new table builder.
	 * 
	 * @param tableName
	 *            the table name
	 */
	public TableBuilder(String tableName) {
		this.tableName = tableName;
		columns = new ArrayList<String>();
	}

	/**
	 * Adds the column.
	 * 
	 * @param columnName
	 *            the column name
	 * @param columnType
	 *            the column type
	 * @return the table builder
	 */
	public TableBuilder addColumn(String columnName, SQLiteDataType columnType) {
		columns.add(columnName + " " + columnType.getSqlType());
		return this;
	}

	/**
	 * Adds foreign key.
	 * 
	 * @param keyName
	 *            key name
	 * @param referenceTable
	 *            table name that foreign key will be referencing
	 * @param referenceTableKey
	 *            key name in table that we are referring to
	 * @return self
	 */
	public TableBuilder addForeignKey(String keyName, String referenceTable, String referenceTableKey) {
		columns.add("FOREIGN KEY(" + keyName + ") REFERENCES " + referenceTable + "(" + referenceTableKey + ")");
		return this;
	}

	/**
	 * Sets the unique column.
	 * 
	 * @param keyName
	 *            the key name
	 * @return the table builder
	 */
	public TableBuilder setUniqueColumn(String keyName) {
		columns.add("UNIQUE (" + keyName + ")");
		return this;
	}

	/**
	 * Creates the query.
	 * 
	 * @return the string
	 */
	public String createQuery() {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE " + tableName + " (");
		for (String column : columns) {
			builder.append(column + ", ");
		}
		builder.delete(builder.length() - 2, builder.length());
		builder.append(");");
		return builder.toString();
	}
}
