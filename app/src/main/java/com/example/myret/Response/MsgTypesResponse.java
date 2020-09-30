package com.example.myret.Response;

import com.example.myret.Modal.MsgTypes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MsgTypesResponse {
    @SerializedName("MsgTypes")
    List<com.example.myret.Modal.MsgTypes> MsgTypes;

    public MsgTypesResponse() {
    }

    public MsgTypesResponse(List<MsgTypes> msgTypes) {
        MsgTypes = msgTypes;
    }

    public List<MsgTypes> getMsgTypes() {
        return MsgTypes;
    }

    public void setMsgTypes(List<MsgTypes> msgTypes) {
        MsgTypes = msgTypes;
    }
}
