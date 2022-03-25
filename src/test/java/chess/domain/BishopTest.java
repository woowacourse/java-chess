package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {
    @ParameterizedTest
    @ValueSource(strings = {"d4", "c5", "d6", "e5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossible(String input) {
        Position position = Position.from("d5");
        Bishop bishop = new Bishop(Team.BLACK, position);
        Assertions.assertThatThrownBy(() -> {
                    bishop.validateIsPossible(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"h7", "a8", "b1", "h1"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void isPossible(String input) {
        Position position = Position.from("e4");
        Bishop bishop = new Bishop(Team.BLACK, position);
        bishop.validateIsPossible(Position.from(input));
    }
}