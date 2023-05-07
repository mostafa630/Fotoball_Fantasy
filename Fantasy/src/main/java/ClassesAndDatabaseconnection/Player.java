package ClassesAndDatabaseconnection;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Player {
    private String id ,userName ,password ;
    private long budget ;

    public List<Pair<String , Boolean>> myTeam = new ArrayList<>();

    public static Hashtable<String,Player> players = new Hashtable<>();

    public static Hashtable<String , Boolean> playersNationalIDs = new Hashtable<>();

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


    public void putPlayerInMyTeam(String footballerName, Boolean isPlaying){
        Pair<String , Boolean> myPair = new Pair<>(footballerName , isPlaying);
        myTeam.add(myPair);
    }

    public static void putPlayerToPlayers(String name , Player player) {
        players.put(name,player);
    }

    public static void putNationalIDToNationalIDs(String nationalID) {
        playersNationalIDs.put(nationalID , true);
    }

    }
