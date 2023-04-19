import java.sql.Date;

public class Interaction {

    int interID;
    String patientID, interTime, interDesc;
    Date date;

    public Interaction(int interID, String patientID, String interTime, Date date, String interDesc){

        this.interID = interID;
        this.patientID = patientID;
        this.interTime = interTime;
        this.date = date;
        this.interDesc = interDesc;

    }

    public int getInterID() {
        return interID;
    }

    public void setInterID(int interID) {
        this.interID = interID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getInterTime() {
        return interTime;
    }

    public void setInterTime(String interTime) {
        this.interTime = interTime;
    }

    public String getInterDesc() {
        return interDesc;
    }

    public void setInterDesc(String interDesc) {
        this.interDesc = interDesc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
