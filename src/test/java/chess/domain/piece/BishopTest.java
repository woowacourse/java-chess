package chess.domain.piece;

import chess.domain.Team;
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

class BishopTest {
    @ParameterizedTest
    @ValueSource(strings = {"d4", "c5", "d6", "e5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 이동 가능하지 않은 위치라면 예외가 발생한다.")
    void validateIsPossible(String input) {
        Position source = Position.from("d5");
        Bishop bishop = new Bishop(Team.BLACK);
        Assertions.assertThatThrownBy(() -> {
                    bishop.findPath(source, Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"c1:g5:d2,e3,f4", "f1:b5:e2,d3,c4", "c8:f5:d7,e6"}, delimiter = ':')
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPath(String source, String destination, String paths) {
        // given
        Bishop bishop = new Bishop(Team.WHITE);
        List<Position> expected = Arrays.stream(paths.split(","))
                .map(Position::from)
                .collect(Collectors.toList());

        // when
        List<Position> path = bishop.findPath(Position.from(source), Position.from(destination));

        // then
        assertThat(path).isEqualTo(expected);
    }
}