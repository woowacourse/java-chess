package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Queen;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest
    @ValueSource(strings = {"g6", "f8", "b1", "h1"})
    @DisplayName("Queen의 이동이 불가능한 경우 테스트")
    void validateIsPossible(String input) {
        Queen queen = new Queen(Team.BLACK, Position.from("e5"));
        Assertions.assertThatThrownBy(() -> {
                    queen.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f8", "h6", "b6", "f3", "g7", "e7", "e6"})
    @DisplayName("Queen의 이동이 가능한 경우 테스트")
    void isPossible(String input) {
        Queen queen = new Queen(Team.BLACK, Position.from("f6"));
        queen.findPath(Position.from(input));
    }

    @Test
    @DisplayName("Queen의 이동 경로 리스트 조회")
    void findPath() {
        Queen queen = new Queen(Team.BLACK, Position.from("f6"));
        List<Position> path = queen.findPath(Position.from("f3"));
        assertThat(path).containsExactly(Position.from("f5"), Position.from("f4"));
    }
}