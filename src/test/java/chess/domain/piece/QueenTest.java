package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:Q", "WHITE:q"}, delimiter = ':')
    @DisplayName("Queen 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(PieceColor color, String pieceName) {
        Queen queen = new Queen(color);

        assertThat(queen.signature()).isEqualTo(pieceName);
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Queen 이 대각선으로 1칸 움직일 경우 - 가능")
    void canMoveWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Queen queen = new Queen(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(queen.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Queen 이 대각선으로 2칸 이상 움직일 경우 - 가능")
    void canMoveWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Queen queen = new Queen(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(queen.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_5", "C:RANK_5:C:RANK_4", "C:RANK_5:C:RANK_6", "C:RANK_5:C:RANK_4"},
            delimiter = ':')
    @DisplayName("Queen 이 상하좌우로 1칸 움직일 경우 - 가능")
    void canMoveWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Queen queen = new Queen(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(queen.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_2", "C:RANK_5:A:RANK_5", "A:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Queen 이 상하좌우로 2칸 이상 움직일 경우 - 가능")
    void canMoveWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Queen queen = new Queen(PieceColor.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(queen.isCorrectMovement(source, target, false)).isTrue();
    }

    @Test
    @DisplayName("Queen 은 기물을 넘을 수 없다.")
    void canNotJumpOverPieces() {
        Queen queen = new Queen(PieceColor.BLACK);

        assertThat(queen.canJumpOverPieces()).isFalse();
    }

    @Test
    @DisplayName("Queen 은 Pawn 이 아니다.")
    void isNotPawn() {
        Queen queen = new Queen(PieceColor.BLACK);

        assertThat(queen.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Queen 은 King 이 아니다.")
    void isNotKing() {
        Queen queen = new Queen(PieceColor.BLACK);

        assertThat(queen.isKing()).isFalse();
    }

    @Test
    @DisplayName("Queen 의 점수는 9 이다.")
    void isScore9() {
        Queen queen = new Queen(PieceColor.BLACK);

        assertThat(queen.score()).isEqualTo(9);
    }
}
