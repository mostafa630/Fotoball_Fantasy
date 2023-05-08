package ClassesAndDatabaseconnection;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Player {
    private String id ,userName ,password ;
    private int budget=100000;

    public List<Pair<String , Boolean>> myTeam = new ArrayList<>(12); // contain the team of the user
    private static Hashtable<String,Player> players = new Hashtable<>(); // contains all user of program

    private static Hashtable<String , Boolean> playersNationalIDs = new Hashtable<>(); // contains all users' id

    public Player(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
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

    public void setBudget(int budget) {
        this.budget = budget;
    }


    public void putPlayerInMyTeam(String footballerName, Boolean isPlaying){
        Pair<String , Boolean> myPair = new Pair<>(footballerName , isPlaying);
        myTeam.add(myPair);
    }

    public static Hashtable<String, Player> getPlayers() {
        return players;
    }

    public static Hashtable<String, Boolean> getPlayersNationalIDs() {
        return playersNationalIDs;
    }
    public static void putPlayerToPlayers(String userName , Player player) {
        players.put(userName,player);
    }

    public static void putNationalIDToNationalIDs(String nationalID) {
        playersNationalIDs.put(nationalID , true);
    }
    public void setInitialTeamNull()
    {
        Pair<String ,Boolean> pair=new Pair<>("null",false);
        for(int i=0 ;i<15;i++)
        {
            myTeam.add(pair);
        }
    }
    public void saveToDatebase(Player player)
    {
        Connection con = DatabaseConnection.getConnection(); // connect with database
        // SQl command to inser in data base
        String query ="INSERT INTO player (id ,userName ,password ,budget) VALUES(?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // replace ? with actual data
            preparedStatement.setString(1,player.getId());
            preparedStatement.setString(2, player.getUserName());
            preparedStatement.setString(3, player.getPassword());
            preparedStatement.setFloat(4,player.getBudget());
            preparedStatement.executeUpdate(); // make this updates appear in the database
        }
        catch (SQLException ex)
        {
            System.out.println("insertion at playre  table failed");
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("connection close failed for player");
            }
        }
         con = DatabaseConnection.getConnection(); // connect with database
        // insert teams of user to database
         query ="INSERT INTO teams (id ,player1 ,player2 ,player3,player4,player5,player6,player7,player8,player9,player10,player11,player12,player13,player14,player15) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // replace ? with actual data
            preparedStatement.setString(1,player.getId());
            for(int i=2 ,j=0 ;j<myTeam.size();i++,j++) {
                String temp = player.myTeam.get(i-2).getKey();
                if(player.myTeam.get(i-2).getValue()==false) {
                    temp+="0";
                }else{
                    temp+="1";
                }
                preparedStatement.setString(i, temp);
            }
            preparedStatement.executeUpdate(); // make this updates appear in the database
        }
        catch (SQLException ex)
        {
            System.out.println("insertion at teams  table failed");
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("connection close failed for teams");
            }
        }
    }


}
