package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookMoveStrategyTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,A,SEVEN",
            "A,THREE,C,THREE",
            "C,THREE,A,THREE"
    })
    @DisplayName("룩이 갈 수 있는 위치 중 하나여야 한다.")
    void canValidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertDoesNotThrow(() -> new RookMoveStrategy()
                .isValidateCanMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,THREE",
            "A,THREE,B,FOUR"
    })
    @DisplayName("룩이 갈 수 위치가 아니면 에러가 발생해야 한다.")
    void canInvalidMove(File fileA, Rank rankA, File fileB, Rank rankB) {
        assertThatThrownBy(() -> new RookMoveStrategy()
                .isValidateCanMove(Color.WHITE,
                        new Position(fileA, rankA),
                        new Position(fileB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩이 이동할 수 없는 위치입니다.");
    }
}
