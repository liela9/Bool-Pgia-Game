import javax.swing.*;

public class Main {

      public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to 'Bool Pgia' game! Lets start");
        String userGuess = startMessage();
        Game game = new Game(userGuess);
        start(game);
    }

    /**
     * This method receives the user guess.
     * checks its valid.
     * calls the method "play" to check the user guess.
     */
    public static void start(Game game){
        String history = "Previous guesses:\n";

        while (true) {
            if (!game.validation()) {//checks if the user input is valid
                if (!messageAndTryAgain("Illegal value. ", game))
                    return;
            } else {// input is valid
                if (game.play()) {// correct guess
                    win(game);
                    if (!messageAndTryAgain("", game))//offers the user one more round

                        return;
                }
                else {// wrong guess - presents the previous wrong guesses to the user
                    history += "Guss "+game.getUserGuess()+": bool-> "+game.getBool()+" pgia-> "+game.getPgia()+"\n";
                    JOptionPane.showMessageDialog(null, history);

                    game.keepGuessing(startMessage());
                }
            }
        }
    }

    /**
     * This method pops up a window with the below message, and return the user input.
     */
    public static String startMessage(){
        return JOptionPane.showInputDialog("Please guess a number with 4 unique digits:");
    }

    /**
     * This method pops up a window with the below message
     * it return true if the user wants to keep try guessing, else return false.
     */
    public static boolean messageAndTryAgain(String message, Game game){
        int choice = JOptionPane.showConfirmDialog(null, message+"Try again?");
        if (choice == JOptionPane.OK_OPTION) {
            game.restart(startMessage());
            return true;
        }
        return false;
    }

    /**
     * This method pops up a window with the below message
     * it informs the user how many gusses they took until success.
     */
    public static void win(Game game){
        JOptionPane.showMessageDialog(null, "Congratulations! it took for you "+
                game.getCountGuesses()+" guesses to success");
    }
}
