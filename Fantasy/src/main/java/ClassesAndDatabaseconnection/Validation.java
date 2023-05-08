package ClassesAndDatabaseconnection;

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
        /*
        If the national ID matches the pattern,
        we then validate it according to the rules for an Egyptian national ID.
        In this case, the first 13 digits are weighted,
        and the resulting sum is divided by 11.
        The remainder of this division is then used to calculate the check digit.
        If the check digit matches the last digit of the national ID, it is considered valid.
         */
        // Check if the national ID is a valid Egyptian national ID and unique
        int sum = 0;
        int[] weights = {2, 7, 6, 5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            int digit = Integer.parseInt(nationalID.substring(i, i + 1));
            sum += digit * weights[i];
        }
        int checkDigit = Integer.parseInt(nationalID.substring(13));
        int remainder = (sum % 11);
        int calculatedCheckDigit = (remainder == 0 || remainder == 1) ? remainder : 11 - remainder;
        return (checkDigit == calculatedCheckDigit && !Player.getPlayersNationalIDs().containsKey(nationalID));
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



}
