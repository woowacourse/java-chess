package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;

import java.util.Arrays;

import static spark.Spark.staticFiles;

public class Application {

    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessController chessController = new ChessController();
        chessController.run();
    }

}
