package chess.domain.piece;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PositionFixture.B2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KingPieceTest {
    private KingPiece kingPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        kingPiece = new KingPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @ParameterizedTest
    @CsvSource(value = {"A, ONE", "A, TWO", "A, THREE", "B, THREE", "C, THREE", "C, TWO", "C, ONE", "B, ONE"})
    void 킹은_대각선_혹은_직선으로_1칸_움직일_수_있다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertTrue(() -> kingPiece.canMove(B2, destination, oppositePiece));
    }

    @ParameterizedTest
    @CsvSource(value = {"D, FOUR", "B, FOUR", "D, TWO"})
    void 킹은_대각선이나_직선으로_1칸만_이동_가능하다(File file, Rank rank) {
        //given
        Position destiny = Position.of(file, rank);

        //expect
        assertFalse(() -> kingPiece.canMove(B2, destiny, oppositePiece));
    }
}
