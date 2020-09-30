package com.example.myret.Response;

import com.example.myret.Modal.Msgs;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MsgsResponse {

    //المشكله كانت هنا
    @SerializedName("Msgs")
    List<Msgs> msgs;

    public MsgsResponse() {
    }

    public MsgsResponse(List<Msgs> msgs) {
        this.msgs = msgs;
    }

    public List<Msgs> getMsgs() {
        return msgs;
    }

    public void setMsgsList(List<Msgs> msgs) {
        this.msgs = msgs;
    }
}
