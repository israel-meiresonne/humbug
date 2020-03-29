package g53298.humbug.controller;

import g53298.humbug.model.Direction;
import g53298.humbug.model.Model;
import g53298.humbug.view.text.InterfaceView;

/**
 *
 * @author israelmeiresonne
 */
public class Controller {
    private Model game;
    private InterfaceView view;
    
    public Controller(InterfaceView view, Model game){
        this.game = game;
        this.view = view;
    }
    
    public void startGame(){
        game.startLevel(1);
        boolean isOver = false;
        while(!isOver){
            view.displayBoard(game.getBoard(), game.getAnimals());
            game.move(view.askPosition(), view.askDirection());
            isOver = game.levelIsOver();
        }
        view.displayBoard(game.getBoard(), game.getAnimals());
        System.out.println("La partie est terminée!");
    }
}
