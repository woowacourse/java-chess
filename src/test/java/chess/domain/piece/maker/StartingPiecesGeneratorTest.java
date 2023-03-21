package chess.domain.piece.maker;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.PositionFixture.A1;
import static chess.PositionFixture.A2;
import static chess.PositionFixture.A7;
import static chess.PositionFixture.A8;
import static chess.PositionFixture.B1;
import static chess.PositionFixture.B2;
import static chess.PositionFixture.B7;
import static chess.PositionFixture.B8;
import static chess.PositionFixture.C1;
import static chess.PositionFixture.C2;
import static chess.PositionFixture.C7;
import static chess.PositionFixture.C8;
import static chess.PositionFixture.D1;
import static chess.PositionFixture.D2;
import static chess.PositionFixture.D7;
import static chess.PositionFixture.D8;
import static chess.PositionFixture.E1;
import static chess.PositionFixture.E2;
import static chess.PositionFixture.E7;
import static chess.PositionFixture.E8;
import static chess.PositionFixture.F1;
import static chess.PositionFixture.F2;
import static chess.PositionFixture.F7;
import static chess.PositionFixture.F8;
import static chess.PositionFixture.G1;
import static chess.PositionFixture.G2;
import static chess.PositionFixture.G7;
import static chess.PositionFixture.G8;
import static chess.PositionFixture.H1;
import static chess.PositionFixture.H2;
import static chess.PositionFixture.H7;
import static chess.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class StartingPiecesGeneratorTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void init_test() {
        final List<Piece> pieces = new StartingPiecesGenerator().generate();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .containsExactlyInAnyOrder(
                        tuple(A8, Color.BLACK, Rook.class),
                        tuple(B8, Color.BLACK, Knight.class),
                        tuple(C8, Color.BLACK, Bishop.class),
                        tuple(D8, Color.BLACK, Queen.class),
                        tuple(E8, Color.BLACK, King.class),
                        tuple(F8, Color.BLACK, Bishop.class),
                        tuple(G8, Color.BLACK, Knight.class),
                        tuple(H8, Color.BLACK, Rook.class),
                        tuple(A7, Color.BLACK, Pawn.class),
                        tuple(B7, Color.BLACK, Pawn.class),
                        tuple(C7, Color.BLACK, Pawn.class),
                        tuple(D7, Color.BLACK, Pawn.class),
                        tuple(E7, Color.BLACK, Pawn.class),
                        tuple(F7, Color.BLACK, Pawn.class),
                        tuple(G7, Color.BLACK, Pawn.class),
                        tuple(H7, Color.BLACK, Pawn.class),

                        tuple(A1, Color.WHITE, Rook.class),
                        tuple(B1, Color.WHITE, Knight.class),
                        tuple(C1, Color.WHITE, Bishop.class),
                        tuple(D1, Color.WHITE, Queen.class),
                        tuple(E1, Color.WHITE, King.class),
                        tuple(F1, Color.WHITE, Bishop.class),
                        tuple(G1, Color.WHITE, Knight.class),
                        tuple(H1, Color.WHITE, Rook.class),
                        tuple(A2, Color.WHITE, Pawn.class),
                        tuple(B2, Color.WHITE, Pawn.class),
                        tuple(C2, Color.WHITE, Pawn.class),
                        tuple(D2, Color.WHITE, Pawn.class),
                        tuple(E2, Color.WHITE, Pawn.class),
                        tuple(F2, Color.WHITE, Pawn.class),
                        tuple(G2, Color.WHITE, Pawn.class),
                        tuple(H2, Color.WHITE, Pawn.class)
                );
    }

}
