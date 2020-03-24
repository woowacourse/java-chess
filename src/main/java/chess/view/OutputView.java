package chess.view;

import chess.domain.Position;
import chess.domain.chessPiece.Piece;
import chess.domain.chessboard.ChessBoard;

public class OutputView {
    private static final String EMPTY_MARK = ".";

    public static void printChessBoard(ChessBoard chessBoard) {
        for (Position position : chessBoard.getChessBoard()) {
            if (position.isNewLine()) {
                System.out.println();
            }
            Piece piece = chessBoard.isEqaul(position);
            if (piece == null) {
                System.out.print(EMPTY_MARK);
                continue;
            }
            System.out.print(piece.pieceName());

        }
    }
}
