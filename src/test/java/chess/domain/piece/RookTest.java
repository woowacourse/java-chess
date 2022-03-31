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

public class RookTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:R", "WHITE:r"}, delimiter = ':')
    @DisplayName("Rook 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Rook rook = new Rook(color);

        assertThat(rook.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Rook 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Rook rook = new Rook(Color.BLACK);
        Position source = Position.of(Column.C, Row.RANK_5);
        Position target = Position.of(Column.H, Row.RANK_5);
        assertThat(rook.isCorrectMovement(source, target, false)).isTrue();
    }

    @Test
    @DisplayName("Rook 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Rook rook = new Rook(Color.BLACK);
        Position source = Position.of(Column.C, Row.RANK_5);
        Position target = Position.of(Column.E, Row.RANK_6);
        assertThat(rook.isCorrectMovement(source, target, true)).isFalse();
    }
}
