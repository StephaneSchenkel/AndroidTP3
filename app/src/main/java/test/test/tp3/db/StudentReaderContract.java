package test.test.tp3.db;

import android.provider.BaseColumns;

public final class StudentReaderContract {

    private StudentReaderContract() {}

    /* Inner class that defines the table contents */
    public static class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_1 = "lastname";
        public static final String COLUMN_2 = "firstname";
        public static final String COLUMN_3 = "sexe";
        public static final String COLUMN_4 = "email";
        public static final String COLUMN_5 = "birthday";
        public static final String COLUMN_6 = "groupe";
        public static final String COLUMN_7 = "redoublant";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
                    StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    StudentEntry.COLUMN_1 + " TEXT," +
                    StudentEntry.COLUMN_2 + " TEXT," +
                    StudentEntry.COLUMN_3 + " TEXT," +
                    StudentEntry.COLUMN_4 + " TEXT," +
                    StudentEntry.COLUMN_5 + " TEXT," +
                    StudentEntry.COLUMN_6 + " TEXT," +
                    StudentEntry.COLUMN_7 + " INT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;

}
