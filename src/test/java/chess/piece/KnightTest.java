package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트의 이동 경로를 계산한다")
    void test1() {
        //given
        final Position position = new Position(Row.FIVE, Column.E);
        final Map<Position, Piece> positionKnightMap = Map.of(new Position(Row.FIVE, Column.F),
                new Knight(new Position(Row.FIVE, Column.F), Color.BLACK));
        final Board board = new Board(positionKnightMap);

        //when
        final Knight knight = new Knight(position, Color.BLACK);
        final List<Position> positions = knight.calculateAvailablePositions(board);

        //then
        System.out.println(positions);
        System.out.println(positions.size());

    }

}
