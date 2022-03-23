package chess;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.Location;
import chess.domain.Rank;
import chess.domain.piece.Piece;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Board board = new Board();
        Map<Location, Piece> board1 = board.getBoard();

        for (Rank rank : Rank.reverseValues()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                stringBuilder.append(board1.get(Location.of(file, rank)).toString());
            }
            System.out.println(stringBuilder);
        }
    }
}
