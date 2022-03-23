package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

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
}
