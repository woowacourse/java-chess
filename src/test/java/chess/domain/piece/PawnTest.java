package chess.domain.piece;

import static chess.domain.piece.Fixture.A7;
import static chess.domain.piece.Fixture.E2;
import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.PAWN_BLACK;
import static chess.domain.piece.Fixture.PAWN_WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest
    @CsvSource(value = {"A2,A4,WHITE", "B2,B4,WHITE", "C2,C4,WHITE", "D2,D4,WHITE", "E2,E4,WHITE", "F2,F4,WHITE",
            "G2,G4,WHITE", "H2,H4,WHITE"})
    @DisplayName("백폰은 랭크2에서만 2칸 전진 가능하다")
    void advanceTwoRanksForFirstMoveWhite(String from, String to, Color color) {
        final Position fromPosition = Position.from(from);
        final Board board = BoardFixture.of(fromPosition, new Pawn(color));
        final MoveResult moveResult = board.move(fromPosition, Position.from(to));
        assertThat(moveResult).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest
    @CsvSource(value = {"A7,A5,BLACK", "B7,B5,BLACK", "C7,C5,BLACK", "D7,D5,BLACK", "E7,E5,BLACK",
            "F7,F5,BLACK", "G7,G5,BLACK", "H7,H5,BLACK"})
    @DisplayName("흑폰은 랭크7에서만 2칸 전진 가능하다")
    void advanceTwoRanksForFirstMoveBlack(String from, String to, Color color) {
        final Position fromPosition = Position.from(from);
        final Board board = BoardFixture.of(E2, PAWN_WHITE, fromPosition, new Pawn(color));
        board.move(E2, E4);
        final MoveResult moveResult = board.move(fromPosition, Position.from(to));
        assertThat(moveResult).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2,A4", "B2,B4", "C2,C4", "D2,D4", "E2,E4", "F2,F4", "G2,G4", "H7,H5",
            "A7,A5", "B7,B5", "C7,C5", "D7,D5", "E7,E5", "F7,F5", "G7,G5", "H7,H5"})
    @DisplayName("폰은 첫 수에만 2칸 전진 가능하고 그 외에는 1칸 전진한다")
    void advanceTwoRanksAfterFirstMoveShouldFail() {
        final Board board = BoardFixture.of(E2, PAWN_WHITE, A7, PAWN_BLACK);
        final MoveResult moveResult = board.move(E2, E4);
        assertThat(moveResult).isEqualTo(MoveResult.SUCCESS);
    }

    @ParameterizedTest
    @CsvSource(value = {"F4", "F3", "E3", "D3", "D4"})
    @DisplayName("폰은 옆으로도 뒤로도 이동할 수 없다")
    void notMovableBackwardOrHorizontal(String to) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE);
        assertThatThrownBy(() -> board.move(E4, Position.from(to)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰 이동에 실패했습니다");
    }

    @ParameterizedTest
    @CsvSource(value = {"D5,SUCCESS", "F5,SUCCESS"})
    @DisplayName("폰은 대각 전방에 상대 기물이 있을 때 예외적으로 대각선 1칸 전진이 가능하다")
    void advanceDiagonalWhenEnemyIsPresent(String to, MoveResult expected) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE, Position.from(to), PAWN_BLACK);
        final MoveResult move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"D5", "F5"})
    @DisplayName("폰은 대각 전방에 상대 기물이 없으면 대각 전진이 불가하다")
    void failToAdvanceDiagonalWhenEnemyIsAbsent(String to) {
        final Board board = BoardFixture.of(E4, PAWN_WHITE);
        assertThatThrownBy(() -> board.move(E4, Position.from(to)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("폰 이동에 실패했습니다");
    }

    @Test
    @DisplayName("폰은 1점으로 계산된다")
    void getScore() {
        final double score = PAWN_WHITE.getScore();
        assertThat(score).isEqualTo(1.0);
    }
}
