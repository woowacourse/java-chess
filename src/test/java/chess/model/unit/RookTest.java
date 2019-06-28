package chess.model.unit;

import chess.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @Test
    void 룩_Navigator_생성_테스트() {
        Piece piece = new Rook(Side.BLACK);
        Square square = Square.of(Column.Col_2, Row.Row_G);
        int distance = Integer.MAX_VALUE;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.N, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.W, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.E, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.S, square, distance));
        assertThat(new HashSet<>(piece.findSquareNavigators(square))).isEqualTo(
                new HashSet<>(squareNavigators));
    }
}
