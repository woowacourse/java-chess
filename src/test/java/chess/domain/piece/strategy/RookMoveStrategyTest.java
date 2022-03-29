package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
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
    void canValidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertDoesNotThrow(() -> new RookMoveStrategy()
                .isValidateCanMove(Team.WHITE, new Rook(Team.WHITE),
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                )
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "A,ONE,B,THREE",
            "A,THREE,B,FOUR"
    })
    @DisplayName("룩이 갈 수 위치가 아니면 에러가 발생해야 한다.")
    void canInvalidMove(Column columnA, Rank rankA, Column columnB, Rank rankB) {
        assertThatThrownBy(() -> new RookMoveStrategy()
                .isValidateCanMove(Team.WHITE, new Rook(Team.WHITE),
                        new Position(columnA, rankA),
                        new Position(columnB, rankB)
                ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩이 이동할 수 없는 위치입니다.");
    }
}
