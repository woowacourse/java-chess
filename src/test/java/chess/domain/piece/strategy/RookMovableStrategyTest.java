package chess.domain.piece.strategy;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.Color;
import chess.domain.piece.Rook;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

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
    @DisplayName("목표 지점이 이동 가능 경로를 벗어났는지에 따른 이동 가능여부")
    void emptyDirection(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Rook(WHITE)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히지 않고 목표지점이 비어 있으면 이동 가능")
    void isMovableToClearEmptyPosition() {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(start, new Rook(WHITE)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("가로막히지 않고 목표지점에 기물이 존재시 이동 가능 여부")
    void isMovableToClearPiecePosition(Color color, boolean expected) {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Rook(WHITE),
                target, new Rook(color)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Rook(WHITE),
                new Position('d', '7'), new Rook(BLACK)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition(Color color) {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                start, new Rook(WHITE),
                new Position('d', '7'), new Rook(BLACK),
                target, new Rook(color)));

        assertThat(rookMovableStrategy.isMovable(start, target, chessBoard)).isFalse();
    }
}
