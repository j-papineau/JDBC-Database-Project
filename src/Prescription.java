import java.sql.Date;

public class Prescription {

    String name, docID, patID;
    Date date;

    public Prescription(String name, String docID, String patID, Date date){

        this.name = name;
        this.docID = docID;
        this.patID = patID;
        this.date = date;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getPatID() {
        return patID;
    }

    public void setPatID(String patID) {
        this.patID = patID;
    }
}
