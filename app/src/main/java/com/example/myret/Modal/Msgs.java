package com.example.myret.Modal;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Msgs implements Serializable, Parcelable {


    @ColumnInfo(name = "MsgID")
    @NonNull
    private int MsgID;

    @SerializedName("MsgDescription")
    private String MsgDescription;

    @SerializedName("TypeDescription")
    private int TypeDescription;

    @SerializedName("newMsg")
    private int newMsg;

    private int origPos;

    private int isFav;

    private int orderFav;


    public Msgs() {

    }

    public Msgs(int msgID, String msgDescription, int TypeDescription, int newMsg) {
        MsgID = msgID;
        MsgDescription = msgDescription;
        TypeDescription = TypeDescription;
        this.newMsg = newMsg;
    }

    public Msgs(String msgDescription, int typeDescription, int newMsg) {
        MsgDescription = msgDescription;
        TypeDescription = typeDescription;
        this.newMsg = newMsg;
    }

    public int getMsgID() {
        return MsgID;
    }

    public void setMsgID(int msgID) {
        MsgID = msgID;
    }

    public String getMsgDescription() {
        return MsgDescription;
    }

    public void setMsgDescription(String msgDescription) {
        MsgDescription = msgDescription;
    }

    public int getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(int typeDescription) {
        TypeDescription = typeDescription;
    }

    public int getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(int newMsg) {
        this.newMsg = newMsg;
    }

    public int getOrigPos() {
        return origPos;
    }

    public void setOrigPos(int origPos) {
        this.origPos = origPos;
    }

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
    }

    public int getOrderFav() {
        return orderFav;
    }

    public void setOrderFav(int orderFav) {
        this.orderFav = orderFav;
    }

    @Override
    public String toString() {
        return "Msgs{" +
                "MsgID=" + MsgID +
                ", MsgDescription='" + MsgDescription + '\'' +
                ", TypeDescription=" + TypeDescription +
                ", newMsg=" + newMsg +
                ", origPos=" + origPos +
                ", isFav=" + isFav +
                ", orderFav=" + orderFav +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
