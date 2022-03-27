package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

public class BlankTest {

    @Test
    @DisplayName("Blank 가 움직일 수 있는지 체크하는 경우 예외를 발생시키는지")
    void isNotMovable() {
        Blank blank = new Blank();
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.D, PositionY.RANK_7);
        assertThatThrownBy(() -> blank.isMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Blank 경로를 찾는 경우 예외를 발생시키는지")
    void findRoute() {
        Blank blank = new Blank();
        Position source = new Position(PositionX.C, PositionY.RANK_5);
        Position target = new Position(PositionX.D, PositionY.RANK_7);
        assertThatThrownBy(() -> blank.findRoute(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
