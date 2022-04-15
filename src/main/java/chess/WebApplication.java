package chess;

import chess.game.WebChessGame;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        port(8080);
        WebChessGame webChessGame = new WebChessGame();
        webChessGame.run();
    }
}
