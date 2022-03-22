package chess;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, String> board;

    private Board(Map<Position, String> board) {
        this.board = board;
    }

    public static Board create() {
        Map<Position, String> board = new LinkedHashMap<>(64);
        for (Rank rank : Rank.values()) {
            for (int i=0; i<8; i++) {
                board.put(new Position(File.valueOf(i), rank), checkPiece(rank, i));
            }
        }

        return new Board(board);
    }

    private static String checkPiece(Rank rank, int i) {
        List<String> piece = List.of("R", "N", "B", "Q", "K", "B", "N", "R");
        if (rank == Rank.EIGHT || rank == Rank.ONE) {
            return piece.get(i);
        }
        if (rank == Rank.SEVEN || rank == Rank.TWO) {
            return "P";
        }
        return ".";
    }

    public String getPiece(Position position) {
        return board.get(position);
    }

    public Map<Position, String> getBoard() {
        return board;
    }
}

