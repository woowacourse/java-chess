package domain.chess;

import domain.chess.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.print.PeekGraphics;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @DisplayName("PieceFactory create 호출 시 자동으로 말들이 자동 생성된다.")
    @Test
    void peice_factory_generate() {
        List<Piece> pieces = PieceFactory.createPieces();
        List<Piece> expected = Arrays.asList(
                Bishop.Of("B", Position.Of(0, 2), true),
                Bishop.Of("B", Position.Of(0, 5), true),
                Bishop.Of("b", Position.Of(7, 2), false),
                Bishop.Of("b", Position.Of(7, 5), false),
                King.Of("K", Position.Of(0, 4), true),
                King.Of("k", Position.Of(7, 4), false),
                Knight.Of("N", Position.Of(0, 1), true),
                Knight.Of("N", Position.Of(0, 6), true),
                Knight.Of("n", Position.Of(7, 1), false),
                Knight.Of("n", Position.Of(7, 6), false),
                Queen.Of("Q", Position.Of(0, 3), true),
                Queen.Of("q", Position.Of(7, 3), false),
                Pawn.Of("P", Position.Of(1, 0), true),
                Pawn.Of("P", Position.Of(1, 1), true),
                Pawn.Of("P", Position.Of(1, 2), true),
                Pawn.Of("P", Position.Of(1, 4), true),
                Pawn.Of("P", Position.Of(1, 3), true),
                Pawn.Of("P", Position.Of(1, 5), true),
                Pawn.Of("P", Position.Of(1, 6), true),
                Pawn.Of("P", Position.Of(1, 7), true),
                Pawn.Of("p", Position.Of(6, 0), false),
                Pawn.Of("p", Position.Of(6, 1), false),
                Pawn.Of("p", Position.Of(6, 2), false),
                Pawn.Of("p", Position.Of(6, 3), false),
                Pawn.Of("p", Position.Of(6, 4), false),
                Pawn.Of("p", Position.Of(6, 5), false),
                Pawn.Of("p", Position.Of(6, 6), false),
                Pawn.Of("p", Position.Of(6, 7), false),
                Rook.Of("R", Position.Of(0, 0), true),
                Rook.Of("R", Position.Of(0, 7), true),
                Rook.Of("r", Position.Of(7, 7), false),
                Rook.Of("r", Position.Of(7, 0), false)
        );
        assertThat(pieces).containsAll(expected);
    }
}