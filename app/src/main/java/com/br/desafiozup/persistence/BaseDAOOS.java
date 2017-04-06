package com.br.desafiozup.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.br.desafiozup.database.BaseDAO;
import com.br.desafiozup.database.EntitiePersistable;
import com.br.desafiozup.database.util.DataSerializer;

public abstract class BaseDAOOS<T extends EntitiePersistable> extends BaseDAO<T> {

    protected Context context;

    public BaseDAOOS(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public DataSerializer getDataSerializer() {
        return DataSerializerImp.getInstance();
    }

    @Override
    public SQLiteOpenHelper getDataBaseHelper() {
        return OpenHelperDesafioZup.getInstance(getContext());
    }

    public void beginTransaction() {
        dataBase.beginTransaction();
    }

    public void setTransactionSuccessful() {
        dataBase.setTransactionSuccessful();
    }

    public void endTransaction() {
        dataBase.endTransaction();
    }

    public void deleteByIds(long[] ids) {
        String where = generateWhereClause(ids);
        String[] whereArgs = generateWhereArgs(ids);

        delete("id in " + where, whereArgs);
    }

    private String[] generateWhereArgs(long[] ids) {

        String[] whereArgs = new String[ids.length];

        for(int i = 0; i < ids.length; i ++) {
            whereArgs[i] = String.valueOf(ids[i]);
        }

        return whereArgs;
    }

    private String generateWhereClause(long[] ids) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");

        for(int i = 0; i < ids.length; i++) {

            if(i != 0) {
                stringBuilder.append(",");
            }

            stringBuilder.append("?");
        }

        stringBuilder.append(");");
        String where = stringBuilder.toString();
        return where;
    }
}