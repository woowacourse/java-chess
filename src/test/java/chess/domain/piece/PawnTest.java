package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @ParameterizedTest
    @ValueSource(strings = {"e6", "d7", "f7"})
    @DisplayName("White 팀의 Pawn이 뒤 또는 옆으로 이동하려는 경우 예외 발생 테스트")
    void validateIsPossibleByWhiteTeam(String input) {
        Pawn pawn = new Pawn(Team.WHITE, Position.from("e7"));
        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"e3", "f3", "d3", "e4"})
    @DisplayName("White팀 Pawn의 앞 또는 대각선으로의 이동이 가능한 경우 테스트")
    void isPossibleByWhiteTeam(String input) {
        Pawn pawn = new Pawn(Team.WHITE, Position.from("e2"));
        pawn.findPath(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d4", "b4", "b5", "c5", "d5"})
    @DisplayName("White 팀의 Pawn이 뒤 또는 옆으로 이동하려는 경우 예외 발생 테스트")
    void validateIsPossibleByBlackTeam(String input) {
        Pawn pawn = new Pawn(Team.BLACK, Position.from("c4"));
        Assertions.assertThatThrownBy(() -> {
                    pawn.findPath(Position.from(input));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("해당 위치로 말이 움직일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6", "e5"})
    @DisplayName("Black팀 Pawn의 앞 또는 대각선으로의 이동이 가능한 경우 테스트")
    void isPossibleByBlackTeam(String input) {
        Pawn pawn = new Pawn(Team.BLACK, Position.from("e7"));
        pawn.findPath(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "e6", "f6"})
    @DisplayName("Black팀 Pawn의 이동 경로 리스트 조회")
    void findPathByBlack(String input) {
        Pawn pawn = new Pawn(Team.BLACK, Position.from("e7"));
        List<Position> path = pawn.findPath(Position.from(input));
        assertThat(path).containsExactly(Position.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"d3", "e3", "f3"})
    @DisplayName("White팀 Pawn의 이동 경로 리스트 조회")
    void findPathByWhite(String input) {
        Pawn pawn = new Pawn(Team.WHITE, Position.from("e2"));
        List<Position> path = pawn.findPath(Position.from(input));
        assertThat(path).containsExactly(Position.from(input));
    }
}