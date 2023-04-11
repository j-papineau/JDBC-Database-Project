import java.sql.Date;
public class Doctor {


    String docID,contactNum,address,fName,lName,phone,deptCode,ssn;
    Date bDate;
    public Doctor(String docID, String contactNum, Date bDate, String address, String fName, String lName, String phone, String deptCode, String ssn){
        this.docID = docID;
        this.contactNum = contactNum;
        this.bDate = bDate;
        this.address = address;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.deptCode = deptCode;
        this.ssn = ssn;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }
}
