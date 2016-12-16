package org.acm.nitsurat.acmsvnit;

import android.graphics.Bitmap;

import java.sql.Blob;

/**
 * Created by karan1106 on 16/12/16.
 */

public class TeamMember {
    private String mMemberName;
    private String mRole;
    private String mYear;
    private String mAreaExp;
    private String mMobNo;
    Bitmap mImage;
    public TeamMember(String memberName,String role,String year,String areaExp,String mobNo, Bitmap image){
        mMemberName = memberName;
        mMobNo = mobNo;
        mYear = year;
        mAreaExp = areaExp;
        mRole = role;
        mImage = image;
    }
    public String getMemberName() {
        return mMemberName;
    }

    public void setMemberName(String memberName) {
        mMemberName = memberName;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getAreaExp() {
        return mAreaExp;
    }

    public void setAreaExp(String areaExp) {
        mAreaExp = areaExp;
    }

    public String getMobNo() {
        return mMobNo;
    }

    public void setMobNo(String mobNo) {
        mMobNo = mobNo;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }
}
