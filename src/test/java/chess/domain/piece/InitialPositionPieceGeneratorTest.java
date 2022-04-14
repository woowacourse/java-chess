package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Column;
import chess.domain.position.Row;

public class InitialPositionPieceGeneratorTest {

    @Test
    @DisplayName("a1은 룩으로 초기화된다.")
    void init_a1() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.A, Row.ONE)).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("b8은 나이트로 초기화된다.")
    void init_b8() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.B, Row.EIGHT)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("c8은 비숍으로 초기화된다.")
    void init_c8() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.C, Row.EIGHT)).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("d1은 퀸으로 초기화된다.")
    void init_d1() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.D, Row.ONE)).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("e8은 킹으로 초기화된다.")
    void init_e8() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.E, Row.EIGHT)).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("h2은 폰으로 초기화된다.")
    void init_h2() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.H, Row.TWO)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("c6은 폰으로 초기화된다.")
    void init_c6() {
        assertThat(InitialPositionPieceGenerator.generatePiece(Column.C, Row.SIX)).isInstanceOf(None.class);
    }
}
