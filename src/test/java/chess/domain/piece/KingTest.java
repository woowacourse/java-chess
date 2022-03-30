package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Knight;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"d4", "b3", "f5", "f7", "h1"})
    @DisplayName("King의 이동이 불가능한 경우 테스트")
    void validateIsPossible(String input) {
        Knight knight = new Knight(Team.BLACK, Position.from("c4"));
        Assertions.assertThatThrownBy(() -> {
                    knight.validateIsPossible(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f6", "d6", "f2", "d2", "c3", "g3", "g5", "c5"})
    @DisplayName("King의 이동 경로 리스트 조회")
    void isPossible(String input) {
        Knight knight = new Knight(Team.BLACK, Position.from("e4"));
        knight.validateIsPossible(Position.from(input));
    }
}