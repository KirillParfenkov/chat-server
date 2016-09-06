package by.parf.bean;

/**
 * Created by parf on 7.9.16.
 */
public class Registration {

    private String id;
    private String name;

    public Registration() {
    }

    public Registration(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
