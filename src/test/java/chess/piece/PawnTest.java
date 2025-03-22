package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("폰의 이동 경로를 계산 한다")
    void test1() {
        //given
        final Position position = new Position(Row.FIVE, Column.E);
        final Map<Position, Piece> positionKnightMap = Map.of(new Position(Row.SIX, Column.F),
                new Knight(new Position(Row.SIX, Column.F), Color.BLACK));
        final Board board = new Board(positionKnightMap);

        //when
        final Pawn pawn1 = new Pawn(position, Color.BLACK, false);
        final Pawn pawn2 = new Pawn(position, Color.BLACK, true);
        final Pawn pawn3 = new Pawn(position, Color.WHITE, false);
        final List<Position> positions1 = pawn1.calculateAvailablePositions(board);
        final List<Position> positions2 = pawn2.calculateAvailablePositions(board);
        final List<Position> positions3 = pawn3.calculateAvailablePositions(board);

        //then
        System.out.println(positions1);
        System.out.println(positions1.size());
        System.out.println(positions2);
        System.out.println(positions2.size());
        System.out.println(positions3);
        System.out.println(positions3.size());

    }

}
