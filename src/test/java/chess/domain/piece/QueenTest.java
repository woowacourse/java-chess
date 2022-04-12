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

public class QueenTest {
    @ParameterizedTest
    @CsvSource(value = {"BLACK:Q", "WHITE:q"}, delimiter = ':')
    @DisplayName("Queen 의 색깔에 맞는 이름을 반환하는지")
    void checkNameByColor(Color color, String pieceName) {
        Piece queen = new Piece(color, PieceType.QUEEN, new QueenMovingPattern(), 0);

        assertThat(queen.signature()).isEqualTo(pieceName);
    }

    @Test
    @DisplayName("Queen 이 움직일 수 있는 위치이면 true를 반환하는지")
    void isMovable() {
        Piece queen = new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.H, PositionY.RANK_5);
        assertThat(queen.isMovable(new HashMap<>(), source, target)).isTrue();
    }

    @Test
    @DisplayName("Queen 이 움직일 수 없는 위치이면 false를 반환하는지")
    void isNotMovable() {
        Piece queen = new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.H, PositionY.RANK_7);
        assertThat(queen.isMovable(new HashMap<>(), source, target)).isFalse();
    }

    @Test
    @DisplayName("Queen 이 움직이는 경로를 얻어오는지")
    void findRoute() {
        Piece queen = new Piece(Color.BLACK, PieceType.QUEEN, new QueenMovingPattern(), 0);
        Position source = Position.of(PositionX.C, PositionY.RANK_5);
        Position target = Position.of(PositionX.F, PositionY.RANK_5);
        List<Position> route = queen.findRoute(source, target);
        assertThat(route).containsExactly(Position.of(PositionX.D, PositionY.RANK_5), Position.of(PositionX.E, PositionY.RANK_5));
    }
}
