package chess.model.unit;

import chess.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void 나이트_Navigator_생성_테스트() {
        Piece piece = new Knight(Side.BLACK);
        Square square = Square.of(Column.Col_2, Row.Row_G);
        int distance = 1;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.NNE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.NNW, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.SEE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.SWW, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.SSE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.SSW, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.NEE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.NWW, square, distance));
        assertThat(new HashSet<>(piece.findSquareNavigators(square))).isEqualTo(
                new HashSet<>(squareNavigators));
    }
}