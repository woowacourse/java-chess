package chess.domain.piece;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.KING_WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource(value = {"E5,SUCCESS", "F4,SUCCESS", "E3,SUCCESS", "D4,SUCCESS",
            "D5,SUCCESS", "F5,SUCCESS", "F3,SUCCESS", "D3,SUCCESS"})
    @DisplayName("킹은 모든 방향으로 1칸씩 이동 가능하다")
    void movableDiagonal(String to, MoveResult expected) {
        final Board board = BoardFixture.of(E4, KING_WHITE);
        final MoveResult move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"E6", "G4", "E2", "C4", "C6", "G6", "G2", "C2"})
    @DisplayName("킹은 2칸 이상 이동 불가하다")
    void movableVerticalOrHorizontal(String to) {
        final Board board = BoardFixture.of(E4, KING_WHITE);
        assertThatThrownBy(() -> board.move(E4, Position.from(to)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("King의 이동 범위를 초과했습니다");
    }

    @Test
    @DisplayName("킹은 0점으로 계산된다")
    void getScore() {
        final double score = KING_WHITE.getScore();
        assertThat(score).isEqualTo(0.0);
    }
}
