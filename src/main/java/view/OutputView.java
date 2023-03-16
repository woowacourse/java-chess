package view;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Rank;

import java.util.Map;

public class OutputView {
    public void printChessBoard(Map<Position, Piece> chessBoard) {
        Rank currentRank = Rank.EIGHT;
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            if (!currentRank.equals(positionPieceEntry.getKey().getRank())) {
                currentRank = currentRank.getPrevious();
                System.out.println();
            }
            System.out.print(positionPieceEntry.getValue().getCategory().getPieceValue());
        }
    }
}
