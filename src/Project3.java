import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Project3 {
    private static int totGetCmds = 0;
    private static int totFindCmds = 0;
    private static int totNodesVisited = 0;

    public static void main(String [] arguments) {
        String movieFile = "/Users/hits/eclipse-workspace/Project3/movie.txt";  // This can be changed to read and save movies from a file to the hashmap
        HMap db;                                    // To store movies as a database
        Scanner sc = new Scanner(System.in);        // Will be used to read input from the user
        int hashSize = 0;                           // The size of the array inside the HMap
        while (hashSize <= 1){
            System.out.println("Enter size of the hash table: ");
            String input = sc.nextLine();           // Get the input from the user
            hashSize = Integer.parseInt(input);     // save user input for the size of the hashmap
        }
        if (!movieFile.isBlank())                    // If we have a movie file to import
            db = buildDatabase(movieFile, hashSize); // Create the hashmap from the movieFile
        else                                        // Else there is no movie file
            db = new HMap(hashSize);                // Create an empty hasp map with the given size

        System.out.println("Welcome to the movie database. These are the available options");
        printMenuOptions();                         // Print the options with their cmds
        String input;                               // To save the user input
        char cmd;                                   // To save the cmd input by the user
        do {
            System.out.println("\n\nEnter cmd: ");
            input = sc.nextLine();                  // Get the input from the user
            input = input.toUpperCase();            // Change the cmd/input to uppercase
            cmd = input.charAt(0);                  // We use the first letter of the input as cmd
            menu(cmd, db, sc);                      // Handle the logic of the user
        } while(cmd != 'Q');
        sc.close();

    }

    private static HMap buildDatabase(String movieFile, int hashSize) {
        /**
         * This function takes in a file location, attempts to read the file, create Movie objects, and load them into
         * a self-made HashMap/HMap I created in order to save all the movie objects
         * @param file - Location of the file that contains the movies we want
         * @param hashSize - the size of the array we want to create inside the HMap
         * @return HMap - A HMap, or HashMap, I built for this project to store objects
        **/

        HMap db = new HMap(hashSize);               // Create the empty database
        File file = new File (movieFile);           // Look for the movie file
        int count = 0;
        try {
            Scanner scanner = new Scanner(file);        // Create a scanner to read the file



            while (scanner.hasNextLine())               // While we haven't finished reading the entire file
            {
                Movie tempMovie;                        // Declare a movie object
                String title = "";                      // Title of the movie
                int relYear = 0, runTime = 0;           // Year the movie was released and the running time
                String tempStr;                         // This will be used to save a line and later convert it to int for relYear & runTime

                title = scanner.nextLine();

                tempStr = scanner.nextLine();
                relYear = Integer.parseInt(tempStr);

                tempStr = scanner.nextLine();
                runTime = Integer.parseInt(tempStr);

                // We get here after saving the needed params to create the movie object
                tempMovie = new Movie(title, relYear, runTime);
                boolean didINsert = db.insert(title, tempMovie);            // Insert the movie into the hashmap, with the title being the Key and the movie obj the value
                if (didINsert)
                    count++;
            }
        } catch (FileNotFoundException e){
            System.out.println("File\' " + movieFile + "\' was not found. The database will be empty");
        }
        return db;

    }


    private static void menu(char cmd, HMap db, Scanner input){
        /**
         * Function takes in a cmd provided by the user and handles what option did they enter.
         * The following are the current options available for the user: add, list all, get movie, find movie,
         *  compute the occupancy of the table, and quit
         *
         * @param cmd - Letter that addresses what option the user wants to do
         * @param db - The hashmap that will act as a database of movies
         * */

        Movie movie;
        String title;
        switch (cmd){
            case 'A':
                int yearReleased, runningTime;
                String strInput;
                System.out.println("Enter the title of the movie: ");
                title = input.nextLine();

                System.out.println("Enter the year the movie was released in: ");
                strInput = input.nextLine();                    // Save the user input
                yearReleased = Integer.parseInt(strInput);      // Convert input to an int

                System.out.println("Enter running time for the movie: ");
                strInput = input.nextLine();                    // Save the user input
                runningTime = Integer.parseInt(strInput);       // Convert and save the input as int

                movie = new Movie(title,yearReleased,runningTime);
                db.insert(title,movie);
                break;

            case 'L':
                db.printMap();
                break;

            case 'G':
                totGetCmds++;                                   // Update total times get cmd
                System.out.println("Enter the title of the movie: ");
                title = input.nextLine();
                movie = db.removeValue(title);
                if(movie == null)
                    System.out.println("The movie is not in the database, can't be checkout");
                else
                {
                    db.removeValue(title);
                    System.out.println("The movie " + title + " was checked out");

                }
                totNodesVisited += db.getLastSearchResult();    // Add total nodes visited for this cmd
                break;

            case 'F':
                totFindCmds++;                                  // Update total find cmds
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
                totNodesVisited += db.getLastSearchResult();    // Add total nodes visited for this cmd
                break;

            case 'O':
                double mapSize = db.getSize()  + 0.0;
                double arrSize = db.getMapArrLen() + 0.0;
                double occupancy = mapSize / arrSize;
                System.out.println("Occupancy: " + occupancy );
                break;

            case 'Q':
                System.out.println("Stats of Program: ");
                System.out.println("Get Commands: " + totGetCmds);
                System.out.println("Find Commands: " + totFindCmds);
                System.out.println("Total movies visited: " + totNodesVisited);
                System.out.println("\n\nExiting the program....");
                System.out.println("You exited the program.");

                break;

            case 'H':
                printMenuOptions();
            default:
                System.out.println("Not a valid cmd! Please check the available commands from here: " );
                printMenuOptions();
        }
    }

    private static void printMenuOptions(){
        /**
         * Function prints all available cmd options for the program and a small description
         * */

        System.out.println("CMD | Descriptions");
        System.out.println("A | Add a movie");
        System.out.println("L | List all movies");
        System.out.println("F | Find if a movie is a available");
        System.out.println("G | Get all movies");
        System.out.println("O | Compute the occupancy of the table");
        System.out.println("Q | Quit the database");
        System.out.println("H | Display command options and descriptions");

    }
}

