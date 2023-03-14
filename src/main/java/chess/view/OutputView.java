package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Rank;
import chess.domain.Square;

public class OutputView {

    public void printBoard(Board board) {
        for (int i = board.getRanks().size() - 1; i > -1; i--) {
            Rank rank = board.getRanks().get(i);
            printRank(rank);
            System.out.println();
        }

    }

    private static void printRank(Rank rank) {
        for (Square square : rank.getSquares()) {
            Piece piece = square.getPiece();
            PieceType pieceType = piece.getPieceType();
            PieceTypeView pieceTypeView = PieceTypeView.of(pieceType);
            System.out.print(pieceTypeView.getMessage(piece.getColor()));
        }
    }
}
