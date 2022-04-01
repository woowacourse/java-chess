package chess.domain.piece;

import static chess.domain.piece.Fixture.BISHOP_WHITE;
import static chess.domain.piece.Fixture.E4;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @ParameterizedTest
    @CsvSource(value = {"C6,true", "G6,true", "G2,true", "C2,true"})
    @DisplayName("비숍은 대각선으로 이동한다")
    void movableDiagonal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, BISHOP_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"E6,false", "G4,false", "E2,false", "C4,false"})
    @DisplayName("비숍은 상하좌우로 이동할 수 없다")
    void notMovableVerticalOrHorizontal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, BISHOP_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"C6,D5,false", "G6,F5,false", "G2,F3,false", "C2,D3,false"})
    @DisplayName("비숍은 이동 경로에 기물이 존재하면 이동할 수 없다")
    void notMovableForPieceOnTheWay(String to, String anotherPiece, boolean expected) {
        final Board board = BoardFixture.of(E4, BISHOP_WHITE, Position.from(anotherPiece), BISHOP_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @Test
    @DisplayName("비숍은 3점으로 계산된다")
    void getScore() {
        final Bishop bishop = BISHOP_WHITE;
        final double score = bishop.getScore();
        assertThat(score).isEqualTo(3.0);
    }
}
