package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @ParameterizedTest
    @ValueSource(strings = {"d4", "c5", "d6", "e5"})
    @DisplayName("Bishop의 이동이 불가능한 경우 테스트")
    void validateIsPossible(String input) {
        Bishop bishop = new Bishop(Team.BLACK, Position.from("d5"));
        Assertions.assertThatThrownBy(() -> {
                    bishop.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"h7", "a8", "b1", "h1"})
    @DisplayName("Bishop의 이동이 가능한 경우 테스트")
    void isPossible(String input) {
        Bishop bishop = new Bishop(Team.BLACK, Position.from("e4"));
        bishop.findPath(Position.from(input));
    }

    @Test
    @DisplayName("Bishop의 이동 경로 리스트 조회")
    void findPath() {
        Bishop bishop = new Bishop(Team.BLACK, Position.from("e4"));
        List<Position> path = bishop.findPath(Position.from("h7"));
        assertThat(path).containsExactly(Position.from("f5"), Position.from("g6"));
    }
}