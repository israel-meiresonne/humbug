package g53298.humbug.controller;

import g53298.humbug.model.Level;
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
    private boolean isWin;
    private boolean isLose;

    /**
     * Constructor
     *
     * @param view used to display de the game
     * @param game contains the logic of the game
     */
    public Controller(InterfaceView view, Model game) {
        this.game = game;
        this.view = view;
        isWin = false;
        isLose = false;
    }

    /**
     * Start and manage a game
     */
    public void startGame() {
        int level = view.askLevel("Choisis un niveau", "Ce niveau n'est pas "
                + "disponible!");
        game.startLevel(level);

        boolean isOver = false;

        while (!isOver) {
            level = updateLevel(level);

            System.out.println();
            view.displayRemainingMoves(game.getRemainingMoves());
            view.displayBoard(game.getBoard(), game.getAnimals());

            Position position = view.askPosition();
            if (game.isAnimalOn(position)) {
                game.move(position, view.askDirection());
            } else {
                view.displayError("Il n'y a pas d'animal à cette position!");
            }
            isOver = isOver(level);
        }
        view.displayBoard(game.getBoard(), game.getAnimals());
        System.out.println("La partie est terminée!");
    }

    /**
     * Update the game's level
     *
     * @param level game's current level
     * @return game's new level
     */
    private int updateLevel(int level) {
        if (isWin) {
            System.out.println("Vois-ci le suivant, le niveau " + (++level));
            game.startLevel(level);
            isWin = false;
        } else if (isLose) {
            game.startLevel(level);
            isLose = false;
        }
        return level;
    }

    /**
     * Update attributs isWin and isLose
     *
     * @param level game's current level
     * @return true if there is another level playable else false
     */
    private boolean isOver(int level) {
        boolean isOver = false;
        switch (game.getLevelStatus()) {
            case FAIL:
                view.displayError("Désolé, vous avez perdu, le niveau est "
                        + "redémaré!");
                this.isLose = true;
                break;
            case WIN:
                view.displaySuccess("Félicitation, tu as réussi le niveau "
                        + level + "!");
                this.isWin = true;
                isOver = !Level.levelExist(level+1);
                if(isOver){
                    view.displaySuccess("Tous les niveaux sont fini!");
                }
                break;
        }
        return isOver;
    }

}
