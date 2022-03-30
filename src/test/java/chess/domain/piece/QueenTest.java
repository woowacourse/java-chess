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

public class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:Q", "WHITE:q"}, delimiter = ':')
    @DisplayName("Queen 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Queen queen = new Queen(color);

        assertThat(queen.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Queen 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Queen queen = new Queen(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.H, Row.RANK_5);
        assertThat(queen.isCorrectMovement(source, target)).isTrue();
    }

    @Test
    @DisplayName("Queen 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Queen queen = new Queen(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.H, Row.RANK_7);
        assertThat(queen.isCorrectMovement(source, target)).isFalse();
    }

    @Test
    @DisplayName("Queen 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Queen queen = new Queen(Color.BLACK);
        Position source = new Position(Column.C, Row.RANK_5);
        Position target = new Position(Column.F, Row.RANK_5);
        List<Position> route = queen.findRoute(source, target);
        assertThat(route).containsExactly(new Position(Column.D, Row.RANK_5), new Position(Column.E, Row.RANK_5));
    }
}
