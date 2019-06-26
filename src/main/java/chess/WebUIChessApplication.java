package chess;

import chess.controller.ChessGameController;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        port(8080);

        ChessGameController chessGameController = new ChessGameController();

        get("/", chessGameController::main);

        get("/home", chessGameController::start);

        get("/continue/:gameId", chessGameController::continueGame);

        get("/move", chessGameController::move);

        get("/error", chessGameController::printMessage);

        exception(Exception.class, chessGameController::catchException);
    }
}
