package chess.domain.piece.strategy;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class BishopMovableStrategyTest {

    private PieceMovableStrategy bishopMovableStrategy;
    private Position start;

    @BeforeEach
    void setUp() {
        bishopMovableStrategy = new BishopMovableStrategy();
        start = new Position('d', '4');
    }

    @ParameterizedTest
    @CsvSource(value = {"b,6,true", "c,5,true", "c,3,true", "b,5,false", "d,6,false"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어났는지에 따른 이동 가능여부")
    void emptyDirection(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Bishop(WHITE)));

        assertThat(bishopMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히지 않고 목표지점이 비어 있으면 이동 가능")
    void isMovableToClearEmptyPosition() {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Bishop(WHITE)));

        assertThat(bishopMovableStrategy.isMovable(start, target, chessBoard)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("가로막히지 않고 목표지점에 기물이 존재시 이동 가능 여부")
    void isMovableToClearPiecePosition(Color color, boolean expected) {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Bishop(WHITE),
                target, new Bishop(color)));

        assertThat(bishopMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Bishop(WHITE),
                new Position('c', '5'), new Bishop(BLACK)));

        assertThat(bishopMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition(Color color) {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Bishop(WHITE),
                new Position('c', '5'), new Bishop(BLACK),
                target, new Bishop(color)));

        assertThat(bishopMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }
}
