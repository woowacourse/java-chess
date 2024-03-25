package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonal() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(7, 7))))
                .containsExactly(Position.of(5, 5), Position.of(6, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionDiagonalMinus() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(1, 1))))
                .containsExactly(Position.of(3, 3), Position.of(2, 2));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLine() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(4, 7))))
                .containsExactly(Position.of(4, 5), Position.of(4, 6));
    }

    @DisplayName("두 위치 사이의 퀸이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionLineMinus() {
        assertThat(new Queen(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(1, 4))))
                .containsExactly(Position.of(3, 4), Position.of(2, 4));
    }
}
