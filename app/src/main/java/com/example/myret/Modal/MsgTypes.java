package com.example.myret.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class MsgTypes {


    @SerializedName("TypeID")
    private int TypeID;

    @SerializedName("TypeDescription")
    private String TypeDescription;

    @SerializedName("newMsg")
    private String newMsg;

    private int counter;

    public MsgTypes(int typeID, String typeDescription, String newMsg, int counter) {
        TypeID = typeID;
        TypeDescription = typeDescription;
        this.newMsg = newMsg;
        this.counter = counter;
    }

    public MsgTypes() {
    }

    public int getTypeID() {
        return TypeID;
    }

    public void setTypeID(int typeID) {
        TypeID = typeID;
    }

    public String getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        TypeDescription = typeDescription;
    }

    public String getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(String newMsg) {
        this.newMsg = newMsg;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "MsgTypes{" +
                "TypeID=" + TypeID +
                ", TypeDescription='" + TypeDescription + '\'' +
                ", newMsg='" + newMsg + '\'' +
                ", counter=" + counter +
                '}';
    }
}
