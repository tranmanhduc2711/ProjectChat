package GUI;

import java.io.Serializable;

// class luu thong tin client
public class User implements Serializable {
    private static final long serialVersionUID = -4473542480197292876L;

    private String id;
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public User(String id,String name,int age) {
        this.id=id;
        this.name = name;
        this.age=age;
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
