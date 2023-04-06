import java.sql.Date;

public class Employee {

    String fname, lname, address, sex;
    Date bdate;
    int ssn, salary, superssn, dno;
    public Employee(String fname, String lname, int ssn, Date bdate, String address, String sex,int salary,int superssn,int dno){

    this.fname = fname;
    this.lname = lname;
    this.ssn = ssn;
    this.bdate = bdate;
    this.address = address;
    this.sex = sex;
    this.salary = salary;
    this.superssn = superssn;
    this.dno = dno;


    }//end constructor

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public Date getBdate() {
        return bdate;
    }

    public int getSsn() {
        return ssn;
    }

    public int getSalary() {
        return salary;
    }

    public int getSuperssn() {
        return superssn;
    }

    public int getDno() {
        return dno;
    }
}
