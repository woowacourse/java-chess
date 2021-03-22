package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }

}
