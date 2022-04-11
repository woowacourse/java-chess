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

class KnightTest {
    @ParameterizedTest
    @ValueSource(strings = {"c5", "d6", "g5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 이동 가능하지 않은 위치라면 예외가 발생한다.")
    void validateIsPossible(String destination) {
        Position source = Position.from("d5");
        Knight knight = new Knight(Team.BLACK);
        Assertions.assertThatThrownBy(() -> {
                    knight.findPath(source, Position.from(destination));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b6", "c7", "e7", "f6", "f4", "e3", "c3", "b4"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPath(String destination) {
        // given
        Position sourcePosition = Position.from("d5");
        Position destinationPosition = Position.from(destination);
        Knight knight = new Knight(Team.BLACK);

        // when
        List<Position> path = knight.findPath(sourcePosition, destinationPosition);

        // then
        assertThat(path.size()).isEqualTo(0);
    }
}