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

public class KnightTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:N", "WHITE:n"}, delimiter = ':')
    @DisplayName("Knight 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Piece knight = new Piece(color, PieceType.KNIGHT, new KnightMovingPattern(), 0);

        assertThat(knight.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Knight 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Piece knight = new Piece(Color.BLACK, PieceType.KNIGHT, new KnightMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.A, PositionY.RANK_6);
        assertThat(knight.isMovable(new HashMap<>(), source, target)).isTrue();
    }

    @Test
    @DisplayName("Knight 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Piece knight = new Piece(Color.BLACK, PieceType.KNIGHT, new KnightMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.F, PositionY.RANK_2);
        assertThat(knight.isMovable(new HashMap<>(), source, target)).isFalse();
    }

    @Test
    @DisplayName("Knight 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Piece knight = new Piece(Color.BLACK, PieceType.KNIGHT, new KnightMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.E, PositionY.RANK_4);
        List<Position> route = knight.findRoute(source, target);
        assertThat(route).isEmpty();
    }
}
