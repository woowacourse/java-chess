package chess;

import chess.controller.ChessController;
import chess.controller.MoveController;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        setConfiguration();
        get("/", MoveController::moveToMainPage);
        get("/chessboard/result", MoveController::moveToResultPage);
        get("/chessboard", ChessController::updateChessBoard);
        post("/chessboard/move", ChessController::move);
//
//        webChessController.runExceptionHandler();
//        webChessController.showResult();
    }

    private static void setConfiguration() {
        port(8080);
        staticFiles.location("/static");
    }
}
