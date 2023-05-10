package ClassesAndDatabaseconnection;

import java.util.Hashtable;
import java.util.List;

public class Team {
    private String teamName;
    private String teamLeague;
    // key --> the team name
    // value --> list of players of this team
    public static Hashtable<String , List<String>> teams = new Hashtable<>();

    public Team(String teamName, String teamLeague) {
        this.teamName = teamName;
        this.teamLeague = teamLeague;
    }

    public static Hashtable<String, List<String>> getTeams() {
        return teams;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLeague() {
        return teamLeague;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamLeague(String teamLeague) {
        this.teamLeague = teamLeague;
    }

    public static void putFootballerInTeams(String teamName , String footballerName){
        teams.get(teamName).add(footballerName);
    }


}
