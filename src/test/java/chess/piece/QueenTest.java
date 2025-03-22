package chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸의 이동 경로를 계산한다")
    void test1() {
        //given
        final Position position = new Position(Row.FIVE, Column.E);
        final Map<Position, Piece> positionKnightMap = Map.of(new Position(Row.EIGHT, Column.E),
                new Knight(new Position(Row.SIX, Column.E), Color.BLACK));
        final Board board = new Board(positionKnightMap);

        //when
        final Queen queen1 = new Queen(position, Color.BLACK);
        final Queen queen2 = new Queen(position, Color.WHITE);
        final List<Position> positions1 = queen1.calculateAvailablePositions(board);
        final List<Position> positions2 = queen2.calculateAvailablePositions(board);


        //then
        System.out.println(positions1);
        System.out.println(positions1.size());
        System.out.println(positions2);
        System.out.println(positions2.size());

    }

}
