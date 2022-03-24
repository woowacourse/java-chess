package chess.domain.piece;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class RookTest {

    private Piece rook;
    private Position source;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.WHITE);
        source = new Position('d', '4');
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("룩 기물 생성")
    void createRook(Color color) {
        assertThat(new Rook(color)).isInstanceOf(Rook.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"d,8,true", "d,7,true", "c,4,true", "c,5,false", "c,6,false"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어났는지에 따른 이동 가능여부")
    void emptyDirection(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Rook(WHITE)));

        assertThat(rook.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히지 않고 목표지점이 비어 있으면 이동 가능")
    void isMovableToClearEmptyPosition() {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Rook(WHITE)));

        assertThat(rook.isMovable(source, target, chessBoard)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("가로막히지 않고 목표지점에 기물이 존재시 이동 가능 여부")
    void isMovableToClearPiecePosition(Color color, boolean expected) {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Rook(WHITE),
                target, new Rook(color)));

        assertThat(rook.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Rook(WHITE),
                new Position('d', '7'), new Rook(BLACK)));

        assertThat(rook.isMovable(source, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition(Color color) {
        Position target = new Position('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Rook(WHITE),
                new Position('d', '7'), new Rook(BLACK),
                target, new Rook(color)));

        assertThat(rook.isMovable(source, target, chessBoard)).isFalse();
    }
}
