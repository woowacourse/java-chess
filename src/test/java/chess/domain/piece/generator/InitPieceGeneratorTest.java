package chess.domain.piece.generator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.mulitiplemovepiece.Bishop;
import chess.domain.piece.mulitiplemovepiece.Queen;
import chess.domain.piece.mulitiplemovepiece.Rook;
import chess.domain.piece.unitmovepiece.King;
import chess.domain.piece.unitmovepiece.Knight;
import chess.domain.position.File;
import chess.domain.position.Rank;

public class InitPieceGeneratorTest {

    @Test
    @DisplayName("a1은 룩으로 초기화된다.")
    void init_a1() {
        assertThat(InitPieceGenerator.generatePiece(File.A, Rank.ONE)).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("b8은 나이트로 초기화된다.")
    void init_b8() {
        assertThat(InitPieceGenerator.generatePiece(File.B, Rank.EIGHT)).isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("c8은 비숍으로 초기화된다.")
    void init_c8() {
        assertThat(InitPieceGenerator.generatePiece(File.C, Rank.EIGHT)).isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("d1은 퀸으로 초기화된다.")
    void init_d1() {
        assertThat(InitPieceGenerator.generatePiece(File.D, Rank.ONE)).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("e8은 킹으로 초기화된다.")
    void init_e8() {
        assertThat(InitPieceGenerator.generatePiece(File.E, Rank.EIGHT)).isInstanceOf(King.class);
    }

    @Test
    @DisplayName("h2은 폰으로 초기화된다.")
    void init_h2() {
        assertThat(InitPieceGenerator.generatePiece(File.H, Rank.TWO)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("c6은 폰으로 초기화된다.")
    void init_c6() {
        assertThat(InitPieceGenerator.generatePiece(File.C, Rank.SIX)).isInstanceOf(None.class);
    }
}
