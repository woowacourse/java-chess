package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:K", "WHITE:k"}, delimiter = ':')
    @DisplayName("King 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(PieceColor color, String pieceName) {
        King king = new King(color);

        assertThat(king.signature()).isEqualTo(pieceName);
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("King 이 대각선으로 1칸 움직일 경우 - 가능")
    void canMoveWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        King king = new King(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(king.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("King 이 대각선으로 2칸 이상 움직일 경우 - 불가능")
    void canNotMoveWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        King king = new King(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(king.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_5", "C:RANK_5:C:RANK_4", "C:RANK_5:C:RANK_6", "C:RANK_5:C:RANK_4"},
            delimiter = ':')
    @DisplayName("King 이 상하좌우로 1칸 움직일 경우 - 가능")
    void canMoveWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        King king = new King(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(king.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_2", "C:RANK_5:A:RANK_5", "A:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("King 이 상하좌우로 2칸 이상 움직일 경우 - 불가능")
    void canNotMoveWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        King king = new King(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(king.isCorrectMovement(source, target, false)).isFalse();
    }

    @Test
    @DisplayName("King 은 기물을 넘을 수 없다.")
    void canNotJumpOverPieces() {
        King king = new King(PieceColor.BLACK);

        assertThat(king.canJumpOverPieces()).isFalse();
    }

    @Test
    @DisplayName("King 은 Pawn 이 아니다.")
    void isNotPawn() {
        King king = new King(PieceColor.BLACK);

        assertThat(king.isPawn()).isFalse();
    }

    @Test
    @DisplayName("King 은 King 이다.")
    void isKing() {
        King king = new King(PieceColor.BLACK);

        assertThat(king.isKing()).isTrue();
    }

    @Test
    @DisplayName("King 의 점수는 0 이다.")
    void isScore0() {
        King king = new King(PieceColor.BLACK);

        assertThat(king.score()).isEqualTo(0);
    }
}
