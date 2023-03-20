package chess.piece.sliding;

import static chess.PositionFixtures.A3;
import static chess.PositionFixtures.B1;
import static chess.PositionFixtures.B3;
import static chess.PositionFixtures.B4;
import static chess.PositionFixtures.B5;
import static chess.PositionFixtures.C1;
import static chess.PositionFixtures.C2;
import static chess.PositionFixtures.C3;
import static chess.PositionFixtures.D2;
import static chess.PositionFixtures.E2;
import static chess.PositionFixtures.F2;
import static chess.PositionFixtures.G2;
import static chess.PositionFixtures.H2;
import static chess.piece.PiecesFixtures.BISHOP_WHITE_D4;
import static chess.piece.PiecesFixtures.KING_BLACK_H2;
import static chess.piece.PiecesFixtures.KING_WHITE_A2;
import static chess.piece.PiecesFixtures.KNIGHT_WHITE_B6;
import static chess.piece.PiecesFixtures.QUEEN_WHITE_B2;
import static chess.piece.PiecesFixtures.ROOK_WHITE_A1;
import static chess.piece.PiecesFixtures.ROOK_WHITE_B2;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

class QueenTest {

    /*
    ........
    ........
    .n......
    .*......
    .*.b....
    ***.....
    kq*****K
    r**.....
    */
    @Test
    void legalMovePositions_b2() {
        final var movablePositions = QUEEN_WHITE_B2.legalMovePositions(Set.of(
                ROOK_WHITE_A1,
                KING_WHITE_A2,
                ROOK_WHITE_B2,
                KNIGHT_WHITE_B6,
                BISHOP_WHITE_D4,
                KING_BLACK_H2
        ));

        assertThat(movablePositions).containsOnly(A3, B1, B3, B4, B5, C1, C2, C3, D2, E2, F2, G2, H2);
    }
}
