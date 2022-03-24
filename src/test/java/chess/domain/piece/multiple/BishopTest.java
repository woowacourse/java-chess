package chess.domain.piece.multiple;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class BishopTest {

    private Piece bishop;
    private Position source;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.WHITE);
        source = new Position('d', '4');
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("비숍 기물 생성")
    void createBishop(Color color) {
        Piece bishop = new Bishop(color);
        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,6,true", "c,5,true", "c,3,true", "b,5,false", "d,6,false"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어났는지에 따른 이동 가능여부")
    void emptyDirection(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Bishop(WHITE)));

        assertThat(bishop.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히지 않고 목표지점이 비어 있으면 이동 가능")
    void isMovableToClearEmptyPosition() {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Bishop(WHITE)));

        assertThat(bishop.isMovable(source, target, chessBoard)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("가로막히지 않고 목표지점에 기물이 존재시 이동 가능 여부")
    void isMovableToClearPiecePosition(Color color, boolean expected) {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Bishop(WHITE),
                target, new Bishop(color)));

        assertThat(bishop.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Bishop(WHITE),
                new Position('c', '5'), new Bishop(BLACK)));

        assertThat(bishop.isMovable(source, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition(Color color) {
        Position target = new Position('b', '6');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Bishop(WHITE),
                new Position('c', '5'), new Bishop(BLACK),
                target, new Bishop(color)));

        assertThat(bishop.isMovable(source, target, chessBoard)).isFalse();
    }
}
