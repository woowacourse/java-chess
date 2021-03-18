package chess.controller;

import chess.domain.board.Board;
import chess.domain.piece.PieceFactory;
import chess.view.InputView;

public class ChessController {

    public static void run() {

        String input = InputView.inputCommandFromUser();
        Board board = null;

        if (input.startsWith("start") && board == null) {
            board = new Board(PieceFactory.createPieces());
        }


    }
}
