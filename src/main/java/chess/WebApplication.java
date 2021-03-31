package chess;

import chess.controller.web.WebController;

public class WebApplication {
    public static void main(String[] args) {
        WebController webController = new WebController();
        webController.mapping();
    }
}
