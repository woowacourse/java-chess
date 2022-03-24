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

public class BlackPawnMovableStrategyTest {

    private PieceMovableStrategy blackPawnMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        blackPawnMovableStrategy = new BlackPawnMovableStrategy();
        start = new Position('b', '8');
    }

    @ParameterizedTest
    @CsvSource(value = {"b,7,true", "b,6,false", "b,8,false", "a,8,false", "a,6,false"})
    @DisplayName("폰의 빈곳 전진 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Pawn(WHITE)));

        assertThat(blackPawnMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,6,WHITE", "b,6,BLACK"})
    @DisplayName("기물이 가로막을 경우의 전진 불가능")
    void cannotMoveToPiecePosition(char col, char row, Color color) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Pawn(WHITE),
                target, new Pawn(color)));

        assertThat(blackPawnMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"a,7,BLACK,true", "c,7,BLACK,true", "a,7,WHITE,false", "c,7,WHITE,false"})
    @DisplayName("대각선 방향에 적이 있으면 전진 가능")
    void canMoveToEnemyPiecePosition(char col, char row, Color color, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Pawn(WHITE),
                target, new Pawn(color)));

        assertThat(blackPawnMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }
}
