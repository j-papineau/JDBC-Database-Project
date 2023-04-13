public class Department {

    String depCode, officeNum,officePhone, depHead, depName;

    public Department(String depCode, String officeNum, String officePhone, String depHead, String depName){

        this.depCode = depCode;
        this.officeNum = officeNum;
        this.officePhone = officePhone;
        this.depHead = depHead;
        this.depName = depName;

    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(String officeNum) {
        this.officeNum = officeNum;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getDepHead() {
        return depHead;
    }

    public void setDepHead(String depHead) {
        this.depHead = depHead;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
