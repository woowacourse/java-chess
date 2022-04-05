package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PiecesUtil.BLACK_PAWN;
import static chess.domain.PiecesUtil.WHITE_PAWN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PawnTest {

    @Test
    @DisplayName("Pawn 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfBlack() {
        Assertions.assertThat(BLACK_PAWN.getSignature()).isEqualTo("P");
    }

    @Test
    @DisplayName("Pawn 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfWhite() {
        Assertions.assertThat(WHITE_PAWN.getSignature()).isEqualTo("p");
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Pawn 이 기물이 없는 대각선으로 1칸 움직일 경우 - 불가능")
    void canNotMoveWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Pawn 이 기물이 없는 대각선으로 2칸 이상 움직일 경우 - 불가능")
    void canNotMoveWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_4", "C:RANK_5:B:RANK_4", }, delimiter = ':')
    @DisplayName("검정 Pawn 이 기물이 있는 앞쪽 대각선으로 1칸 움직일 경우 - 가능")
    void canMoveBlackPawnWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, true)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_6", }, delimiter = ':')
    @DisplayName("흰 Pawn 이 기물이 있는 앞쪽 대각선으로 1칸 움직일 경우 - 가능")
    void canMoveWhitePawnWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(WHITE_PAWN.isCorrectMovement(source, target, true)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_4:C:RANK_3", "H:RANK_5:H:RANK_4", "F:RANK_4:F:RANK_3"}, delimiter = ':')
    @DisplayName("움직인 적이 있는 검정 Pawn 이 비어있는 앞으로 1칸 움직일 경우 - 가능")
    void canMoveOfBlackPawn1StepNotFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_4:C:RANK_3", "H:RANK_5:H:RANK_4", "F:RANK_4:F:RANK_3"}, delimiter = ':')
    @DisplayName("움직인 적이 있는 검정 Pawn 이 기물이 있는 앞으로 1칸 움직일 경우 - 불가능")
    void canNotMoveOfBlackPawn1StepNotFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, true)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_7:C:RANK_5", "H:RANK_7:H:RANK_5", "F:RANK_7:F:RANK_5"}, delimiter = ':')
    @DisplayName("움직인 적이 없는 검정 Pawn 이 비어있는 앞으로 2칸 움직일 경우 - 가능")
    void canMoveOfBlackPawn2StepFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_7:C:RANK_5", "H:RANK_7:H:RANK_5", "F:RANK_7:F:RANK_5"}, delimiter = ':')
    @DisplayName("움직인 적이 없는 검정 Pawn 이 기물이 있는 앞으로 2칸 움직일 경우 - 불가능")
    void canNotMoveOfBlackPawn2StepFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, true)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_4:C:RANK_5", "H:RANK_5:H:RANK_6", "F:RANK_4:F:RANK_5"}, delimiter = ':')
    @DisplayName("움직인 적이 있는 흰 Pawn 이 비어있는 앞으로 1칸 움직일 경우 - 가능")
    void canMoveOfWhitePawn1StepNotFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(WHITE_PAWN.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_4:C:RANK_5", "H:RANK_5:H:RANK_6", "F:RANK_4:F:RANK_5"}, delimiter = ':')
    @DisplayName("움직인 적이 있는 흰 Pawn 이 기물이 있는 앞으로 1칸 움직일 경우 - 불가능")
    void canNotMoveOfWhitePawn1StepNotFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(WHITE_PAWN.isCorrectMovement(source, target, true)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_2:C:RANK_3", "H:RANK_2:H:RANK_3", "F:RANK_2:F:RANK_3"}, delimiter = ':')
    @DisplayName("움직인 적이 없는 흰 Pawn 이 비어있는 앞으로 2칸 움직일 경우 - 가능")
    void canMoveOfWhitePawn2StepFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(WHITE_PAWN.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_2:C:RANK_3", "H:RANK_2:H:RANK_3", "F:RANK_2:F:RANK_3"}, delimiter = ':')
    @DisplayName("움직인 적이 없는 검정 Pawn 이 기물이 있는 앞으로 2칸 움직일 경우 - 불가능")
    void canNotMoveOfWhitePawn2StepFirst(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, true)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_6", "C:RANK_5:D:RANK_5", "C:RANK_5:B:RANK_5"}, delimiter = ':')
    @DisplayName("검정 Pawn 이 좌우, 뒤로 움직일 경우 - 불가능")
    void canNotMoveWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(BLACK_PAWN.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_4", "C:RANK_5:D:RANK_5", "C:RANK_5:B:RANK_5"}, delimiter = ':')
    @DisplayName("흰 Pawn 이 좌우, 뒤로 움직일 경우 - 불가능")
    void canNotMoveWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(WHITE_PAWN.isCorrectMovement(source, target, false)).isFalse();
    }

    @Test
    @DisplayName("Pawn 은 기물을 넘을 수 없다.")
    void canNotJumpOverPieces() {
        assertThat(BLACK_PAWN.canJumpOverPieces()).isFalse();
    }

    @Test
    @DisplayName("Pawn 은 Pawn 이다.")
    void isPawn() {
        assertThat(BLACK_PAWN.isPawn()).isTrue();
    }

    @Test
    @DisplayName("Pawn 은 King 이 아니다.")
    void isNotKing() {
        assertThat(BLACK_PAWN.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 의 점수는 1 이다.")
    void isScore1() {
        assertThat(BLACK_PAWN.score()).isEqualTo(1);
    }
}
