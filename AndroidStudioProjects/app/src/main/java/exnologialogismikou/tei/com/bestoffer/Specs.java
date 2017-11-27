package exnologialogismikou.tei.com.bestoffer;

public class Specs {
    private String  editTextName;
    private String  editTextAddress;
    private String  editTextPhone;
    private String  editTextTime;


    public Specs(String Name,String Address,String Phone,String Time){
        this. editTextName = Name;
        this.editTextAddress = Address;
        this.editTextPhone = Phone;
        this.editTextTime = Time;
    }

    public String getName() {
        return editTextName;
    }

    public void setName(String Name) {
        editTextName = Name;
    }

    public String getAddress() {
        return editTextAddress;
    }

    public void setAddress(String Address) {
        editTextAddress = Address;
    }

    public String getPhone() {
        return editTextPhone;
    }

    public void setEditTextPhone(String Phone) {
        editTextPhone = Phone;
    }

    public String getTime() {
        return editTextTime;
    }

    public void setEditTextTime(String Name) {
        editTextName = Name;
    }
}
