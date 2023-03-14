package view;

import domain.ChessBoard;
import domain.Color;
import domain.Piece;
import domain.Rank;

import java.util.List;

public class OutputView {

    public void printChessBoard(ChessBoard chessBoard) {
        List<Rank> ranks = chessBoard.getChessBoard();

        for (Rank rank : ranks) {
            printRank(rank);
        }
    }

    private void printRank(final Rank rank) {
        for (Piece piece : rank.getRank()) {
            System.out.print(convertPieceToElement(piece));
        }
        System.out.println();
    }

    private String convertPieceToElement(final Piece piece) {
        final String elementName = ChessBoardElement.from(piece.getType()).getElementName();

        if (piece.getColor() == Color.BLACK) {
            return elementName.toUpperCase();
        }
        return elementName;
    }

}
