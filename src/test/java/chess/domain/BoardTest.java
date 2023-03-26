package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.init(1);
    }

    @Test
    @DisplayName("source 위치의 기물이 target 위치로 이동할 수 없으면 예외가 발생한다.")
    void validateUnmovable() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 4);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 예외가 발생한다.")
    void validateBlocked() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 1);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동 경로에 기물이 있습니다.");
    }

    @Test
    @DisplayName("해당 팀의 현재 점수를 구한다.")
    void calculateScore_noChange() {
        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("해당 팀의 현재 점수를 구한다.")
    void calculateScore() {
        board.move(new Position(2, 2), new Position(4, 2));
        board.move(new Position(7, 3), new Position(5, 3));
        board.move(new Position(4, 2), new Position(5, 3));
        assertAll(
                () -> assertThat(board.calculateScore(Team.WHITE)).isEqualTo(new Score(37)),
                () -> assertThat(board.calculateScore(Team.BLACK)).isEqualTo(new Score(37))
        );
    }

    @Test
    @DisplayName("King이 존재하는지 확인한다.")
    void hasKing() {
        assertThat(board.hasKing(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("King이 존재하지 않는지 확인한다.")
    void hasNoKing() {
        board.move(new Position(1, 7), new Position(3, 8));
        board.move(new Position(3, 8), new Position(5, 7));
        board.move(new Position(5, 7), new Position(7, 6));
        board.move(new Position(7, 6), new Position(6, 4));
        board.move(new Position(6, 4), new Position(8, 5));

        assertThat(board.hasKing(Team.BLACK)).isFalse();
    }
}
