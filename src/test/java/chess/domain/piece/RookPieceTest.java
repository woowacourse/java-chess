package chess.domain.piece;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.PositionFixture.A1;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RookPieceTest {

    private RookPiece rookPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        rookPiece = new RookPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @ParameterizedTest
    @CsvSource(value = {"A, EIGHT", "H, ONE"})
    void 룩은_직선으로_움직일_수_있다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        final Class<RookPiece> rookPieceClass = RookPiece.class;
        Class<? extends RookPiece> aClass = rookPiece.getClass();
        System.out.println(rookPieceClass == aClass);

        //expect
        assertTrue(() -> rookPiece.canMove(A1, destination, oppositePiece));
    }

    @ParameterizedTest
    @CsvSource(value = {"B, TWO", "C, TWO"})
    void 룩이_직선이_아닌_방향으로_움직일_수_없다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertFalse(() -> rookPiece.canMove(A1, destination, oppositePiece));
    }
}
