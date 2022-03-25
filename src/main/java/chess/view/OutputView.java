package chess.view;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printBoard(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.get(new Position(rank, file));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }
}
