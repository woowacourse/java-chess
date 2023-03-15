package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class OutputView {

    public void printBoard(Board board) {
        for (Rank rank : board.getRanks()) {
            printRank(rank);
            System.out.println();
        }
    }

    private void printRank(Rank rank) {
        for (Square square : rank.getSquares()) {
            Piece piece = square.getPiece();
            PieceType pieceType = piece.getPieceType();
            PieceTypeView pieceTypeView = PieceTypeView.of(pieceType);
            System.out.print(pieceTypeView.getMessage(piece.getColor()));
        }
    }
}
