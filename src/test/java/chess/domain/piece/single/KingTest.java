package chess.domain.piece.single;

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

class KingTest {

    private Piece king;
    private Position source;

    @BeforeEach
    void setUp() {
        king = new King(WHITE);
        source = Position.of('a', '1');
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("킹 기물 생성")
    void createKing(Color color) {
        Piece king = new King(color);
        assertThat(king).isInstanceOf(King.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,true", "b,1,true", "b,2,true", "b,3,false", "c,3,false"})
    @DisplayName("킹의 빈곳 이동 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row, boolean expected) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, new King(WHITE)));

        assertThat(king.isMovable(source, target, chessBoard)).isEqualTo(expected);
    }

    @Test
    @DisplayName("기물이 존재할 경우의 이동 가능 여부 확인")
    void isMovableToDifferentPiecePosition() {
        Position target = Position.of('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, new King(WHITE),
                target, new King(BLACK)
        ));

        assertThat(king.isMovable(source, target, chessBoard)).isTrue();
    }
}
