package chess;

import controller.Controller;
import spark.Spark;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        Spark.staticFileLocation("/public");

        get(Controller.HOME_PATH, Controller::home);
        get(Controller.CHESSGAME_PATH, Controller::chessGame);
        post(Controller.MOVE_PATH, Controller::move);
        get(Controller.STATUS_PATH, Controller::status);
    }
}
