package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PiecesUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {

    @Test
    @DisplayName("Rook 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfBlack() {
        Assertions.assertThat(BLACK_ROOK.getSignature()).isEqualTo("R");
    }

    @Test
    @DisplayName("Rook 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfWhite() {
        Assertions.assertThat(WHITE_ROOK.getSignature()).isEqualTo("r");
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Rook 이 대각선으로 1칸 움직일 경우 - 불가능")
    void canMoveWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_ROOK.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Rook 이 대각선으로 2칸 이상 움직일 경우 - 불가능")
    void canNotMoveWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_ROOK.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_5", "C:RANK_5:C:RANK_4", "C:RANK_5:C:RANK_6", "C:RANK_5:C:RANK_4"},
            delimiter = ':')
    @DisplayName("Rook 이 상하좌우로 1칸 움직일 경우 - 가능")
    void canNotMoveWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_ROOK.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_2", "C:RANK_5:A:RANK_5", "A:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Rook 이 상하좌우로 2칸 이상 움직일 경우 - 가능")
    void canMoveWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_ROOK.isCorrectMovement(source, target, false)).isTrue();
    }

    @Test
    @DisplayName("Rook 은 기물을 넘을 수 없다.")
    void canNotJumpOverPieces() {
        assertThat(BLACK_ROOK.canJumpOverPieces()).isFalse();
    }

    @Test
    @DisplayName("Rook 은 Pawn 이 아니다.")
    void isNotPawn() {
        assertThat(BLACK_ROOK.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Rook 은 King 이 아니다.")
    void isNotKing() {
        assertThat(BLACK_ROOK.isKing()).isFalse();
    }

    @Test
    @DisplayName("Rook 의 점수는 5 이다.")
    void isScore5() {
        assertThat(BLACK_ROOK.score()).isEqualTo(5);
    }
}
