import java.util.ArrayList;
import java.util.List;

public class Game {
    static int wrongLetters = 0;
    static int totalPoints = 10;
    static List<Character> wrongChars = new ArrayList< >();

    public String getMovieTitle( List<String> movieList ) {
        int movieListSize = movieList.size();
        int index = (int)(Math.random() * movieListSize + 1);
        String movieTitle = movieList.get(index);
        return movieTitle;
    }

    public int validation( char userInput, String movieTitle, String maskedTitle ) {
        if ( movieTitle.indexOf(userInput) != -1 ) { //if user input matches a letter in the title
            System.out.println("You are guessing: " + maskedTitle);
            System.out.println("You have guessed " + wrongLetters + " wrong letters: "+wrongChars);
        } else { //if user input doesn't match any letter in the title. Skip adding wrong letter more than once
            if( (wrongChars.size() == 0) || ( (wrongChars.size() >= 1) && (wrongChars.indexOf(userInput) == -1 )) ) {
                wrongChars.add( userInput );
                wrongLetters++;
                totalPoints--;
            }
            System.out.println( "You are guessing: " + maskedTitle );
            System.out.println( "You have guessed " + wrongLetters + " wrong letters: "+wrongChars );
        }
        return totalPoints;
    }
}
