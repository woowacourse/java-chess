package chess.domain.piece.strategy;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnFirstMovableStrategyTest {

    private PieceMovableStrategy whitePawnFirstMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        whitePawnFirstMovableStrategy = new WhitePawnFirstMovableStrategy();
        start = new Position('b', '1');
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2,true", "b,3,true", "b,1,false", "b,4,false", "a,1,false"})
    @DisplayName("폰의 빈곳 전진 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Pawn(WHITE)));

        assertThat(whitePawnFirstMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2,WHITE", "b,3,WHITE", "b,2,BLACK", "b,3,BLACK"})
    @DisplayName("기물이 가로막을 경우의 전진 불가능")
    void cannotMoveToPiecePosition(char col, char row, Color color) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Pawn(WHITE),
                target, new Pawn(color)));

        assertThat(whitePawnFirstMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }
}
