package chess.domain.piece;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.QUEEN_WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {
    @ParameterizedTest
    @CsvSource(value = {"C6,true", "G6,true", "G2,true", "C2,true"})
    @DisplayName("퀸은 대각선으로도 이동한다")
    void movableDiagonal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, QUEEN_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"E6,true", "G4,true", "E2,true", "C4,true"})
    @DisplayName("퀸은 상하좌우로도 이동한다")
    void movableVerticalOrHorizontal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, QUEEN_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"C6,D5,false", "G6,F5,false", "G2,F3,false", "C2,D3,false",
            "E6,E5,false", "G4,F4,false", "E2,E3,false", "C4,D4,false"})
    @DisplayName("퀸은 이동 경로에 기물이 존재하면 이동할 수 없다")
    void notMovableForPieceOnTheWay(String to, String anotherPiece, boolean expected) {
        final Board board = BoardFixture.of(E4, QUEEN_WHITE, Position.from(anotherPiece), QUEEN_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @Test
    @DisplayName("퀸은 9점으로 계산된다")
    void getScore() {
        final Piece queen = QUEEN_WHITE;
        final double score = queen.getScore();
        assertThat(score).isEqualTo(9.0);
    }
}
