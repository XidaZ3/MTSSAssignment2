////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class UserImpl implements User{
    private long id;
    private String name;
    private int age;

    public UserImpl(long id, String name,int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
