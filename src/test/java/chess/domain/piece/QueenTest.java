package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @ParameterizedTest
    @ValueSource(strings = {"b7", "a7", "f7", "h7"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 이동 가능하지 않은 위치라면 예외가 발생한다.")
    void validateIsPossible(String input) {
        Position source = Position.from("d8");
        Queen Queen = new Queen(Team.BLACK);
        Assertions.assertThatThrownBy(() -> {
                    Queen.findPath(source, Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"d8:g5:e7,f6", "d8:h8:e8,f8,g8", "d8:a5:c7,b6"}, delimiter = ':')
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPath(String source, String destination, String paths) {
        // given
        Queen Queen = new Queen(Team.WHITE);
        List<Position> expected = Arrays.stream(paths.split(","))
                .map(Position::from)
                .collect(Collectors.toList());

        // when
        List<Position> path = Queen.findPath(Position.from(source), Position.from(destination));

        // then
        assertThat(path).isEqualTo(expected);
    }
}