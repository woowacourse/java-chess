package chess2.domain.board;

import chess2.domain.square.Color;
import chess2.domain.square.Empty;
import chess2.domain.square.Pawn;
import chess2.domain.square.Square;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row {

    private static final int ROW_SIZE = 8;
    private final List<Square> squares;

    private Row(List<Square> squares) {
        this.squares = squares;
    }

    public static Row ofEmpty() {
        List<Square> squares = Stream.generate(Empty::new)
                .limit(ROW_SIZE)
                .collect(Collectors.toList());
        return new Row(squares);
    }

    public static Row ofPawn(Color color) {
        List<Square> squares = Stream.generate(() -> new Pawn(color))
                .limit(ROW_SIZE)
                .collect(Collectors.toList());
        return new Row(squares);
    }

    public List<Square> getSquares() {
        return squares;
    }

    @Override
    public String toString() {
        return "Row{" + "squares=" + squares + '}';
    }
}
