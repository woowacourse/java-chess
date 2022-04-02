package chess.domain.piece;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.E6;
import static chess.domain.piece.Fixture.E7;
import static chess.domain.piece.Fixture.E8;
import static chess.domain.piece.Fixture.PAWN_BLACK;
import static chess.domain.piece.Fixture.PAWN_WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @Test
    @DisplayName("폰은 첫 수에만 2칸 전진 가능하고 그 외에는 1칸 전진한다")
    void advanceTwoRanksForFirstMoveOnlyOtherwiseOneRank() {
        final Board board = BoardFixture.of(E4, PAWN_WHITE);
        final boolean firstMove = board.move(E4, E6);
        final boolean secondMove = board.move(E6, E8);
        final boolean thirdMove = board.move(E6, E7);
        assertAll(
                () -> assertThat(firstMove).isTrue(),
                () -> assertThat(secondMove).isFalse(),
                () -> assertThat(thirdMove).isTrue()
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"F4,false", "F3,false", "E3,false", "D3,false", "D4,false"})
    @DisplayName("폰은 옆으로도 뒤로도 이동할 수 없다")
    void notMovableBackwardOrHorizontal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"D5,true", "F5,true"})
    @DisplayName("폰은 대각 전방에 상대 기물이 있을 때 예외적으로 대각선 1칸 전진이 가능하다")
    void advanceDiagonalWhenEnemyIsPresent(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE, Position.from(to), PAWN_BLACK);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"D5,false", "F5,false"})
    @DisplayName("폰은 대각 전방에 상대 기물이 없으면 대각 전진이 불가하다")
    void failToAdvanceDiagonalWhenEnemyIsAbsent(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @Test
    @DisplayName("폰은 1점으로 계산된다")
    void getScore() {
        final Piece pawn = PAWN_WHITE;
        final double score = pawn.getScore();
        assertThat(score).isEqualTo(1.0);
    }
}
