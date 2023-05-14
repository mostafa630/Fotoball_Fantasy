package ClassesAndDatabaseconnection;

import javafx.util.Pair;

public class Validation {
    /*
    - The username length must range between 8 and 20 characters otherwise, it will consider as an invalid username.
    - The username is allowed to contain only alphanumeric characters.
    - The first character of the username must be an alphabetic character, i.e., [a-z].
    - The username must be unique.
    */
    public static boolean usernameValidation(String username){
        String regularExpression = "^[a-z][a-z0-9]{7,19}$";

        return (username.matches(regularExpression) && !Player.getPlayers().containsKey(username));

    }


    /*
    - The National ID length must be 14 digits otherwise, it will consider as an invalid national ID.
    - The National ID is allowed to contain only numeric characters.
    - The National ID can not start with leading zeros.
    - The National ID must be unique.

    */
    public static boolean nationalIdValidation(String nationalID){
        String regularExpression = "^[1-9]\\d{13}$";
        if(!nationalID.matches(regularExpression)){
            return false;
        }
        else return true ; //-----> will be removed
        /*
        If the national ID matches the pattern,
        we then validate it according to the rules for an Egyptian national ID.
        In this case, the first 13 digits are weighted,
        and the resulting sum is divided by 11.
        The remainder of this division is then used to calculate the check digit.
        If the check digit matches the last digit of the national ID, it is considered valid.
         */
        // Check if the national ID is a valid Egyptian national ID and unique
        /*int sum = 0;
        int[] weights = {2, 7, 6, 5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            int digit = Integer.parseInt(nationalID.substring(i, i + 1));
            sum += digit * weights[i];
        }
        int checkDigit = Integer.parseInt(nationalID.substring(13));
        int remainder = (sum % 11);
        int calculatedCheckDigit = (remainder == 0 || remainder == 1) ? remainder : 11 - remainder;
        return (checkDigit == calculatedCheckDigit && !Player.getPlayersNationalIDs().containsKey(nationalID));*/
    }

    /*
    - The password length must range between 8 and 20 characters otherwise, it will consider as an invalid password.
    - The password is allowed to contain any characters.
    */
    public static boolean passwordValidation(String password){
        return (password.length() >= 8 && password.length() <= 20);
    }
    public static boolean confirmPassword(String password , String confirmedPassword){
        return (password.equals(confirmedPassword));
    }

    /*
    - check if the username exist or not.
    - if existed, check if the password correct or not.
     */
    public static boolean loginCheck(String username , String password){
        return (Player.getPlayers().containsKey(username) &&
                password.equals(Player.getPlayers().get(username).getPassword()));
    }


    /*
    - This Function will be used to check if Team Name is valid or Not
    - the following conditions should be applied:
        1- It must be Alphabetic characters only.
        2- It must be Pascal Case -> (the first letter is capital ).
        3- It must be new team name -> (doesn't exist before) --> I will check it at AddNewTeamController just for message
     */

    public static boolean teamNameValidation(String teamName){


        // Define regular expression to match capital words
        String regularExpression = "\\b[A-Z][a-z]+(?: [A-Z][a-z]+)*\\b";
        /*
            This regular expression uses the \b anchor to match word boundaries,
             [A-Z] to match an uppercase letter,
              [a-z]+ to match one or more lowercase letters,
               (?: [A-Z][a-z]+)* to match only whitespace character followed by an uppercase letter
                and one or more lowercase letters,
                 and \b again to match the end of a word.
            The \\b characters match word boundaries, ensuring that the pattern only matches complete words
             rather than just uppercase letters within larger words.
               The * at the end makes sure that the entire pattern can repeat zero or more times.
         */

        return (teamName.matches(regularExpression));
    }

    /*
    - This Function will be used to check if Footballer Name is valid or Not
    - the following conditions should be applied:
        1- It must be Alphabetic characters only.
        2- It must be Pascal Case -> (the first letter is capital ).
        3- It must be new footballer name -> (doesn't exist before) --> I will check it at AddNewFootballerController just for message
     */
    public static boolean footballerNameValidation(String footballerName){


        // Define regular expression to match capital words
        String regularExpression = "\\b[A-Z][a-z]+(?: [A-Z][a-z]+)*\\b";
        /*
            This regular expression uses the \b anchor to match word boundaries,
             [A-Z] to match an uppercase letter,
              [a-z]+ to match one or more lowercase letters,
               (?: [A-Z][a-z]+)* to match only whitespace character followed by an uppercase letter
                and one or more lowercase letters,
                 and \b again to match the end of a word.
            The \\b characters match word boundaries, ensuring that the pattern only matches complete words
             rather than just uppercase letters within larger words.
               The * at the end makes sure that the entire pattern can repeat zero or more times.
         */
        return (footballerName.matches(regularExpression));
    }

    /*

    we will validate the footballer price, but the price is taken as a string,
    so we will firstly check if it is valid or not, then convert it to float

     */

    public static boolean footballerPriceValidation(String footballerPrice){

        /*
        Here's what each part of the regular expression means:

        ^ - Matches the start of the string.
        [0-9] - Matches any digit from 0 to 9.
        \\. - Matches a dot character.
        $ - Matches the end of the string.
        So this regular expression matches any string that starts with a single digit (0-9),
         followed by a dot, and then ends with another single digit (0-9).
                 */
        String regularExpression = "^[1-9][0-9]*\\.[0-9]$";

        return (footballerPrice.matches(regularExpression));
    }


    /*
        Rules for footballers prices:
        Goalkeeper: £4.0k to £6.0k
        Defender: £4.5k to £7.5k
        Midfielder: £5.0k to £12.0k
        Forward: £6.0k to £14.0k
    */
    public static Pair<String , Boolean> footballerPricePositionValidation(String position , float price){
        Pair<String , Boolean> pair;
        if(position.equals("Goalkeeper")){
            if(price >= 4.0 && price <= 6.0){
                pair = new Pair<>("Valid Price" , true);
            }else{
                pair = new Pair<>("Goalkeeper Price: £4.0k to £6.0k" , false);
            }
        }else if(position.equals("Defender")){
            if(price >= 4.5 && price <= 7.5){
                pair = new Pair<>("Valid Price" , true);
            }else{
                pair = new Pair<>("Defender Price: £4.5k to £7.5k" , false);
            }
        } else if (position.equals("Midfielder")) {
            if(price >= 5.0 && price <= 12.0){
                pair = new Pair<>("Valid Price" , true);
            }else{
                pair = new Pair<>("Midfielder Price: £5.0k to £12.0k" , false);
            }
        }else{
            // Forward
            if(price >= 6.0 && price <= 14.0){
                pair = new Pair<>("Valid Price" , true);
            }else{
                pair = new Pair<>("Forward Price: £6.0k to £14.0k" , false);
            }
        }

        return pair;
    }


    public static boolean footballerPointsValidation(String footballerPoints){
        String regularExpression = "^[1-9][0-9]*";

        return footballerPoints.matches(regularExpression);
    }
}
