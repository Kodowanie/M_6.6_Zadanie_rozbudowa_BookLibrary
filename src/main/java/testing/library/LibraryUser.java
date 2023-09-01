package testing.library;

import java.util.Objects;

public class LibraryUser {

    private String firstname;
    private String lasname;
    private String peselId;

    public LibraryUser(String firstname, String lasname, String peselId) {
        this.firstname = firstname;
        this.lasname = lasname;
        this.peselId = peselId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLasname() {
        return lasname;
    }

    public String getPeselId() {
        return peselId;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLasname(String lasname) {
        this.lasname = lasname;
    }

    public void setPeselId(String peselId) {
        this.peselId = peselId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibraryUser that = (LibraryUser) o;

        if (!Objects.equals(firstname, that.firstname)) return false;
        if (!Objects.equals(lasname, that.lasname)) return false;
        return Objects.equals(peselId, that.peselId);
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lasname != null ? lasname.hashCode() : 0);
        result = 31 * result + (peselId != null ? peselId.hashCode() : 0);
        return result;
    }
}
