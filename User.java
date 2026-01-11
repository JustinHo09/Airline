/**
 * Justin Ho
 * 03/07/2025
 * CSC 1120
 *
 * Pledge: I have neither given nor received unauthorized aid on this program.
 *
 * Description: This class is used to create and model an instance of a user account. This is used
 * to store the user's information like name, password, birthday, pin, and flight(if any). This class
 * is used in the main Airline class. This class also test the password to see if it is a palindrome.
 *
 * Input: This class not does take in any direct user input, the input for this class comes from
 * the Airline class which does take in user input.
 *
 * Output: This class outputs some information like account details and if the password is a
 * palindrome or not.
 *
 */

import java.util.Stack;

public class User {

    private String name;
    private String password;
    private String birthday;
    private int pin;
    private String flight;

    /**
     * This is a constructor for teh account class and it initilizes the name, password, birthday,
     * and pin of the users account to the inputed values. It does not initilize the flight for the
     * user.
     * @param name This is a String value set to become the name for the account.
     * @param password This is a String value to become the account's password.
     * @param birthday This is a String value to become the account's birthday.
     * @param pin This is the integer value to become the account's pin.
     */
    public User(String name, String password, String birthday, int pin) {
        this.name=name;
        this.password = password;
        this.birthday = birthday;
        this.pin = pin;
    }

    /**
     * This method sets the account's flight to be the flight entered.
     * @param flight This is a String which represents the flight to be set as the booked flight
     */
    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * This method returns the String which is the flight associated with the account.
     * @return The flight ticket and information the user has.
     */
    public String getFlight(){
        return flight;
    }

    /**
     * This method changes the account's password to the inputed password, and then it
     * prints the account details, which also indicate if the new password is a palindrome.
     * @param password The new String to become the password.
     */
    public void changePassword(String password) {
        this.password = password;
        printAccountDetails();
    }

    /**
     * This method returns the account's password.
     * @return The account's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method prints the account's details: name, password, pin, and birthday. This method
     * also checks to see if the new password is a palindrome and prints if it is or isn't.
     */
    public void printAccountDetails(){
        System.out.println(name+"'s Account Details:");
        System.out.println("  Password: "+password);
        System.out.println("  Pin: "+pin);
        System.out.println("  Birthday: "+birthday);
        check_Palindrome(password);
    }

    /**
     * This method checks to see if inputed string is a palindrome or not and then prints to a
     * message indicating if it is a palindrome or not.
     * @param word This is the password entered to be checked.
     */
    public void check_Palindrome(String word){
        //turns the string lowercase to make it not case-sensitive
        String wordLower= word.toLowerCase();
        Stack<Character> check= new Stack<Character>();
        String result="";
        //this adds each character in the String into the Stack one at a time
        for(int i=0;i<wordLower.length();i++){
            check.add(wordLower.charAt(i));
        }
        //This part checks the size of the stack and then uses that in the for loop
        //to use as the bounds.
        int size=check.size();
        //This loop removes the top element of the stack and adds it to string and does it until
        //there is no more in the stack, which reverses the word.
        for(int i=0;i<size;i++){
            result=result+(check.pop());
        }
        //this checks the word and the reversed word to see if they are equal and prints the
        //appropriate message if the word is or is not a palindrome
        if(result.equals(wordLower)){
            System.out.println("Your password is a palindrome");
        }else{
            System.out.println("Your password is not a palindrome");
        }
    }
}
