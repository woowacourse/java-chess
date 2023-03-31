package chess.domain.piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.B2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BishopPieceTest {

    private BishopPiece bishopPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        bishopPiece = new BishopPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @ParameterizedTest
    @ValueSource(strings = {"NONE", "BLACK"})
    void 비숍이_빈칸_혹은_상대편말_방향_대각선으로_움직일_수_있다(Color color) {
        //expect
        assertTrue(() -> bishopPiece.canMove(A1, B2, oppositePiece));
    }

    @Test
    void 비숍이_대각선이_아닌_방향으로_움직일_수_없다() {
        //expect
        assertFalse(() -> bishopPiece.canMove(A1, A2, oppositePiece));
    }
}
