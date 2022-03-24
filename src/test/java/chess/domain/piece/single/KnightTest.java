package chess.domain.piece.single;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.single.Knight;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private Piece knight;
    private Position source;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.WHITE);
        source = new Position('a', '1');
    }

    @Test
    @DisplayName("나이트 기물 생성")
    void createPawn() {
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"c,2,true", "b,3,true", "b,2,false", "b,4,false", "c,5,false"})
    @DisplayName("나이트의 빈곳 이동 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new Knight(WHITE)));

        assertThat(knight.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"c,2,WHITE,false", "b,3,BLACK,true"})
    @DisplayName("기물이 존재할 경우의 이동 가능 여부 확인")
    void isMovableToDifferentPiecePosition(char col, char row, Color color, boolean expected) {
        Position target = new Position(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new Knight(WHITE),
                target, new Knight(color)
        ));

        assertThat(knight.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }
}
