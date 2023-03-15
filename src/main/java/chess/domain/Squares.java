package chess.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Squares {
    private static final List<Square> squares = new ArrayList<>();

    static {
        createSquares();
    }

    private Squares() {
    }

    private static void createSquares() {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.add(Square.of(file, rank));
            }
        }
    }

    public static List<Square> getSquares() {
        return Collections.unmodifiableList(squares);
    }
}
