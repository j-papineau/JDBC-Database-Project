import java.sql.Date;
public class Patient {

        String patientID, ssn, primaryDocID, secondaryDocID, fName, lName, currentAddress, currentPhone, patientCondition,
        permCity, permState, permStreet, permZip, permPhone, sex;
        Date bDate;
        public Patient(String patientID, String ssn, String primaryDocID, String secondaryDocID, String fName, String lName, String currentAddress,
        String currentPhone, String patientCondition, Date bDate, String permCity, String permState, String permStreet, String permZip,
        String permPhone, String sex){

            this.patientID = patientID;
            this.ssn = ssn;
            this.primaryDocID = primaryDocID;
            this.secondaryDocID = secondaryDocID;
            this.fName = fName;
            this.lName = lName;
            this.currentAddress = currentAddress;
            this.currentPhone = currentPhone;
            this.patientCondition = patientCondition;
            this.bDate = bDate;
            this.permCity = permCity;
            this.permState = permState;
            this.permStreet = permStreet;
            this.permZip = permZip;
            this.permPhone = permPhone;
            this.sex = sex;
        }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPrimaryDocID() {
        return primaryDocID;
    }

    public void setPrimaryDocID(String primaryDocID) {
        this.primaryDocID = primaryDocID;
    }

    public String getSecondaryDocID() {
        return secondaryDocID;
    }

    public void setSecondaryDocID(String secondaryDocID) {
        this.secondaryDocID = secondaryDocID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentPhone() {
        return currentPhone;
    }

    public void setCurrentPhone(String currentPhone) {
        this.currentPhone = currentPhone;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getPermCity() {
        return permCity;
    }

    public void setPermCity(String permCity) {
        this.permCity = permCity;
    }

    public String getPermState() {
        return permState;
    }

    public void setPermState(String permState) {
        this.permState = permState;
    }

    public String getPermZip() {
        return permZip;
    }

    public void setPermZip(String permZip) {
        this.permZip = permZip;
    }

    public String getPermPhone() {
        return permPhone;
    }

    public void setPermPhone(String permPhone) {
        this.permPhone = permPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPermStreet() {
        return permStreet;
    }

    public void setPermStreet(String permStreet) {
        this.permStreet = permStreet;
    }

    public Date getBDate() {
        return bDate;
    }

    public void setBDate(Date bDate) {
        this.bDate = bDate;
    }
}
