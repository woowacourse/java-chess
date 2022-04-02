package chess.domain.piece;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.KING_WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {
    @ParameterizedTest
    @CsvSource(value = {"D5,true", "F5,true", "F3,true", "D3,true",
            "C6,false", "G6,false", "G2,false", "C2,false"})
    @DisplayName("킹은 대각선으로도 1칸씩 이동한다")
    void movableDiagonal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, KING_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"E5,true", "F4,true", "E3,true", "D4,true",
            "E6,false", "G4,false", "E2,false", "C4,false"})
    @DisplayName("킹은 상하좌우로도 1칸씩 이동한다")
    void movableVerticalOrHorizontal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, KING_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @Test
    @DisplayName("킹은 0점으로 계산된다")
    void getScore() {
        final Piece king = KING_WHITE;
        final double score = king.getScore();
        assertThat(score).isEqualTo(0.0);
    }
}
