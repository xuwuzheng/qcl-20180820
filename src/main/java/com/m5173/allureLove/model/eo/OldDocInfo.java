package com.m5173.allureLove.model.eo;

import com.m5173.allureLove.common.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_feedback")
public class OldDocInfo extends BaseEntity {

    private String AUTHOR;

    private String PRAISEYES;

    private String TYPENAME;

    private String VISITNUM;

    private String STATE;

    private String WRITEPOP;

    private String ANSWERINGNUM;

    private String READPOP;

    private String HOTNUM;

    private String GROUPNAME;

    private String LASTVTIME;

    private String SHORTTITLE;

    private String PRAISENO;

    private String EVALUATE;

    private String ID;

    private String DOCDESCRIBE;

    private String DOMTYPE;

    private String PUBTIME;

    private String TITLE;

    private String TAGKEY;

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getPRAISEYES() {
        return PRAISEYES;
    }

    public void setPRAISEYES(String PRAISEYES) {
        this.PRAISEYES = PRAISEYES;
    }

    public String getTYPENAME() {
        return TYPENAME;
    }

    public void setTYPENAME(String TYPENAME) {
        this.TYPENAME = TYPENAME;
    }

    public String getVISITNUM() {
        return VISITNUM;
    }

    public void setVISITNUM(String VISITNUM) {
        this.VISITNUM = VISITNUM;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getWRITEPOP() {
        return WRITEPOP;
    }

    public void setWRITEPOP(String WRITEPOP) {
        this.WRITEPOP = WRITEPOP;
    }

    public String getANSWERINGNUM() {
        return ANSWERINGNUM;
    }

    public void setANSWERINGNUM(String ANSWERINGNUM) {
        this.ANSWERINGNUM = ANSWERINGNUM;
    }

    public String getREADPOP() {
        return READPOP;
    }

    public void setREADPOP(String READPOP) {
        this.READPOP = READPOP;
    }

    public String getHOTNUM() {
        return HOTNUM;
    }

    public void setHOTNUM(String HOTNUM) {
        this.HOTNUM = HOTNUM;
    }

    public String getGROUPNAME() {
        return GROUPNAME;
    }

    public void setGROUPNAME(String GROUPNAME) {
        this.GROUPNAME = GROUPNAME;
    }

    public String getLASTVTIME() {
        return LASTVTIME;
    }

    public void setLASTVTIME(String LASTVTIME) {
        this.LASTVTIME = LASTVTIME;
    }

    public String getSHORTTITLE() {
        return SHORTTITLE;
    }

    public void setSHORTTITLE(String SHORTTITLE) {
        this.SHORTTITLE = SHORTTITLE;
    }

    public String getPRAISENO() {
        return PRAISENO;
    }

    public void setPRAISENO(String PRAISENO) {
        this.PRAISENO = PRAISENO;
    }

    public String getEVALUATE() {
        return EVALUATE;
    }

    public void setEVALUATE(String EVALUATE) {
        this.EVALUATE = EVALUATE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDOCDESCRIBE() {
        return DOCDESCRIBE;
    }

    public void setDOCDESCRIBE(String DOCDESCRIBE) {
        this.DOCDESCRIBE = DOCDESCRIBE;
    }

    public String getDOMTYPE() {
        return DOMTYPE;
    }

    public void setDOMTYPE(String DOMTYPE) {
        this.DOMTYPE = DOMTYPE;
    }

    public String getPUBTIME() {
        return PUBTIME;
    }

    public void setPUBTIME(String PUBTIME) {
        this.PUBTIME = PUBTIME;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTAGKEY() {
        return TAGKEY;
    }

    public void setTAGKEY(String TAGKEY) {
        this.TAGKEY = TAGKEY;
    }
}