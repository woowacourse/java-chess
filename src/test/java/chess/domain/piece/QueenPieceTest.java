package chess.domain.piece;

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
public class QueenPieceTest {

    private QueenPiece queenPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        queenPiece = new QueenPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @ParameterizedTest
    @CsvSource(value = {"A, EIGHT", "H, ONE", "H, EIGHT"})
    void 퀸은_대각선_혹은_직선으로_움직일_수_있다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertTrue(() -> queenPiece.canMove(A1, destination, oppositePiece));
    }

    @ParameterizedTest
    @CsvSource(value = {"B, THREE", "C, TWO"})
    void 퀸이_대각선이_아닌_방향으로_움직일_수_없다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertFalse(() -> queenPiece.canMove(A1, destination, oppositePiece));
    }
}
