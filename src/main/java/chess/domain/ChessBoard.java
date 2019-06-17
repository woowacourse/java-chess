package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    private List<List<String>> board;

    public ChessBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(".");
            }
            board.add(row);
        }
        setMal();
    }

    public void setMal() {
        board.set(0, Arrays.asList("RNBQKBNR".split("")));
        board.set(1, Arrays.asList("PPPPPPPP".split("")));
        board.set(6, Arrays.asList("pppppppp".split("")));
        board.set(7, Arrays.asList("RNBQKBNR".toLowerCase().split("")));
    }

    public String malStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<String> strings : board) {
            stringBuilder.append(String.join("", strings)).append("\n");
        }
        return stringBuilder.toString();
    }

    public String status() {
        return malStatus();
    }

    public void move(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException();
        }
        if (from.charAt(0) < 97 || from.charAt(0) > 104
                || from.charAt(1) < 49 || from.charAt(1) > 56) {
            throw new IllegalArgumentException();
        }

        if (to.charAt(0) < 97 || to.charAt(0) > 104
                || to.charAt(1) < 49 || to.charAt(1) > 56) {
            throw new IllegalArgumentException();
        }
    }
}
