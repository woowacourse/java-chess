package chess.domain.piece.single;

import static chess.domain.piece.Piece.createBlackPiece;
import static chess.domain.piece.Piece.createWhitePiece;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.piece.PieceRule;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    private PieceRule knight;
    private Position source;

    @BeforeEach
    void setUp() {
        knight = new Knight();
        source = Position.of('a', '1');
    }

    @Test
    @DisplayName("나이트 기물 생성")
    void createPawn() {
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2", "b,4", "c,5"})
    @DisplayName("나이트의 빈곳 이동 가능 여부 확인")
    void isMovableToEmptyPosition(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, createWhitePiece(new Knight())));

        assertThatThrownBy(() -> knight.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("기물이 존재할 경우의 이동 가능 여부 확인")
    void isMovableToDifferentPiecePosition() {
        Position target = Position.of('b', '3');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new Knight()),
                target, createBlackPiece(new Knight())
        ));

        assertThat(knight.move(source, target, chessBoard)).isInstanceOf(Knight.class);
    }
}
