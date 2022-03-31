package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
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
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossible(String input) {
        Position position = Position.from("e5");
        Queen queen = new Queen(Team.BLACK, position);
        Assertions.assertThatThrownBy(() -> {
                    queen.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f8", "h6", "b6", "f3", "g7", "e7", "e6"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void isPossible(String input) {
        Position position = Position.from("f6");
        Queen queen = new Queen(Team.BLACK, position);
        queen.findPath(Position.from(input));
    }

    @Test
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPath() {
        Position position = Position.from("f6");
        Queen queen = new Queen(Team.BLACK, position);
        List<Position> path = queen.findPath(Position.from("f3"));
        assertThat(path).containsExactly(Position.from("f5"), Position.from("f4"));
    }
}