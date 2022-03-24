package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.pawn.BlackFirstPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.WhiteFirstPawn;
import chess.domain.piece.pawn.WhitePawn;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WhitePawnMovableStrategyTest {

    private PieceMovableStrategy whitePawnMovableStrategy;
    private Position source;

    @BeforeEach
    void setUp() {
        whitePawnMovableStrategy = new WhitePawnMovableStrategy();
        source = new Position('b', '1');
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2,true", "b,3,false", "b,1,false", "a,1,false", "a,2,false"})
    @DisplayName("폰의 빈곳 전진 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new WhitePawn()));

        assertThat(whitePawnMovableStrategy.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("cannotMoveToPiecePosition")
    @DisplayName("기물이 가로막을 경우의 전진 불가능")
    void cannotMoveToPiecePosition(Position target, Piece piece) {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new WhitePawn(),
                target, piece));

        assertThat(whitePawnMovableStrategy.isMovable(source, target, chessBoard)).isFalse();
    }

    private static Stream<Arguments> cannotMoveToPiecePosition() {
        return Stream.of(
                Arguments.of(new Position('b', '2'), new WhiteFirstPawn()),
                Arguments.of(new Position('b', '2'), new BlackFirstPawn())
        );
    }

    @ParameterizedTest
    @MethodSource("canMoveToEnemyPiecePosition")
    @DisplayName("대각선 방향에 적이 있으면 전진 가능")
    void canMoveToEnemyPiecePosition(Position target, Piece piece, boolean expected) {
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new WhitePawn(),
                target, piece));

        assertThat(whitePawnMovableStrategy.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    private static Stream<Arguments> canMoveToEnemyPiecePosition() {
        return Stream.of(
                Arguments.of(new Position('a', '2'), new BlackFirstPawn(), true),
                Arguments.of(new Position('c', '2'), new BlackFirstPawn(), true),
                Arguments.of(new Position('a', '2'), new WhiteFirstPawn(), false),
                Arguments.of(new Position('c', '2'), new WhiteFirstPawn(), false)
        );
    }
}
