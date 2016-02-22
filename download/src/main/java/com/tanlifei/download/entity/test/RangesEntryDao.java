package com.tanlifei.download.entity.test;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.tanlifei.download.entity.test.RangesEntry;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "range".
*/
public class RangesEntryDao extends AbstractDao<RangesEntry, Void> {

    public static final String TABLENAME = "range";

    /**
     * Properties of entity RangesEntry.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", true, "URL");
        public final static Property Range_key = new Property(2, Integer.class, "range_key", false, "RANGE_KEY");
        public final static Property RangeTotalLength = new Property(3, Integer.class, "rangeTotalLength", false, "RANGE_TOTAL_LENGTH");
        public final static Property RangesEntryId = new Property(4, String.class, "rangesEntryId", false, "RANGES_ENTRY_ID");
    };

    private Query<RangesEntry> downloadEntry_RangesQuery;

    public RangesEntryDao(DaoConfig config) {
        super(config);
    }
    
    public RangesEntryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"range\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"URL\" TEXT PRIMARY KEY NOT NULL ," + // 1: url
                "\"RANGE_KEY\" INTEGER," + // 2: range_key
                "\"RANGE_TOTAL_LENGTH\" INTEGER," + // 3: rangeTotalLength
                "\"RANGES_ENTRY_ID\" TEXT NOT NULL );"); // 4: rangesEntryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"range\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RangesEntry entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUrl());
 
        Integer range_key = entity.getRange_key();
        if (range_key != null) {
            stmt.bindLong(3, range_key);
        }
 
        Integer rangeTotalLength = entity.getRangeTotalLength();
        if (rangeTotalLength != null) {
            stmt.bindLong(4, rangeTotalLength);
        }
        stmt.bindString(5, entity.getRangesEntryId());
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public RangesEntry readEntity(Cursor cursor, int offset) {
        RangesEntry entity = new RangesEntry( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // url
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // range_key
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // rangeTotalLength
            cursor.getString(offset + 4) // rangesEntryId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RangesEntry entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.getString(offset + 1));
        entity.setRange_key(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setRangeTotalLength(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setRangesEntryId(cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(RangesEntry entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(RangesEntry entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "ranges" to-many relationship of DownloadEntry. */
    public List<RangesEntry> _queryDownloadEntry_Ranges(String rangesEntryId) {
        synchronized (this) {
            if (downloadEntry_RangesQuery == null) {
                QueryBuilder<RangesEntry> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.RangesEntryId.eq(null));
                downloadEntry_RangesQuery = queryBuilder.build();
            }
        }
        Query<RangesEntry> query = downloadEntry_RangesQuery.forCurrentThread();
        query.setParameter(0, rangesEntryId);
        return query.list();
    }

}