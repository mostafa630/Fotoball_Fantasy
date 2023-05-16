package ClassesAndDatabaseconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Season {
    private final static int numOfRounds = 3;
    private static int  currentRound = 0;
    private static boolean canModify = true;
    private static int numOfUpdatedFootballer =0;

    public static int getCurrentRound() {
        return currentRound;
    }

    public static void setCurrentRound(int currentRound) {
        Season.currentRound = currentRound;
    }

    public static boolean isCanModify() {
        return canModify;
    }

    public static void setCanModify(boolean canModify) {
        Season.canModify = canModify;
    }

    public static int getNumOfUpdatedFootballer() {
        return numOfUpdatedFootballer;
    }

    public static void setNumOfUpdatedFootballer(int numOfUpdatedFootballer) {
        Season.numOfUpdatedFootballer = numOfUpdatedFootballer;
    }



    public static void saveToDatabase()
    {
        Connection con = DatabaseConnection.getConnection(); // connect with database
        // SQl command to insert in data base
        String query ="INSERT INTO season (currentRound , canModify,numOfUpdatedFootballer) VALUES(?,?,?);";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)) { // insert values of columns for a row
            preparedStatement.setInt(1,currentRound);
            preparedStatement.setBoolean(2,canModify);
            preparedStatement.setInt(3, numOfUpdatedFootballer);

            preparedStatement.executeUpdate(); // make this updates appear in the database
        }
        catch (SQLException ex)
        {
            System.out.println("insertion at season table failed");
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("connection close failed for season");
            }
        }
    }




    public static void loadSeasonFromDatabase() {
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT * FROM season;";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet =preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int currentRound=resultSet.getInt("currentRound");
                boolean canModify =resultSet.getBoolean("canModify");
                int numOfUpdatedFootballers=resultSet.getInt("numOfUpdatedFootballer");
                setCurrentRound(currentRound);
                setCanModify(canModify);
                setNumOfUpdatedFootballer(numOfUpdatedFootballers);
            }

        } catch (SQLException ex) {
            System.out.println("load data from season table failed");
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
                System.out.println("close season table failed");

            }
        }
    }

}
