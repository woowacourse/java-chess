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

class KnightMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "B,ONE,A,THREE",
            "B,ONE,C,THREE",
            "C,ONE,A,TWO",
            "C,ONE,E,TWO",
            "B,THREE,A,ONE",
            "B,THREE,C,ONE",
            "C,THREE,A,TWO",
            "C,THREE,E,TWO"
    })
    @DisplayName("나이트가 갈 수 있는 위치 중 하나여야 한다.")
    void canValidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertDoesNotThrow(() -> new KnightMoveStrategy()
                .isValidateCanMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "D,ONE,D,FIVE",
            "D,ONE,B,THREE",
            "D,ONE,F,THREE",
            "D,THREE,A,THREE",
            "D,THREE,B,ONE",
            "D,FIVE,D,ONE",
            "D,THREE,F,ONE",
            "D,THREE,F,THREE"
    })
    @DisplayName("나이트가 갈 수 있는 위치가 아니면 에러가 발생한다.")
    void canInvalidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertThatThrownBy(() -> new KnightMoveStrategy()
                .isValidateCanMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트가 이동할 수 없는 위치입니다.");
    }
}
