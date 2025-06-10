package com.example.bustracker;

public class DetailsDataModel {
    public String name="",email="",id="",boardingPoint="",feeStructure="",phoneNo="",numberPlate,year="",dept="",busNo="",isFeePaid="",passValidFrom="",passValidTill="",joinedYear="";
    public DetailsDataModel(String nameStr,String email,String idStr,String boardingPointStr,String feeStructureStr,String year,String dept,String busNo,String isFeePaid,String passValidFrom,String passValidTill){
        this.name = nameStr;
        this.email = email;
        this.id = idStr;
        this.boardingPoint = boardingPointStr;
        this.feeStructure = feeStructureStr;
        this.year = year;
        this.dept = dept;
        this.busNo = busNo;
        this.isFeePaid = isFeePaid;
        this.passValidFrom = passValidFrom;
        this.passValidTill = passValidTill;
    }
    public DetailsDataModel(String nameStr,String email,String phoneNo,String boardingPointStr,String id,String numberPlate,String joinedYear){
        this.name = nameStr;
        this.email = email;
        this.id = id;
        this.boardingPoint = boardingPointStr;
        this.phoneNo = phoneNo;
        this.numberPlate = numberPlate;
        this.joinedYear = joinedYear;
    }
    public DetailsDataModel(){

    }
}
