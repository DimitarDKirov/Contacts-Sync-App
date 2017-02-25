package teamwork.contacts_sync_app.views.models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String number;
    private String company;

    public Contact(String name, String number ,String company) {
        this.setName(name);
        this.setNumber(number);
        this.setCompany(company);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
