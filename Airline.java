/**
 * Justin Ho
 * 03/07/2025
 * CSC 1120
 *
 * Pledge: I have neither given nor received unauthorized aid on this program.
 *
 * Description: This is the main class that simulates the Airline account where the user can
 * check their account, view available flights, book a ticket, or change the password.
 * This also creates the user's account by requesting them to enter input like name, birthday
 * password, and pin and when it prints the account details it will also
 * indicate if the password is a palindrome. It will also print out a menu that will show the
 * user options they can enter. The flights ArrayList contains information on where
 * it is going from and to, airline, and return time. This will continue to run until the user
 * selects the quit option.
 *
 * Input: The user enters input to enter their account information, select options on the menu
 * to see or book a ticket, quit the program, change their password. The values they input
 * generally are words with some being numbers like the pin or options.
 *
 * Output: The output of the program is the menu which contains the options for the user to
 * interact with. As well as information on the flights, their account details, and if their
 * password is a palindrome.
 *
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Airline {

    private static boolean running = true;
    private static ArrayList<String> flights = new ArrayList<>();
    private static Scanner scan1= new Scanner(System.in);
    private static User account;

    public static void main(String[] args){
        //Uses the methods appropriate methods to ensure the name, birthday, password, and pin
        // are the right input or in the proper format.
        String name= nameRegex();
        String birthday=birthdayRegex();
        String password=setPassword();
        int pin=setPin();
        account= new User(name,password,birthday,pin);
        //This prints the starting information and then begins the main program
        printTitle();
        account.printAccountDetails();
        makeFlights();
        printMenu();
        Scanner scan2= new Scanner(System.in);
        while(running==true){
                switch (scan2.nextLine()){
                    case "1":
                        //This will carry out the first option and display all available flights
                        displayFlights();
                        pause1();
                        break;
                    case "2":
                        //This will book the users flight by using the bookFlight method
                        bookFlight();
                        break;
                    case "3":
                        //This carries out the view ticket option and prints out the user's ticket
                        System.out.println("View your ticket selected");
                        System.out.println(account.getFlight());
                        pause2();
                        break;
                    case "4":
                        //This carries out the fourth option and changes their password to the next input
                        System.out.println("Change password selected.Please enter new password(" +
                                "Current password: "+account.getPassword()+")");
                        account.changePassword(scan1.nextLine());
                        pause2();
                        break;
                    case "5":
                        //This ends the simulation
                        quit();
                        break;
                    default:
                        //This is here in case the user enters something that is not one of the options.
                        System.out.println("Not a valid input, try again");
                        break;
                }
        }
    }

    /**
     *This method prints out the title and name of the Airport
     */
    private static void printTitle(){
        System.out.println("--------------------------------------------------");
        System.out.println("          Palindrome Airport Enterprises");
    }

    /**
     * This method prints out the menu and showcases the options the user can enter
     */
    private static void printMenu(){
        System.out.println("--------------------------------------------------");
        System.out.println("Please enter one of the following options to continue");
        System.out.println("1: View available flights");
        System.out.println("2: Book a ticket");
        System.out.println("3: View your ticket");
        System.out.println("4: Change your password");
        System.out.println("5: quit");
    }

    /**
     * This method adds Strings representing the flights and their details to the flights ArrayList
     */
    private static void makeFlights(){
        flights.add("Nashville to Tokyo:Southwest:Departure: 11:00 CST -- Return: 04/30: 15:00 CST");
        flights.add("Huston to London:Ryanair:Departure: 02:00 CTS -- Return: 04/13: 20:00 CST");
        flights.add("Cairo to Capetown:Delta:Departure: 13:00 GMT -- Return: 6/25: 17:00 GMT");
        flights.add("Berlin to Manchester:United:Departure: 23:00 GMT -- Return: 7/24: 01:00 GMT");
        flights.add("Phoenix to Singapore:Emirates:Departure: 09:00 PST -- Return: 9/07: 02:00 PST");
    }

    /**
     * This method books the users flight from their input, prints instructions, and catches
     * any exception that may result from user input.
     */
    private static void bookFlight(){
        System.out.println("Book a ticket selected, enter flight number");
        //This is used to keep the loop continuous until the try block goes without an exception
        boolean valid=false;
        while (!valid) {
            try {
                //this tries to se the users flight to the input, which should be one of the flight
                //numbers and when it does it will end the while loop.
                account.setFlight(flights.get(scan1.nextInt()-1));
                valid=true;
            }catch(IndexOutOfBoundsException exception) {
                //This catches an index out of bounds in case the user enters a number that is not
                //within the bounds of the flights ArrayList and removes the invalid input and
                //contines the while loop.
                System.out.println("Not valid flight number try again");
                scan1.next();
            }catch (InputMismatchException exception){
                //This catches an input mismatch in case the user enters non-integer values
                //and removes the invalid input and loops again.
                System.out.println("Not valid flight number try again");
                scan1.next();
            }
        }
        //This prints out conformation that they book that flight and then uses the first pause method
        System.out.println(account.getFlight()+" booked");
        pause1();
    }

    /**
     * This method prints out all of the flights and their information from the flights ArrayList
     */
    private static void displayFlights(){
        System.out.println("View available flights selected");
        for(String s: flights){
            System.out.println("Flight number: "+(flights.indexOf(s)+1)+"--"+s);
        }
    }

    /**
     * This is the first pause method that is used with options 1 and 2. This first scans to remove
     * the input remaining and then carries out the pause, which prints for the user to enter
     * anything before the program continues and prints the menu for them continue.
     */
    private static void pause1(){
        scan1.nextLine();
        System.out.println("Please enter anything to continue");
        if(scan1.nextLine()!=null){
            printMenu();
        }
    }

    /**
     * This is the second pause method that is used with options 3 and 4. This just prints the
     * message for the user to enter anything to continue and once the input is entered it
     * continues and prints the menu
     */
    private static void pause2(){
        System.out.println("Please enter anything to continue");
        if(scan1.nextLine()!=null){
            printMenu();
        }
    }

    /**
     * This method ends the game and carries out the fifth option by setting running to false
     * and ends the main while loop.
     */
    private static void quit(){
        running=false;
    }

    /**
     * This method gets the users input and returns the name if it meets the valid pattern, which
     * is just only having letters, '.', ' ', or '-'. This prints a message if the input does not
     * match the pattern and loops until the input is valid.
     * @return The String that is going to be used as the account's name.
     */
    private static String nameRegex(){
        System.out.println("Please enter the account name");
        Boolean valid=false;
        String name="";
        //loops until the result is valid
        while(!valid) {
            //checks to see if the input fits with the regex pattern
            name = scan1.nextLine();
            Pattern pattern = Pattern.compile("([a-zA-Z-.' ']*)");
            Matcher matcher = pattern.matcher(name);
            boolean result = matcher.matches();
            //ends the loop if the result is true and continues it and prints a message
            // instructing them how to do so until it is.
            if (result == true){
                valid=true;
            } else {
                name="";
                System.out.println("Only letters, spaces, hyphens, and periods are allowed");
            }
        }
        return name;
    }

    /**
     * This method starts and is used to take user input to set the birthday for the account. This
     * loops until it matches the appropriate pattern of mm/dd/yyyy.
     * @return The String to be used as the account's birthday.
     */
    private static String birthdayRegex(){
        System.out.println("Please enter your birthday");
        Boolean valid=false;
        String bday="";
        //loops until the input matches the regex pattern
        while(!valid){
            //checks to see if the input matches the regex pattern
            bday= scan1.nextLine();
            Pattern pattern= Pattern.compile("([0-9]{2})+(/[0-9]{2})+(/[0-9]{4})");
            Matcher matcher= pattern.matcher(bday);
            boolean result=matcher.matches();
            //if it does match the pattern it ends the loop, but if it doesn't then it
            //contintues it until it does while also printing a message instructing them to follow
            //the format.
            if(result==true){
                valid=true;
            }else{
                bday="";
                System.out.println("Only enter numbers 0-9 and follow the mm/dd/yyyy format");
            }
        }
        return bday;
    }

    /**
     * This method takes in user input to be used as the pin and catches to see if the input is
     * an integer or not and loops until it is.
     * @return The integer to be used as the pin.
     */
    private static int setPin(){
        System.out.println("Please enter account pin");
        Boolean result=false;
        int pin=0;
        //loops until the result is valid
        while(!result) {
            //when the input is valid, then it ends the loop.
            try{
                pin=scan1.nextInt();
                result=true;
            //catches the input mismatch exception if they enter a non integer value and then
            //handles it by removing the incorrect input and printing a message instructing the
            //user what to input.
            }catch(InputMismatchException exception){
                System.out.println("Not valid pin. Try again with numbers 0-9");
                scan1.next();
            }
        }
        return pin;
    }

    /**
     *This method prints an instruction adn then takes the next user input to
     * set as the new password.
     * @return The String that to become the password.
     */
    private static String setPassword(){
        System.out.println("Please enter account password");
        return scan1.nextLine();
    }
}
