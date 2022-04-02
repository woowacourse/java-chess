package chess;

import chess.webcontroller.WebController;

public class WebApplication {
    public static void main(String[] args) {
        WebController controller = new WebController();
        controller.run();
    }
}
