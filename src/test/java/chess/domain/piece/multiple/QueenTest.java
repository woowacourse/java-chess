package chess.domain.piece.multiple;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

class QueenTest {

    private Piece queen;
    private Position source;

    @BeforeEach
    void setUp() {
        queen = new Queen(WHITE);
        source = Position.of('d', '4');
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("퀸 기물 생성")
    void createQueen(Color color) {
        assertThat(new Queen(color)).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,7,true", "d,8,true", "h,8,true", "h,4,true", "e,6,false", "f,5,false", "a,8,false"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어났는지에 따른 이동 가능여부")
    void emptyDirection(char col, char row, boolean expected) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Queen(WHITE)));

        assertThat(queen.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE,false", "BLACK,true"})
    @DisplayName("가로막히지 않고 목표지점에 기물이 존재시 이동 가능 여부")
    void isMovableToClearPiecePosition(Color color, boolean expected) {
        Position target = Position.of('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Queen(WHITE),
                target, new Queen(color)));

        assertThat(queen.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("가로막히고 목표지점이 비어 있으면 이동 불가능")
    void isMovableToNotClearEmptyPosition() {
        Position target = Position.of('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Queen(WHITE),
                Position.of('d', '7'), new Queen(BLACK)));

        assertThat(queen.isMovable(source, target, chessBoard)).isFalse();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("가로막히고 목표지점에 기물이 존재시 이동 불가능")
    void isMovableToNotClearPiecePosition(Color color) {
        Position target = Position.of('d', '8');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Queen(WHITE),
                Position.of('d', '7'), new Queen(BLACK),
                target, new Queen(color)));

        assertThat(queen.isMovable(source, target, chessBoard)).isFalse();
    }
}
