package chess;

import chess.controller.WebChessController;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {

    public static void main(String[] args) {
        setConfiguration();
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        WebChessController webChessController = new WebChessController(chessBoard);
        webChessController.getMainPage();
        webChessController.getChessBoard();
        webChessController.postMovement();
        webChessController.runExceptionHandler();
        webChessController.getResult();
        webChessController.showResult();
    }

    private static void setConfiguration() {
        port(8080);
        staticFiles.location("/static");
    }
}
