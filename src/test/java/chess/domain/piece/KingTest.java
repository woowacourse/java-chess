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

public class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:K", "WHITE:k"}, delimiter = ':')
    @DisplayName("King 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        King king = new King(color);

        assertThat(king.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("King 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        King king = new King(Color.BLACK);
        Position source = Position.of(Column.C, Row.RANK_5);
        Position target = Position.of(Column.D, Row.RANK_6);
        assertThat(king.isCorrectMovement(source, target, false)).isTrue();
    }

    @Test
    @DisplayName("King 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        King king = new King(Color.BLACK);
        Position source = Position.of(Column.C, Row.RANK_5);
        Position target = Position.of(Column.D, Row.RANK_7);
        assertThat(king.isCorrectMovement(source, target, false)).isFalse();
    }
}
