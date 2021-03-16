package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void printBoard() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Position position = Position.of(file.getFile() + rank.getRank());
                if(!board.containsKey(position)) {
                    System.out.printf(".");
                    continue;
                }

                Piece piece = board.get(position);
                if (piece.getName() == null) {
                    System.out.printf("?");
                } else {
                    System.out.printf(piece.getName());
                }
            }
            System.out.println();
        }
    }
}
