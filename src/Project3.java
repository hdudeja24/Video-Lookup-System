import java.sql.SQLOutput;
import java.util.Scanner;

public class Project3 {

    public static void main(String [] arguments) {
//        String movieFile = "";
//        HMap db = (movieFile.isEmpty()) ? new HMap() :
//        if (!movieFile.isEmpty())
//            db = database(movieFile);

        Scanner sc = new Scanner(System.in);        // Will be used to read input from the user
        int hashSize = 0;
        while (hashSize <= 1){
            System.out.println("Enter size of the hash table: ");
            hashSize = sc.nextInt();                // save user input for the size of the hashmap
        }
        HMap db = new HMap(hashSize);               // Create a hasp map with the given size
        System.out.println("Welcome to the movie database. These are the available options");
        printMenuOptions();                         // Print the options with their cmds
        String input;                               // To save the user input
        char cmd;                                   // To save the cmd input by the user
        do {
            System.out.println("Enter cmd: ");
            input = sc.nextLine();                  // Get the input from the user
            cmd = input.charAt(0);                  // We use the first letter of the input as cmd
            menu(cmd, db);                          // Handle the logic of the user
        } while(cmd != 'Q');
        sc.close();
        System.out.println("You exited the program.");






    }

    private static HMap database(String file) {
        /**
         * This function takes in a file location, attempts to read the file, create Movie objects, and load them into
         * a self-made HashMap/HMap I created in order to save all the movie objects
         * @param file - Location of the file that contains the movies we want
         * @return HMap - A HMap, or HashMap, I built for this project to store objects
        * */

        return null;

    }


    private static void menu(char cmd, HMap db){
        /**
         * Function takes in a cmd provided by the user and handles what option did they enter.
         * The following are the current options available for the user: add, list all, get movie, find movie,
         *  compute the occupancy of the table, and quit
         *
         * @param cmd - Letter that addresses what option the user wants to do
         * @param db - The hashmap that will act as a database of movies
         * */

        Scanner input = new Scanner(System.in);     // To read input of user if needed
        Movie movie;
        String title;
        switch (cmd){
            case 'A':
                int yearReleased, runningTime;
                System.out.println("Enter the title of the movie: ");
                title = input.nextLine();

                System.out.println("Enter the year the movie was released in: ");
                yearReleased = input.nextInt();

                System.out.println("Enter running time for the movie: ");
                runningTime = input.nextInt();

                movie = new Movie(title,yearReleased,runningTime);
                db.insert(title,movie);
                break;

            case 'L':
                db.printMap();
                break;

            case 'G':
                System.out.println("Enter the title of the movie: ");
                title = input.nextLine();
                movie = db.getValue(title);
                if(movie == null)
                    System.out.println("The movie is not in the database, can't be checkout");
                else
                {
                    db.removeValue(title);
                    System.out.println("The movie " + title + " was checked out");
                }
                break;

            case 'F':
                System.out.println("Enter the title of the movie: ");
                title = input.nextLine();
                movie = db.getValue(title);
                if(movie == null)
                    System.out.println("The movie is not available");
                else
                {
                    System.out.println("Title: " + movie.getTitle());
                    System.out.println("Year released: " + movie.getYearReleased());
                    System.out.println("Running time: " + movie.getRunningTime());
                }
                break;

            case 'O':
                System.out.println("Occupancy: " + (db.getSize() / db.getMapArrLen()) );
                break;

            case 'Q':
                System.out.println("Exiting the program....");
                break;

            case 'H':
                printMenuOptions();
            default:
                System.out.println("Not a valid cmd! Please check the available commands from here: ");
        }
        input.close();
    }

    private static void printMenuOptions(){
        /**
         *
         * */

        System.out.println("CMDS | Descriptions");
        System.out.println("A | Add a movie");
        System.out.println("L | List all movies");

    }
}

