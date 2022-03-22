package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("폰을 생성한다.")
    void constructPawn() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new Pawn(position))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩을 생성한다.")
    void constructRook() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new Rook(position))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트를 생성한다.")
    void constructKnight() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new Knight(position))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍을 생성한다.")
    void constructBishop() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new Bishop(position))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸을 생성한다.")
    void constructQueen() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new Queen(position))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹을 생성한다.")
    void constructKing() {
        Position position = new Position(Column.A, Row.ONE);
        assertThatCode(() -> new King(position))
                .doesNotThrowAnyException();
    }
}
