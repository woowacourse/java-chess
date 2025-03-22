package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {


    @Test
    @DisplayName("비숍의 이동 경로를 계산 한다")
    void test1() {
        //given
        final Position position = new Position(Row.FIVE, Column.E);
        final Map<Position, Piece> positionKnightMap = Map.of(new Position(Row.SIX, Column.F),
                new Knight(new Position(Row.SIX, Column.F), Color.BLACK));
        final Board board = new Board(positionKnightMap);

        //when
        final Bishop bishop1 = new Bishop(position, Color.WHITE);
        final Bishop bishop2 = new Bishop(position, Color.BLACK);
        final List<Position> positions1 = bishop1.calculateAvailablePositions(board);
        final List<Position> positions2 = bishop2.calculateAvailablePositions(board);

        //then
        System.out.println(positions1);
        System.out.println(positions1.size());
        System.out.println(positions2);
        System.out.println(positions2.size());
    }

}
