package chess.view;

import chess.domain.Position;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessboard.ChessBoard;

public class OutputView {
    private static final String EMPTY_MARK = ".";

    public static void printChessBoard(ChessBoard chessBoard) {
        for (Position position : chessBoard.getChessBoard()) {
            seperateLine(position);
            Piece piece = chessBoard.findPieceByPosition(position);
            if (piece == null) {
                System.out.print(EMPTY_MARK);
                continue;
            }
            System.out.print(piece.pieceName());
        }
    }

    private static void seperateLine(Position position) {
        if (position.isNewRank()) {
            System.out.println();
        }
    }
}
