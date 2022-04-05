package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PiecesUtil.*;
import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    @DisplayName("Knight 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfBlack() {
        assertThat(BLACK_KNIGHT.getSignature()).isEqualTo("N");
    }

    @Test
    @DisplayName("Knight 의 색깔에 맞는 이름을 반환하는지")
    void signatureOfWhite() {
        assertThat(WHITE_KNIGHT.getSignature()).isEqualTo("n");
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_7", "C:RANK_5:D:RANK_7", "C:RANK_5:A:RANK_6", "C:RANK_5:E:RANK_6",
            "C:RANK_5:A:RANK_4", "C:RANK_5:E:RANK_4", "C:RANK_5:B:RANK_3", "C:RANK_5:D:RANK_3",
            "F:RANK_5:D:RANK_6", "G:RANK_5:H:RANK_3", "B:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Knight 가 L 자로 움직이는 경우 - 가능")
    void canMove(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);
        assertThat(BLACK_KNIGHT.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Knight 가 대각선으로 1칸 움직일 경우 - 불가능")
    void canNotMoveWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);
        assertThat(BLACK_KNIGHT.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Knight 가 대각선으로 2칸 이상 움직일 경우 - 불가능")
    void canMoveWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);
        assertThat(BLACK_KNIGHT.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_5", "C:RANK_5:C:RANK_4", "C:RANK_5:C:RANK_6", "C:RANK_5:C:RANK_4"},
            delimiter = ':')
    @DisplayName("Knight 가 상하좌우로 1칸 움직일 경우 - 불가능")
    void canNotMoveWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);
        assertThat(BLACK_KNIGHT.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_2", "C:RANK_5:A:RANK_5", "A:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Knight 가 상하좌우로 2칸 이상 움직일 경우 - 불가능")
    void canNotMoveWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);
        assertThat(BLACK_KNIGHT.isCorrectMovement(source, target, false)).isFalse();
    }

    @Test
    @DisplayName("Knight 은 기물을 넘을 수 있다.")
    void canNotJumpOverPieces() {
        assertThat(BLACK_KNIGHT.canJumpOverPieces()).isTrue();
    }

    @Test
    @DisplayName("Knight 은 Pawn 이 아니다.")
    void isNotPawn() {
        assertThat(BLACK_KNIGHT.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Knight 은 King 이 아니다.")
    void isNotKing() {
        assertThat(BLACK_KNIGHT.isKing()).isFalse();
    }

    @Test
    @DisplayName("Knight 의 점수는 2.5 이다.")
    void isScore2_5() {
        assertThat(BLACK_KNIGHT.score()).isEqualTo(2.5);
    }
}
