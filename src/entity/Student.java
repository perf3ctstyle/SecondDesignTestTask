package entity;

public class Student {

    private final String name;
    private final String middleName;
    private final String surname;
    private final String cityOfResidence;
    private boolean isStudying = false;

    public Student(String name, String middleName, String surname, String cityOfResidence) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.cityOfResidence = cityOfResidence;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public boolean getIsStudying() {
        return isStudying;
    }

    public void setIsStudying(boolean isStudying) {
        this.isStudying = isStudying;
    }
}
