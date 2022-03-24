package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,TWO",
            "B,ONE,A,TWO",
            "B,TWO,A,ONE",
            "A,TWO,B,ONE",
            "A,ONE,C,THREE",
            "C,ONE,A,THREE",
            "C,THREE,A,ONE",
            "A,THREE,C,ONE"
    })
    @DisplayName("비숍이 갈 수 있는 위치 중 하나여야 한다.")
    void canValidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertDoesNotThrow(() -> new BishopMoveStrategy()
                .canMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                )
        );
    }
    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,THREE"
    })
    @DisplayName("비숍이 갈 수 있는 위치가 아니면 에러가 발생해야 한다.")
    void canInvalidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertThatThrownBy(() -> new BishopMoveStrategy()
                .canMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍이 이동할 수 없는 위치입니다.");
    }
}
