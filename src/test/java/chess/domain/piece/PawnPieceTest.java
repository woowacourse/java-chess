package chess.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A4;
import static chess.domain.PositionFixture.B3;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnPieceTest {

    private PawnPiece pawnPiece;
    private Piece sameColorPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        pawnPiece = new PawnPiece(Color.WHITE);
        sameColorPiece = new PawnPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @Test
    void 폰은_초기상태에_앞으로_두칸_갈_수_있다() {

        //expect
        assertTrue(() -> pawnPiece.canMove(A2, A4, emptyPiece));
    }

    @Test
    void 폰은_딱_한번_앞으로_두_칸_전진할_수_있다() {
        // given, when
        boolean firstMove = pawnPiece.canMove(A2, A4, emptyPiece);
        boolean secondResult = pawnPiece.canMove(A2, A4, emptyPiece);

        // then
        assertTrue(firstMove);
        assertFalse(secondResult);
    }

    @Test
    void 폰_대각선_움직임_테스트() {
        //expect
        assertTrue(() -> pawnPiece.canMove(A2, B3, oppositePiece));
    }

    @Test
    void 폰_대각선_움직임_색_예외_테스트() {
        //given, when
        boolean result1 = pawnPiece.canMove(A2, B3, sameColorPiece);
        boolean result2 = pawnPiece.canMove(A2, B3, emptyPiece);

        //expect
        Assertions.assertAll(
                () -> {
                    assertFalse(result1);
                },
                () -> {
                    assertFalse(result2);
                }
        );

    }
}
