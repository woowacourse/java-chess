package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.Pawn;
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

class PawnTest {
    @ParameterizedTest
    @ValueSource(strings = {"e6", "d7", "f7"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 이동 가능하지 않은 위치라면 예외가 발생한다.")
    void validateIsPossibleByWhiteTeam(String input) {
        Position position = Position.from("e7");
        Pawn pawn = new Pawn(Team.WHITE);

        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(position, Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a7:a5:a6,a5", "c7:c6:c6", "d7:d6:d6", "e7:d6:d6"}, delimiter = ':')
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findBlackPath(String source, String destination, String paths) {
        // given
        Pawn pawn = new Pawn(Team.BLACK);
        List<Position> expected = Arrays.stream(paths.split(","))
                .map(Position::from)
                .collect(Collectors.toList());

        // when
        List<Position> path = pawn.findPath(Position.from(source), Position.from(destination));

        // then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a2:a4:a3,a4", "c2:c3:c3", "d2:c3:c3", "d2:e3:e3"}, delimiter = ':')
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findWhitePath(String source, String destination, String paths) {
        // given
        Pawn pawn = new Pawn(Team.WHITE);
        List<Position> expected = Arrays.stream(paths.split(","))
                .map(Position::from)
                .collect(Collectors.toList());

        // when
        List<Position> path = pawn.findPath(Position.from(source), Position.from(destination));

        // then
        assertThat(path).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"d4", "b4", "b5", "c5", "d5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 이동 가능하지 않은 위치라면 예외가 발생한다.")
    void validateIsPossibleByBlackTeam(String input) {
        Position position = Position.from("c4");
        Pawn pawn = new Pawn(Team.BLACK);
        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(position, Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }
}