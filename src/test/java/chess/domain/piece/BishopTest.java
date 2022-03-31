package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:B", "WHITE:b"}, delimiter = ':')
    @DisplayName("Bishop 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Bishop bishop = new Bishop(color);

        assertThat(bishop.signature()).isEqualTo(pieceName);
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:B:RANK_6", "C:RANK_5:D:RANK_6", "C:RANK_5:B:RANK_4", "C:RANK_5:D:RANK_4"},
            delimiter = ':')
    @DisplayName("Bishop 이 대각선으로 1칸 움직일 경우 - 가능")
    void isMovableWithDiagonal1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(bishop.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:F:RANK_2", "C:RANK_5:E:RANK_3", "C:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Bishop 이 대각선으로 2칸 이상 움직일 경우 - 가능")
    void isMovableWithDiagonal(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(bishop.isCorrectMovement(source, target, false)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:D:RANK_5", "C:RANK_5:C:RANK_4", "C:RANK_5:C:RANK_6", "C:RANK_5:C:RANK_4"},
            delimiter = ':')
    @DisplayName("Bishop 이 상하좌우로 1칸 움직일 경우 - 불가능")
    void isNotMovableWithStraight1Step(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(bishop.isCorrectMovement(source, target, false)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"C:RANK_5:C:RANK_2", "C:RANK_5:A:RANK_5", "A:RANK_5:A:RANK_3"}, delimiter = ':')
    @DisplayName("Bishop 이 상하좌우로 2칸 이상 움직일 경우 - 불가능")
    void isNotMovableWithStraight(Column sourceColumn, Row sourceRow, Column targetColumn, Row targetRow) {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = Position.of(sourceColumn, sourceRow);
        Position target = Position.of(targetColumn, targetRow);

        assertThat(bishop.isCorrectMovement(source, target, false)).isFalse();
    }

    @Test
    @DisplayName("Bishop 은 기물을 넘을 수 없다.")
    void canNotJumpOverPieces() {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.canJumpOverPieces()).isFalse();
    }

    @Test
    @DisplayName("Bishop 은 Pawn 이 아니다.")
    void isNotPawn() {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Bishop 은 King 이 아니다.")
    void isNotKing() {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.isKing()).isFalse();
    }

    @Test
    @DisplayName("Bishop 의 점수는 3 이다.")
    void isScore3() {
        Bishop bishop = new Bishop(Color.BLACK);

        assertThat(bishop.score()).isEqualTo(3);
    }
}
