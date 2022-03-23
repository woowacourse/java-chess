package chess.domain.piece.strategy;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Knight;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightMovableStrategyTest {

    private PieceMovableStrategy knightMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        knightMovableStrategy = new KnightMovableStrategy();
        start = new Position('a', '1');
    }

    @ParameterizedTest
    @CsvSource(value = {"c,2,true", "b,3,true", "b,2,false", "b,4,false", "c,5,false"})
    @DisplayName("나이트의 빈곳 이동 가능 여부 확인")
    void exceptionFindRoute(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Knight(WHITE)));

        assertThat(knightMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }
}
