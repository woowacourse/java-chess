package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.Knight;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @ParameterizedTest
    @ValueSource(strings = {"c7", "a7", "a6", "h6"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossible(String input) {
        Position source = Position.from("e8");
        King King = new King(Team.BLACK);
        Assertions.assertThatThrownBy(() -> King.findPath(source, Position.from(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"c8", "d7", "e7", "f7", "f8"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPath(String destination) {
        // given
        Position sourcePosition = Position.from("e8");
        Position destinationPosition = Position.from(destination);
        King King = new King(Team.BLACK);

        // when
        List<Position> path = King.findPath(sourcePosition, destinationPosition);

        // then
        assertThat(path.size()).isEqualTo(0);
    }
}