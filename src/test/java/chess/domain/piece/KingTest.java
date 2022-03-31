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
    @ValueSource(strings = {"e6", "a8", "f5", "f7", "h1"})
    @DisplayName("King의 이동이 불가능한 경우 테스트")
    void validateIsPossible(String input) {
        King king = new King(Team.BLACK, Position.from("c4"));
        Assertions.assertThatThrownBy(() -> {
                    king.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"e5", "e3", "d4", "f4", "d5", "d3", "f5", "f3"})
    @DisplayName("King의 이동이 가능한 경우 테스트")
    void isPossible(String input) {
        King king = new King(Team.BLACK, Position.from("e4"));
        king.findPath(Position.from(input));
    }
}