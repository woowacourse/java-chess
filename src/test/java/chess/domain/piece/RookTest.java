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
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.H, PositionY.RANK_5);
        assertThat(rook.isMovable(source, target)).isTrue();
    }

    @Test
    @DisplayName("Rook 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Rook rook = new Rook(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.H, PositionY.RANK_8);
        assertThat(rook.isMovable(source, target)).isFalse();
    }

    @Test
    @DisplayName("Rook 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Rook rook = new Rook(Color.BLACK);
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.C, PositionY.RANK_8);
        List<Position> route = rook.findRoute(source, target);
        assertThat(route).containsExactly(new Position(PositionX.C, PositionY.RANK_6), new Position(PositionX.C, PositionY.RANK_7));
    }
}
