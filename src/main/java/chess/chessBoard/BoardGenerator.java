package chess.chessBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardGenerator {

    private static final List<Square> board = createBoard();

    private static List<Square> createBoard() {
        return Arrays.stream(Lettering.values())
                .flatMap(lettering -> Arrays.stream(Numbering.values())
                        .map(numbering -> new Square(lettering, numbering)))
                .toList();
    }

    public static List<Square> generate() {
        return new ArrayList<>(board);
    }
}
