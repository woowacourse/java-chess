package chess;

import chess.controller.ChessController;
import chess.controller.ExceptionHandler;
import chess.controller.MoveController;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        setConfiguration();
        get("/", MoveController::moveToMainPage);
        get("/chessboard/result", MoveController::moveToResultPage);
        get("/chessboard/update", ChessController::updateChessBoard);
        post("/chessboard/move", ChessController::move);
        get("/chessboard/result/show", ChessController::showResult);
        get("/chessboard/restart", ChessController::restart);
        exception(RuntimeException.class, ExceptionHandler::bindException);
    }

    private static void setConfiguration() {
        port(8080);
        staticFiles.location("/static");
    }
}
