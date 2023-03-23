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

import static chess.domain.PositionFixture.B2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class KnightPIeceTest {
    private KnightPiece knightPiece;
    private Piece oppositePiece;
    private Piece emptyPiece;

    @BeforeEach
    void setting() {
        knightPiece = new KnightPiece(Color.WHITE);
        oppositePiece = new PawnPiece(Color.BLACK);
        emptyPiece = EmptyPiece.getInstance();
    }

    @ParameterizedTest
    @CsvSource(value = {"D, THREE", "D, ONE", "A, FOUR", "C, FOUR"})
    void 나이트는_한_방향으로_이동한_후_해당_방향으로_대각선_이동_테스트(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertTrue(() -> knightPiece.canMove(B2, destination, oppositePiece));
    }


    @ParameterizedTest
    @CsvSource(value = {"B, THREE", "C, TWO"})
    void 나이트_이동_예외_테스트(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        assertFalse(() -> knightPiece.canMove(B2, destination, oppositePiece));
    }
}
