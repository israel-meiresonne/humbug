package g53298.humbug;

import g53298.humbug.controller.Controller;
import g53298.humbug.model.Game;
import g53298.humbug.model.Model;
import g53298.humbug.view.text.InterfaceView;
import g53298.humbug.view.text.View;

/**
 *
 * @author israelmeiresonne
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model game = new Game();
        InterfaceView view = new View();
        Controller ctr = new Controller(view, game);
        ctr.startGame();
    }
    

}
