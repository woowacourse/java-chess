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

public class BishopTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:B", "WHITE:b"}, delimiter = ':')
    @DisplayName("Bishop 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Piece bishop = new Piece(color, PieceType.BISHOP, new BishopMovingPattern(), 0);

        assertThat(bishop.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Piece bishop = new Piece(Color.BLACK, PieceType.BISHOP, new BishopMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.F, PositionY.RANK_2);
        assertThat(bishop.isMovable(new HashMap<>(), source, target)).isTrue();
    }

    @Test
    @DisplayName("Bishop 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Piece bishop = new Piece(Color.BLACK, PieceType.BISHOP, new BishopMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.G, PositionY.RANK_6);
        assertThat(bishop.isMovable(new HashMap<>(), source, target)).isFalse();
    }

    @Test
    @DisplayName("Bishop 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Piece bishop = new Piece(Color.BLACK, PieceType.BISHOP, new BishopMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.F, PositionY.RANK_2);
        List<Position> route = bishop.findRoute(source, target);
        assertThat(route).containsExactly(Position.of(PositionX.D, PositionY.RANK_4), Position.of(PositionX.E, PositionY.RANK_3));
    }
}
