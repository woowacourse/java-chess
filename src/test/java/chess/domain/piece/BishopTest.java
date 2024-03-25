package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    @DisplayName("비숍은 대각선이 아닌 경우, 예외가 발생한다.")
    @Test
    void bishopMoveOverDiagonalLine() {
        assertThatThrownBy(() -> new Bishop(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(1, 4))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없습니다.");
    }

    @DisplayName("두 위치 사이의 비숍이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new Bishop(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(7, 7))))
                .containsExactly(Position.of(5, 5), Position.of(6, 6));
    }

    @DisplayName("두 위치 사이의 비숍이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionMinus() {
        assertThat(new Bishop(Team.WHITE)
                .findBetweenPositions(new Positions(
                        Position.of(4, 4),
                        Position.of(1, 1))))
                .containsExactly(Position.of(3, 3), Position.of(2, 2));
    }
}
