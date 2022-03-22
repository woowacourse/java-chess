package chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.piece.PieceGenerator;
import chess.piece.Rook;

public class PieceGeneratorTest {

    @Test
    @DisplayName("테스트")
    void test() {
        assertThat(PieceGenerator.generatePiece(File.A, Rank.ONE)).isInstanceOf(Rook.class);
    }
}
