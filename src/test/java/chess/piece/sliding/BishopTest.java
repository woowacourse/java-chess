package chess.piece.sliding;

import static chess.PositionFixtures.A3;
import static chess.PositionFixtures.A7;
import static chess.PositionFixtures.B2;
import static chess.PositionFixtures.B6;
import static chess.PositionFixtures.C3;
import static chess.PositionFixtures.C5;
import static chess.PositionFixtures.D2;
import static chess.PositionFixtures.E3;
import static chess.PositionFixtures.F2;
import static chess.PositionFixtures.F4;
import static chess.PositionFixtures.G5;
import static chess.PositionFixtures.H6;
import static chess.piece.PiecesFixtures.BISHOP_WHITE_C1;
import static chess.piece.PiecesFixtures.BISHOP_WHITE_D4;
import static chess.piece.PiecesFixtures.BISHOP_WHITE_G1;
import static chess.piece.PiecesFixtures.BISHOP_WHITE_G5;
import static chess.piece.PiecesFixtures.KING_BLACK_B2;
import static chess.piece.PiecesFixtures.KNIGHT_WHITE_E5;
import static chess.piece.pawn.PawnFixtures.PAWN_BLACK_A7;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

class BishopTest {

    /*
    ........
    ........
    .......*
    ......*.
    .....*..
    *...*...
    .*.*....
    ..b.....
     */
    @Test
    void legalMovePositions_c1() {
        final var movablePositions = BISHOP_WHITE_C1.legalMovePositions(Set.of(
                BISHOP_WHITE_C1
        ));

        assertThat(movablePositions).containsOnly(A3, B2, D2, E3, F4, G5, H6);
    }

    /*
    ........
    ........
    ........
    ........
    .....p..
    ....*...
    .K.*....
    ..b.....
     */
    @Test
    void legalMovePositions_c1_block() {
        final var movablePositions = BISHOP_WHITE_C1.legalMovePositions(Set.of(
                BISHOP_WHITE_C1,
                KING_BLACK_B2,
                BISHOP_WHITE_G5
        ));

        assertThat(movablePositions).containsOnly(B2, D2, E3, F4);
    }

    /*
    ........
    P.......
    .*......
    ..*.n...
    ...b....
    ..*.*...
    .K...*..
    ......b.
     */
    @Test
    void legalMovePositions_d4() {
        final var movablePositions = BISHOP_WHITE_D4.legalMovePositions(Set.of(
                BISHOP_WHITE_D4,
                PAWN_BLACK_A7,
                KING_BLACK_B2,
                BISHOP_WHITE_G1,
                KNIGHT_WHITE_E5
        ));

        assertThat(movablePositions).containsOnly(A7, B2, B6, C3, C5, E3, F2);
    }
}
