package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.board.File;
import chess.board.Rank;

public class PieceGeneratorTest {

    @Test
    @DisplayName("a1은 룩으로 초기화된다.")
    void init_a1() {
        assertThat(PieceGenerator.generatePiece(File.A, Rank.ONE)).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("b8은 나이트로 초기화된다.")
    void init_b8() {
        assertThat(PieceGenerator.generatePiece(File.B, Rank.EIGHT)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("c8은 비숍으로 초기화된다.")
    void init_c8() {
        assertThat(PieceGenerator.generatePiece(File.C, Rank.EIGHT)).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("d1은 퀸으로 초기화된다.")
    void init_d1() {
        assertThat(PieceGenerator.generatePiece(File.D, Rank.ONE)).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("e8은 킹으로 초기화된다.")
    void init_e8() {
        assertThat(PieceGenerator.generatePiece(File.E, Rank.EIGHT)).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("h2은 폰으로 초기화된다.")
    void init_h2() {
        assertThat(PieceGenerator.generatePiece(File.H, Rank.TWO)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("c6은 폰으로 초기화된다.")
    void init_c6() {
        assertThat(PieceGenerator.generatePiece(File.C, Rank.SIX)).isInstanceOf(None.class);
    }
}
