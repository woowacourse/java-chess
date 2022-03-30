package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @ParameterizedTest
    @ValueSource(strings = {"e6", "d7", "f7"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossibleByWhiteTeam(String input) {
        Position position = Position.from("e7");
        Pawn pawn = new Pawn(Team.WHITE, position);
        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"e3", "f3", "d3", "e4"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void isPossibleByWhiteTeam(String input) {
        Position position = Position.from("e2");
        Pawn pawn = new Pawn(Team.WHITE, position);
        pawn.findPath(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d4", "b4", "b5", "c5", "d5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void validateIsPossibleByBlackTeam(String input) {
        Position position = Position.from("c4");
        Pawn pawn = new Pawn(Team.BLACK, position);
        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6", "e5"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void isPossibleByBlackTeam(String input) {
        Position position = Position.from("e7");
        Pawn pawn = new Pawn(Team.BLACK, position);
        pawn.findPath(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPathByBlack(String input) {
        Position position = Position.from("e7");
        Pawn pawn = new Pawn(Team.BLACK, position);
        List<Position> path = pawn.findPath(Position.from(input));
        assertThat(path).containsExactly(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d3", "e3", "f3"})
    @DisplayName("출발 지점과 도착 지점을 입력 후, 경로 리스트를 조회한다.")
    void findPathByWhite(String input) {
        Position position = Position.from("e2");
        Pawn pawn = new Pawn(Team.WHITE, position);
        List<Position> path = pawn.findPath(Position.from(input));
        assertThat(path).containsExactly(Position.from(input));
    }
}