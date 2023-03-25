package chess.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A4;
import static chess.domain.PositionFixture.B3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PawnPieceTest {

    private InitPawnPiece initPawnPiece;
    private PawnPiece pawnPiece;
    private Piece sameColorPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        initPawnPiece = new InitPawnPiece(Color.WHITE);
        pawnPiece = new PawnPiece(Color.WHITE);
        sameColorPiece = new PawnPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @Test
    void 폰은_초기상태에_앞으로_한칸_갈_수_있다() {
        //expect
        assertTrue(() -> initPawnPiece.canMove(A2, A4, emptyPiece));
        assertFalse(() -> pawnPiece.canMove(A2, A4, emptyPiece));
    }

    @Test
    void Init_폰은__앞으로_두_칸전진할_수_있다() {
        // given, when
        InitPawnPiece initPawnPiece = new InitPawnPiece(Color.WHITE);
        boolean firstMove = initPawnPiece.canMove(A2, A4, emptyPiece);

        // then
        assertTrue(firstMove);
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

    @Test
    void 흰색_폰은_rank_가_커지는_방향으로만_이동가능하다() {
        //given
        PawnPiece pawnPiece1 = new PawnPiece(Color.WHITE);

        //when
        boolean result1 = pawnPiece1.canMove(A2, A3, emptyPiece);
        boolean result2 = pawnPiece1.canMove(A3, A2, emptyPiece);

        //then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void 검정_폰은_rank_가_작아지_방향으로만_이동가능하다() {
        //given
        PawnPiece pawnPiece1 = new PawnPiece(Color.BLACK);

        //when
        boolean result1 = pawnPiece1.canMove(A3, A2, emptyPiece);
        boolean result2 = pawnPiece1.canMove(A2, A3, emptyPiece);

        //then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void InitPawn_은_이동_후_Pawn_을_반환한다() {
        //given
        InitPawnPiece initPawnPiece1 = new InitPawnPiece(Color.BLACK);
        Piece piece = initPawnPiece1.nextPiece();

        //then
        assertThat(piece).isInstanceOf(PawnPiece.class);
    }
}
