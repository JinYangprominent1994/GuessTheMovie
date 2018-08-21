import java.io.File;
import java.util.Scanner;

public class Game {
    private Game() throws Exception{
        playGame(SelectMovie());
    }

    private String SelectMovie() throws Exception{
        // read the name of movies, and print these names
        File file = new File("ListOfMovie.txt");
        Scanner scanner = new Scanner(file);
        /*
        try {
            int numOfMovie = 0;     // calculate the number of movie
            while (scanner.hasNextLine()) {
                numOfMovie++;
                System.out.println(numOfMovie);
                scanner.nextLine();
            }//if hasNextLine = true, the number of movie would add one
        }
        catch(Exception e){
            scanner.close();
        }
        */

        String[] movie = new String[25];//use string array movie to store the name of movies

        for (int i = 0; i < 24; i++) {
            movie[i] = scanner.nextLine();
        }

        int randomNum = (int) (Math.random() * 25);// randomNum is the random index of the array of movie
        return movie[randomNum];
    }


    private void playGame(String guessedMovie) {
        String selectedMovieName = guessedMovie.replaceAll(" ", "");
        //eliminate all blank spaces in the name of movie
        System.out.println(selectedMovieName);

        char[] movieChar = selectedMovieName.toCharArray();//put all characters into a char string
        char[] underline = new char[movieChar.length];//put '_' into a char array

        System.out.print("You are guessing:");
        for (int t = 0; t < movieChar.length; t++) {
            underline[t] = '_';
            System.out.print(underline[t]+" ");
        }//print out "_", the number of which is similar to that of characters
        System.out.println(" ");
        System.out.println("You have guessed 0 wrong letter");//start the game

        char[] wrongChar = new char[10];//this array is used to store the wrong user's input character
        int score = 10;  //check if the user has used the total number of chances to guess name
        boolean correctChar = false; //check if user has guess all characters correctly
        int m = 0;    //calculate the wrong number of input
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print("Guessing a letter: ");
            String userString = scanner.nextLine();
            char userInput = userString.charAt(0);

            for (int j = 0; j < movieChar.length; j++) {
                if (userInput == movieChar[j]) {//if guess a correct character, use this character to replace "_"
                    underline[j] = movieChar[j];
                    correctChar = true;
                }
            }
            if(!correctChar) {//if input a wrong char
                wrongChar[m] = userInput;
                m += 1;
                score -= 1;//guess a wrong character, put this character into the array, and "score" subtract 1
            }
            correctChar=false;

            System.out.print("You are guessing: ");
            for (int n = 0; n < movieChar.length; n++) {
                System.out.print(underline[n]+" ");
            }//print out the current situation

            System.out.println(" ");
            System.out.print("You have guessed " + m + " wrong letter: ");
            for (int t = 0; t < m; t++) {
                System.out.print(wrongChar[t]+" ");
            }
            System.out.println(" ");//print out all wrong char

            String guessAllChar = String.valueOf(underline);
            if (guessAllChar.equals(selectedMovieName)) {
                System.out.println("You win");
                System.out.println("you have guessed " + guessedMovie + " successfully");
                break;
            }//have guessed all characters, user win

            if (score == 0) {
                System.out.println("Game Over");
                break;
            }//all chances are used, user lose
        }while(true);
    }

    public static void main(String[] args) throws Exception{
        Game game=new Game();
    }
}
