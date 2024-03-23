package view;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;

import java.util.Map;

import static domain.board.File.*;
import static domain.board.Rank.*;

public class PositionConvertor {
    private static final Map<String, File> files = Map.of(
            "a", A,
            "b", B,
            "c", C,
            "d", D,
            "e", E,
            "f", F,
            "g", G,
            "h", H
    );
    private static final Map<String, Rank> ranks = Map.of(
            "1", ONE,
            "2", TWO,
            "3", THREE,
            "4", FOUR,
            "5", FIVE,
            "6", SIX,
            "7", SEVEN,
            "8", EIGHT
    );

    public static Position convertPosition(String positionString) {
        String[] split = positionString.split("");
        return new Position(files.get(split[0]), ranks.get(split[1]));
    }
}
