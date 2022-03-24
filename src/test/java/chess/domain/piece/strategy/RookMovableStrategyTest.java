package chess.domain.piece.strategy;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookMovableStrategyTest {

    private PieceMovableStrategy rookMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        rookMovableStrategy = new RookMovableStrategy();
        start = new Position('d', '4');
    }

    @ParameterizedTest
    @CsvSource(value = {"d,8,true", "d,7,true", "c,4,true", "c,5,false", "c,6,false"})
    @DisplayName("룩의 빈곳 전진 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Rook(WHITE)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

}
