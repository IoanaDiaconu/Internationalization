package tutorial.internationalization.model;

import com.sun.istack.internal.NotNull;

/**
 * Created by Ioana on 2/28/2016.
 */
public class User {
    Integer id;
    String name;
    String country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isNew() {
        return (this.id == null);
    }

}
