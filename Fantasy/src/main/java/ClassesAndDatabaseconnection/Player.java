package ClassesAndDatabaseconnection;
import java.util.Hashtable;
public class Player {
    private String id ,userName ,password ;
    private long budget ;

    Hashtable<String ,Footballer>myTeam=new Hashtable<>(); // key-->footballername , value---> object of this player

    public Player(String id, String userName, String password, long budget) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.budget = budget;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setFootballerInMyTeam(String name ,Footballer footballer){
        myTeam.put(name ,footballer);
    }


}
