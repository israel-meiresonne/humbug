package g53298.humbug.controller;

import g53298.humbug.model.Model;
import g53298.humbug.model.Position;
import g53298.humbug.view.text.InterfaceView;

/**
 * This class is used to manage the view and the model to run the game
 *
 * @author israelmeiresonne
 */
public class Controller {

    private final Model game;
    private final InterfaceView view;

    /**
     * Constructor
     *
     * @param view used to display de the game
     * @param game contains the logic of the game
     */
    public Controller(InterfaceView view, Model game) {
        this.game = game;
        this.view = view;
    }

    /**
     * Start and manage a game
     */
    public void startGame() {
        int level = view.askInt("Choisis un niveau", "Ce niveau n'est pas "
                + "disponible!");
        game.startLevel(level);

        boolean isOver = false;
        boolean isWin = false;
        boolean isLose = false;
        while (!isOver) {
            if (isWin) {
                System.out.println("Vois-ci le suivant, le niveau " + (++level));
                game.startLevel(level);
                isWin = false;
            } else if (isLose) {
                game.startLevel(level);
                isLose = false;
            }

            System.out.println();
            view.displayRemainingMoves(game.getRemainingMoves());
            view.displayBoard(game.getBoard(), game.getAnimals());
            Position position = view.askPosition();

            if (game.isAnimalOn(position)) {
                game.move(position, view.askDirection());
            } else {
                view.displayError("Il n'y a pas d'animal à cette position!");
            }

            switch (game.getLevelStatus()) {
                case FAIL:
                    view.displayError("Désolé, vous avez perdu, le niveau est "
                            + "redémaré!");
                    isLose = true;
                    break;
                case WIN:
                    System.out.println("Félicitation, tu as réussi le niveau "
                            + level + "!");
                    isWin = true;
//                    isOver = Level.MaxLevel; // test si il reste des niveau 
                    // jouable
                    break;
            }
//            isOver = game.levelIsOver();
        }
        view.displayBoard(game.getBoard(), game.getAnimals());
        System.out.println("La partie est terminée!");
    }
}
