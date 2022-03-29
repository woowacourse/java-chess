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

class KingTest {

    private PieceRule king;
    private Position source;

    @BeforeEach
    void setUp() {
        king = new King();
        source = Position.of('a', '1');
    }

    @Test
    @DisplayName("킹 기물 생성")
    void createKing() {
        assertThat(king).isInstanceOf(King.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,3,false", "c,3,false"})
    @DisplayName("목표 지점이 이동 가능 경로를 벗어나면 예외 발생")
    void isMovableToEmptyPosition(char col, char row) {
        Position target = Position.of(col, row);
        ChessBoard chessBoard = new ChessBoard(Map.of(source, createWhitePiece(new King())));

        assertThatThrownBy(() -> king.move(source, target, chessBoard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("움직일 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("기물이 존재할 경우의 이동 가능 여부 확인")
    void isMovableToDifferentPiecePosition() {
        Position target = Position.of('a', '2');
        ChessBoard chessBoard = new ChessBoard(Map.of(
                source, createWhitePiece(new King()),
                target, createBlackPiece(new King())
        ));

        assertThat(king.move(source, target, chessBoard)).isInstanceOf(King.class);
    }
}
