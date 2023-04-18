public class Prescription {

    String name, docID, patID;

    public Prescription(String name, String docID, String patID){

        this.name = name;
        this.docID = docID;
        this.patID = patID;

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
