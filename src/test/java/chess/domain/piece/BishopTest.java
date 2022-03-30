package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import chess.domain.position.Column;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.Row;

public class BishopTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:B", "WHITE:b"}, delimiter = ':')
    @DisplayName("Bishop 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Bishop bishop = new Bishop(color);

        assertThat(bishop.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.F, Row.RANK_2);
        assertThat(bishop.isCorrectMovement(source, target)).isTrue();
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.G, Row.RANK_6);
        assertThat(bishop.isCorrectMovement(source, target)).isFalse();
    }

    @Test
    @DisplayName("Bishop 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Bishop bishop = new Bishop(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.F, Row.RANK_2);
        List<Position> route = bishop.findRoute(source, target);
        assertThat(route).containsExactly(new Position(Column.D, Row.RANK_4), new Position(Column.E, Row.RANK_3));
    }
}
