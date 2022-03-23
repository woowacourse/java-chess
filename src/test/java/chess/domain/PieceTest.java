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
        assertThatCode(() -> new Pawn(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("룩을 생성한다.")
    void constructRook() {
        assertThatCode(() -> new Rook(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("나이트를 생성한다.")
    void constructKnight() {
        assertThatCode(() -> new Knight(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("비숍을 생성한다.")
    void constructBishop() {
        assertThatCode(() -> new Bishop(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("퀸을 생성한다.")
    void constructQueen() {
        assertThatCode(() -> new Queen(Color.BLACK))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹을 생성한다.")
    void constructKing() {
        assertThatCode(() -> new King(Color.BLACK))
                .doesNotThrowAnyException();
    }
}
