package com.example.myret.Sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myret.Modal.MsgTypes;
import com.example.myret.Modal.Msgs;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class Sqlite extends SQLiteOpenHelper {

    public Sqlite(Context context) {
        super(context, "a0a1sqwqqs", null, 1);
    }

    public Sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String a = "Create Table MessageTypes (TypeID integer   primary key, TypeDescription text ,newMsg integer  )";
        db.execSQL(a);

        String b = "Create Table Messages     (MsgID  integer   primary key,MsgDescription text,TypeDescription integer,origPos integer ,newMsg int,fav int,orderOfFav int)";
        db.execSQL(b);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void saveMsgTypes(MsgTypes cMT) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into MessageTypes(TypeID,TypeDescription,newMsg) values(" + cMT.getTypeID() + ",'" + cMT.getTypeDescription() + "'," + cMT.getNewMsg() + ")";
        db.execSQL(sql);
        db.close();
    }

    public List<MsgTypes> getMsgTypes() {
        MsgTypes u;

        List<MsgTypes> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT *   FROM MessageTypes ", null);

        if (c.moveToFirst()) {

            do {
                u = new MsgTypes();
                u.setTypeID(c.getInt(0));
                u.setTypeDescription(c.getString(1));
                u.setNewMsg(c.getString(2));

                Cursor countCursor = db.rawQuery("SELECT count(*) as c from messages where TypeDescription=" + c.getInt(0), null);
                if (countCursor.moveToFirst()) {
                    u.setCounter(countCursor.getInt(0));
                }

                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }

    public void saveMessages(Msgs msg) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into Messages(MsgID,MsgDescription,TypeDescription,origPOS,newMsg) values(" + msg.getMsgID() + ",'" + msg.getMsgDescription() + "'," + msg.getTypeDescription() + "," + msg.getOrigPos() + "," + msg.getNewMsg() + ")";
        db.execSQL(sql);
        db.close();
    }

    public List<Msgs> getMessages(int typeID) {
        Msgs u;

        List<Msgs> myList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where TypeDescription='" + typeID + "' order by msgID desc", null);

        if (c.moveToFirst()) {

            do {
                u = new Msgs();
                u.setMsgID(c.getInt(0));
                u.setMsgDescription(c.getString(1));
                u.setTypeDescription(c.getInt(2));
                u.setOrigPos(c.getInt(3));
                u.setNewMsg(c.getInt(4));
                u.setOrderFav(c.getInt(6));
                myList.add(u);
            }
            while (c.moveToNext());
        }
        c.close();
        db.close();
        return myList;
    }

    public void clearTables() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from MessageTypes";
        db.execSQL(sql);
        db.close();
    }

    public void ClearMsgs(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from messages";
        db.execSQL(sql);
        db.close();
    }




//    public void changeFav(Msgs msg, int intFav) {
//
//
//        SQLiteDatabase db = getWritableDatabase();
//        String sql = "select max(orderOfFav) from messages";
//        int intMaxOrderOfFav = 0;
//        Cursor c = db.rawQuery(sql, null);
//        if (c.moveToFirst()) ;
//        {
//            intMaxOrderOfFav = c.getInt(0);
//        }
//        intMaxOrderOfFav = intMaxOrderOfFav + 1;
//        if (intFav == 0) {
//            sql = "update Messages set fav=" + intFav + " ,orderOfFav=0 where msgID=" + msg.getMsgID();
//            db.execSQL(sql);
//            sql = "delete from  favs where msgID=" + msg.getMsgID();
//            db.execSQL(sql);
//        } else {
//
//            sql = "update Messages set fav=" + intFav + " ,orderOfFav=" + intMaxOrderOfFav + " where msgID=" + msg.getMsgID();
//            db.execSQL(sql);
//
//            sql = "insert into favs values(" + msg.getMsgID() + ")";
//            db.execSQL(sql);
//        }
//
//        c.close();
//        db.close();
//    }
//
//    public void updateFavOnRefersh() {
//        SQLiteDatabase db = getWritableDatabase();
//        String sql = "update messages set fav=1 where msgid in(select msgid from favs)";
//        db.execSQL(sql);
//
//    }
//
//    public int getIFMsgIsFav(Msgs m) {
//        int result = 0;
//        SQLiteDatabase db = getWritableDatabase();
//        Cursor countCursor = db.rawQuery("SELECT fav  from messages where msgID=" + m.getMsgID(), null);
//        if (countCursor.moveToFirst()) {
//            result = countCursor.getInt(0);
//        }
//        db.close();
//        return result;
//    }
//
//
//    public List<Msgs> getFavMessages() {
//        Msgs u;
//
//        List<Msgs> myList = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where fav=1  order by orderOfFav desc", null);
//
//        if (c.moveToFirst()) {
//
//            do {
//                u = new Msgs();
//                u.setMsgID(c.getInt(0));
//                u.setMsgDescription(c.getString(1));
//                u.setTypeId(c.getInt(2));
//                u.setOrigPos(c.getInt(3));
//                u.setNewMsg(c.getInt(4));
//                u.setOrderFav(c.getInt(6));
//                myList.add(u);
//            }
//            while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return myList;
//    }
//
//    public List<Msgs> getFavMessagesOrderedASC() {
//        Msgs u;
//
//        List<Msgs> myList = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where fav=1  order by orderOfFav asc", null);
//
//        if (c.moveToFirst()) {
//
//            do {
//                u = new Msgs();
//                u.setMsgID(c.getInt(0));
//                u.setMsgDescription(c.getString(1));
//                u.setTypeId(c.getInt(2));
//                u.setOrigPos(c.getInt(3));
//                u.setNewMsg(c.getInt(4));
//                u.setOrderFav(c.getInt(6));
//
//                myList.add(u);
//            }
//            while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return myList;
//    }
//
//
//    public List<Msgs> getFavMessagesNotOrdered() {
//        Msgs u;
//
//        List<Msgs> myList = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery(" SELECT *   FROM Messages   where fav=1  ", null);
//
//        if (c.moveToFirst()) {
//
//            do {
//                u = new Msgs();
//                u.setMsgID(c.getInt(0));
//                u.setMsgDescription(c.getString(1));
//                u.setTypeId(c.getInt(2));
//                u.setOrigPos(c.getInt(3));
//                u.setNewMsg(c.getInt(4));
//                u.setOrderFav(c.getInt(6));
//                myList.add(u);
//            }
//            while (c.moveToNext());
//        }
//        c.close();
//        db.close();
//        return myList;
//    }
//



}
