import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int totalPoints = 10;
    public static void main( String[] args ) throws Exception {
        File file = new File("movies.txt");
        Scanner sc = new Scanner(file);
        List<String> movieList = new ArrayList<>();
        while( sc.hasNextLine() ) {
            movieList.add(sc.nextLine());//read movie line by line & add to array
        }

        Game game = new Game();
        //pick a movie title randomly
        String movieTitle = game.getMovieTitle(movieList);

        //mask all letters of movie title to _(underscore)
        String maskedTitle = movieTitle.replaceAll("[a-zA-Z0-9]", "-");
        StringBuilder sb = new StringBuilder(maskedTitle);
        System.out.println(maskedTitle);//display a random movieTitle(masked)
        System.out.println("There are " + movieTitle.length() + " letters in the movie.");

        while((totalPoints > 0) && (!(movieTitle.equals(maskedTitle)))) {
            Scanner in = new Scanner( System.in );
            System.out.println( "Guess a letter:" );
            char userInput = in.next().charAt(0);
            if(( (userInput >= '0' && userInput <='9'))) {
                System.out.println("Invalid input. Please Guess a letter:");
                continue;
            }
            int unmaskIndex = movieTitle.indexOf(userInput);

            //setting matched user input letters to masked movie title
            if( unmaskIndex != -1) {
                sb.setCharAt(unmaskIndex, userInput);
            }
            while( unmaskIndex >= 0) {
                unmaskIndex = movieTitle.indexOf(userInput,unmaskIndex+1 );//search for next matching index
                //if more than one occurrence of the letter exist in the title, unmask them all
                if( unmaskIndex != -1) {
                    sb.setCharAt(unmaskIndex, userInput);
                }
            }
            maskedTitle = sb.toString();

            //getting total points after guessing movietitle letter
            totalPoints = game.validation( userInput, movieTitle, maskedTitle );

            if(maskedTitle.equals( movieTitle )) {
                System.out.println("\nYou win! You have guessed '"+movieTitle+"' correctly.");
            }
        }
    }
}
