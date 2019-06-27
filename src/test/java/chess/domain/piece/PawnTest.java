package chess.domain.piece;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static chess.domain.piece.PieceGenerator.PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest {
    Piece whitePawn = PAWN.generate(PieceColor.WHITE);
    Piece blackPawn = PAWN.generate(PieceColor.BLACK);

    @Test
    void 흰색_폰_이동_검사_1() {
        assertDoesNotThrow(() ->
                whitePawn.pathOf(Tile.of("B3"), Tile.of("b4"), false)
        );
    }

    @Test
    void 흰색_폰_이동_검사_2() {
        assertThat(whitePawn.pathOf(Tile.of("b2"), Tile.of("b4"), false))
                .isEqualTo(Arrays.asList(Tile.of("b3")));

    }

    @Test
    void 흰색_폰_이동_검사_3() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b3"), Tile.of("b5"), false)
        );
    }

    @Test
    void 흰색_폰_한칸_전진_정상() {
        assertDoesNotThrow(() ->
                whitePawn.pathOf(Tile.of("b3"), Tile.of("b4"), false)
        );
    }

    @Test
    void 흰색_폰_한칸_전진_오류() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b3"), Tile.of("b4"), true)
        );
    }

    @Test
    void 흰색_폰_대각선_전진_에러() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b3"), Tile.of("c4"), false)
        );
    }

    @Test
    void 흰색_폰_대각선_전진_정상() {
        assertDoesNotThrow(() ->
                whitePawn.pathOf(Tile.of("b3"), Tile.of("c4"), true)
        );
    }

    @Test
    void 흰색_폰_뒤로_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b2"), Tile.of("b1"), false)
        );
    }

    @Test
    void 흰색_폰_세칸_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b2"), Tile.of("b5"), false)
        );
    }

    @Test
    void 흰색_폰_대각선_두칸_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                whitePawn.pathOf(Tile.of("b2"), Tile.of("d4"), false)
        );
    }

    @Test
    void 검정_폰_이동_검사_1() {
        assertDoesNotThrow(() ->
                blackPawn.pathOf(Tile.of("B7"), Tile.of("b6"), false)
        );
    }

    @Test
    void 검정_폰_이동_검사_2() {
        assertThat(blackPawn.pathOf(Tile.of("b7"), Tile.of("b5"), false))
                .isEqualTo(Arrays.asList(Tile.of("b6")));

    }

    @Test
    void 검정_폰_이동_검사_3() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b6"), Tile.of("b4"), false)
        );
    }

    @Test
    void 검정_폰_한칸_전진_정상() {
        assertDoesNotThrow(() ->
                blackPawn.pathOf(Tile.of("b6"), Tile.of("b5"), false)
        );
    }

    @Test
    void 검정_폰_한칸_전진_오류() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b6"), Tile.of("b5"), true)
        );
    }

    @Test
    void 검정_폰_대각선_전진_에러() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b6"), Tile.of("c5"), false)
        );
    }

    @Test
    void 검정_폰_대각선_전진_정상() {
        assertDoesNotThrow(() ->
                blackPawn.pathOf(Tile.of("b6"), Tile.of("c5"), true)
        );
    }

    @Test
    void 검정_폰_뒤로_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b5"), Tile.of("b6"), false)
        );
    }

    @Test
    void 검정_폰_세칸_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b5"), Tile.of("b2"), false)
        );
    }

    @Test
    void 검정_폰_대각선_두칸_이동_에러() {
        assertThrows(RuntimeException.class, () ->
                blackPawn.pathOf(Tile.of("b5"), Tile.of("d3"), false)
        );
    }
}