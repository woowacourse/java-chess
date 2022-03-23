package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.D, PositionY.RANK_6);
        assertThat(king.isMovable(source, target)).isTrue();
    }

    @Test
    @DisplayName("King 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        King king = new King(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.D, PositionY.RANK_7);
        assertThat(king.isMovable(source, target)).isFalse();
    }

    @Test
    @DisplayName("King 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        King king = new King(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.C, PositionY.RANK_4);
        List<Position> route = king.findRoute(source, target);
        assertThat(route).isEmpty();
    }
}
