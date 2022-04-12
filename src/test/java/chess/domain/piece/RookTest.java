package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:R", "WHITE:r"}, delimiter = ':')
    @DisplayName("Rook 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Piece rook = new Piece(color, PieceType.ROOK, new RookMovingPattern(), 0);

        assertThat(rook.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Rook 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Piece rook = new Piece(Color.BLACK, PieceType.ROOK, new RookMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.H, PositionY.RANK_5);
        assertThat(rook.isMovable(new HashMap<>(), source, target)).isTrue();
    }

    @Test
    @DisplayName("Rook 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Piece rook = new Piece(Color.BLACK, PieceType.ROOK, new RookMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.H, PositionY.RANK_8);
        assertThat(rook.isMovable(new HashMap<>(), source, target)).isFalse();
    }

    @Test
    @DisplayName("Rook 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Piece rook = new Piece(Color.BLACK, PieceType.ROOK, new RookMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.C, PositionY.RANK_8);
        List<Position> route = rook.findRoute(source, target);
        assertThat(route).containsExactly(Position.of(PositionX.C, PositionY.RANK_6), Position.of(PositionX.C, PositionY.RANK_7));
    }
}
