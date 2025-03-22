package chess.piece;

import static chess.Fixtures.A3;
import static chess.Fixtures.B1;
import static chess.Fixtures.D4;
import static chess.Fixtures.makeGeneralBoard;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.board.Board;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {
    @DisplayName("Knight는_중간에_다른_기물을_뛰어넘을_수_있다")
    @Test
    void move1() {
        //when
        //then
        assertThatCode(() -> makeGeneralBoard().move(B1, A3))
                .doesNotThrowAnyException();
    }

    @DisplayName("Knight는_적합한_경로일_시_이동할_수_있다")
    @CsvSource(value = {"C:SIX", "E:SIX", "C:TWO", "E:TWO", "B:THREE", "B:FIVE", "F:THREE", "F:FIVE"},
            delimiterString = ":")
    @ParameterizedTest
    void move2(Column column, Row row) {
        // given
        Map<Position, Piece> initialBoard = new HashMap<>();
        Knight knight = new Knight(Color.WHITE);
        initialBoard.put(D4, knight);
        Board board = new Board(initialBoard);
        Position goal = new Position(column, row);

        //when
        board.move(D4, goal);

        //then
        assertThat(board).extracting("board", as(MAP))
                .containsEntry(goal, knight);
    }
}
