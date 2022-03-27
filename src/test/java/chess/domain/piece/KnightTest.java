package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"BLACK:N", "WHITE:n"}, delimiter = ':')
    @DisplayName("Knight 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Knight knight = new Knight(color);

        assertThat(knight.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Knight 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Knight knight = new Knight(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.A, PositionY.RANK_6);
        assertThat(knight.isMovable(source, target)).isTrue();
    }

    @Test
    @DisplayName("Knight 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Knight knight = new Knight(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.F, PositionY.RANK_2);
        assertThat(knight.isMovable(source, target)).isFalse();
    }

    @Test
    @DisplayName("Knight 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Knight knight = new Knight(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.E, PositionY.RANK_4);
        List<Position> route = knight.findRoute(source, target);
        assertThat(route).isEmpty();
    }
}
