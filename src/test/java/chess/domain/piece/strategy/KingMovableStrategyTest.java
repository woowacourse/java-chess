package chess.domain.piece.strategy;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Color;
import chess.domain.piece.King;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingMovableStrategyTest {

    private PieceMovableStrategy kingMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        kingMovableStrategy = new KingMovableStrategy();
        start = new Position('a', '1');
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,true", "b,1,true", "b,2,true", "b,3,false", "c,3,false"})
    @DisplayName("킹의 빈곳 이동 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new King(WHITE)));

        assertThat(kingMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("기물이 존재할 경우의 이동 가능 여부 확인")
    void isMovableToDifferentPiecePosition(Color color, boolean expected) {
        Position target = new Position('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new King(WHITE),
                target, new King(color)
        ));

        assertThat(kingMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }
}
