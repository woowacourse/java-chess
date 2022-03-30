package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Rook;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"e5", "c8", "h2", "a3"})
    @DisplayName("Rook의 이동이 불가능한 경우 테스트")
    void validateIsPossible(String input) {
        Rook rook = new Rook(Team.BLACK, Position.from("d4"));
        Assertions.assertThatThrownBy(() -> {
                    rook.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f8", "h6", "b6", "f3"})
    @DisplayName("Rook의 이동이 가능한 경우 테스트")
    void isPossible(String input) {
        Rook rook = new Rook(Team.BLACK, Position.from("f6"));
        rook.findPath(Position.from(input));
    }

    @Test
    @DisplayName("Rook의 이동 경로 리스트 조회")
    void findPath() {
        Rook rook = new Rook(Team.BLACK, Position.from("f6"));
        List<Position> path = rook.findPath(Position.from("f8"));
        assertThat(path).containsExactly(Position.from("f7"));
    }
}