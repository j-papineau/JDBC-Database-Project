import java.sql.Date;

public class Procedure {

    String procNum, duration, description, name, patientID, docID, depCode, notes, time;
    Date date;

    public Procedure(String procNum, String duration, String description, String name, String patientID, String docID, String depCode,
                     String notes, Date date, String time){
        this.procNum = procNum;
        this.duration = duration;
        this.description = description;
        this.name = name;
        this.patientID = patientID;
        this.docID = docID;
        this.depCode = depCode;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }
    public Procedure(String name, String procNum, String duration, String depCode, String description){

        this.name = name;
        this.procNum = procNum;
        this.duration = duration;
        this.depCode = depCode;
        this.description = description;
        this.patientID = "";
        this.docID = "";
        this.notes = "";
        this.date = null;
        this.time = null;

    }

    public String getProcNum() {
        return procNum;
    }

    public void setProcNum(String procNum) {
        this.procNum = procNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
