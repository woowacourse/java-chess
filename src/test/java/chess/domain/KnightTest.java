package chess.domain;

import chess.domain.piece.Knight;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"d4", "b3", "f5", "f7", "h1"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossible(String input) {
        Position position = Position.from("c4");
        Knight knight = new Knight(Team.BLACK, position);
        Assertions.assertThatThrownBy(() -> {
                    knight.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f6", "d6", "f2", "d2", "c3", "g3", "g5", "c5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void isPossible(String input) {
        Position position = Position.from("e4");
        Knight knight = new Knight(Team.BLACK, position);
        knight.findPath(Position.from(input));
    }
}