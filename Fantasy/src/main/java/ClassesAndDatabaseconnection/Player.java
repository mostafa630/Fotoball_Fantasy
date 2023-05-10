package ClassesAndDatabaseconnection;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Player {
    private String id ,userName ,password ;
    private float budget=100;
    private int points =0 ;

    public List<Pair<String , Boolean>> myTeam = new ArrayList<>(); // contain the team of the user
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

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void putFootballerInMyTeam(int index ,String footballerName, Boolean isPlaying){
        Pair<String , Boolean> myPair = new Pair<>(footballerName , isPlaying);
            if(myTeam.get(index).getKey().equals("null"))
                myTeam.set(index, myPair);
            else if(footballerName.equals("null"))
                myTeam.set(index, myPair);

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
    /*
        save the data of the player int the database :
         first we save core data of the player (id ,username ,password ,budget ,points) in the player table
         second we save the team of the user in the teams table
     */
    public void saveToDatebase(Player player)
    {
        // save the core data of the player in the layer team
        Connection con = DatabaseConnection.getConnection(); // connect with database
        // SQl command to inser in data base
        String query ="INSERT INTO player (id ,userName ,password ,budget,points) VALUES(?,?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // replace ? with actual data
            preparedStatement.setString(1,player.getId());
            preparedStatement.setString(2, player.getUserName());
            preparedStatement.setString(3, player.getPassword());
            preparedStatement.setFloat(4,player.getBudget());
            preparedStatement.setInt(5,player.getPoints());
            preparedStatement.executeUpdate(); // make this updates appear in the database
        }
        catch (SQLException ex)
        {
            System.out.println("insertion at player  table failed");
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
         query ="INSERT INTO teams (userName ,player1 ,player2 ,player3,player4,player5,player6,player7,player8,player9,player10,player11,player12,player13,player14,player15) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // replace ? with actual data
            preparedStatement.setString(1,player.getUserName());
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

    /*
       load data of players from the database :
        first we load the core info of the player (id ,username , password ,budget ,points) from the player table
        second we load the user team from the teams table
        */
    public static void loadPlayersFromDatabase() {
        // load main data of player from player table to player haas table
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT * FROM player;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next())
            {
                String id =resultSet.getString("id");
                String userNmae=resultSet.getString("userName");
                String password=resultSet.getString("password");
                float budget =resultSet.getFloat("budget");
                int points =resultSet.getInt("points");

                Player player =new Player(id,userNmae,password);
                player.setBudget(budget);
                player.setPoints(points);
                players.put(userNmae,player);
                playersNationalIDs.put(id ,true);
            }

        } catch (SQLException ex) {
            System.out.println("load data from footballer table failed");
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        // load the user's team from teams table
         con = DatabaseConnection.getConnection();
         query = "SELECT * FROM teams;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next())
            {
                Boolean isPlaying=false;
                List<String>footballerOfTeam=new ArrayList<>();
                String userNmae =resultSet.getString("userName");
                footballerOfTeam.add(resultSet.getString("player1"));
                footballerOfTeam.add(resultSet.getString("player2"));
                footballerOfTeam.add(resultSet.getString("player3"));
                footballerOfTeam.add(resultSet.getString("player4"));
                footballerOfTeam.add(resultSet.getString("player5"));
                footballerOfTeam.add(resultSet.getString("player6"));
                footballerOfTeam.add(resultSet.getString("player7"));
                footballerOfTeam.add(resultSet.getString("player8"));
                footballerOfTeam.add(resultSet.getString("player9"));
                footballerOfTeam.add(resultSet.getString("player10"));
                footballerOfTeam.add(resultSet.getString("player11"));
                footballerOfTeam.add(resultSet.getString("player12"));
                footballerOfTeam.add(resultSet.getString("player13"));
                footballerOfTeam.add(resultSet.getString("player14"));
                footballerOfTeam.add(resultSet.getString("player15"));
                for(String tempFootballer :footballerOfTeam)
                {
                    if(tempFootballer.charAt(tempFootballer.length()-1)=='1')
                        isPlaying=true;
                    else
                        isPlaying=false;
                    String footballer =tempFootballer.substring(0,tempFootballer.length()-1);
                    players.get(userNmae).myTeam.add(new Pair<String ,Boolean>(footballer,isPlaying));
                }

            }

        } catch (SQLException ex) {
            System.out.println("load data from footballer table failed");
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
