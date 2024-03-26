package chess.domain.board;

import static chess.fixture.PieceFixture.BLACK_BISHOP;
import static chess.fixture.PieceFixture.BLACK_KING;
import static chess.fixture.PieceFixture.BLACK_KNIGHT;
import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.BLACK_QUEEN;
import static chess.fixture.PieceFixture.BLACK_ROOK;
import static chess.fixture.PieceFixture.EMPTY;
import static chess.fixture.PieceFixture.WHITE_BISHOP;
import static chess.fixture.PieceFixture.WHITE_KING;
import static chess.fixture.PieceFixture.WHITE_KNIGHT;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PieceFixture.WHITE_QUEEN;
import static chess.fixture.PieceFixture.WHITE_ROOK;
import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A5;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.A8;
import static chess.fixture.PositionFixture.B1;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.B4;
import static chess.fixture.PositionFixture.B5;
import static chess.fixture.PositionFixture.B6;
import static chess.fixture.PositionFixture.B7;
import static chess.fixture.PositionFixture.B8;
import static chess.fixture.PositionFixture.C1;
import static chess.fixture.PositionFixture.C2;
import static chess.fixture.PositionFixture.C3;
import static chess.fixture.PositionFixture.C4;
import static chess.fixture.PositionFixture.C5;
import static chess.fixture.PositionFixture.C6;
import static chess.fixture.PositionFixture.C7;
import static chess.fixture.PositionFixture.C8;
import static chess.fixture.PositionFixture.D1;
import static chess.fixture.PositionFixture.D2;
import static chess.fixture.PositionFixture.D3;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D6;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.D8;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E4;
import static chess.fixture.PositionFixture.E5;
import static chess.fixture.PositionFixture.E6;
import static chess.fixture.PositionFixture.E7;
import static chess.fixture.PositionFixture.E8;
import static chess.fixture.PositionFixture.F1;
import static chess.fixture.PositionFixture.F2;
import static chess.fixture.PositionFixture.F3;
import static chess.fixture.PositionFixture.F4;
import static chess.fixture.PositionFixture.F5;
import static chess.fixture.PositionFixture.F6;
import static chess.fixture.PositionFixture.F7;
import static chess.fixture.PositionFixture.F8;
import static chess.fixture.PositionFixture.G1;
import static chess.fixture.PositionFixture.G2;
import static chess.fixture.PositionFixture.G3;
import static chess.fixture.PositionFixture.G4;
import static chess.fixture.PositionFixture.G5;
import static chess.fixture.PositionFixture.G6;
import static chess.fixture.PositionFixture.G7;
import static chess.fixture.PositionFixture.G8;
import static chess.fixture.PositionFixture.H1;
import static chess.fixture.PositionFixture.H2;
import static chess.fixture.PositionFixture.H3;
import static chess.fixture.PositionFixture.H4;
import static chess.fixture.PositionFixture.H5;
import static chess.fixture.PositionFixture.H6;
import static chess.fixture.PositionFixture.H7;
import static chess.fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardCreatorTest {

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("초기화된 체스판을 생성한다.")
    @Test
    void create() {
        BoardCreator boardCreator = new BoardCreator();

        Map<Position, Piece> board = boardCreator.create();

        assertThat(board).containsExactly(
                Map.entry(A8, BLACK_ROOK),
                Map.entry(B8, BLACK_KNIGHT),
                Map.entry(C8, BLACK_BISHOP),
                Map.entry(D8, BLACK_QUEEN),
                Map.entry(E8, BLACK_KING),
                Map.entry(F8, BLACK_BISHOP),
                Map.entry(G8, BLACK_KNIGHT),
                Map.entry(H8, BLACK_ROOK),

                Map.entry(A7, BLACK_PAWN),
                Map.entry(B7, BLACK_PAWN),
                Map.entry(C7, BLACK_PAWN),
                Map.entry(D7, BLACK_PAWN),
                Map.entry(E7, BLACK_PAWN),
                Map.entry(F7, BLACK_PAWN),
                Map.entry(G7, BLACK_PAWN),
                Map.entry(H7, BLACK_PAWN),

                Map.entry(A6, EMPTY),
                Map.entry(B6, EMPTY),
                Map.entry(C6, EMPTY),
                Map.entry(D6, EMPTY),
                Map.entry(E6, EMPTY),
                Map.entry(F6, EMPTY),
                Map.entry(G6, EMPTY),
                Map.entry(H6, EMPTY),

                Map.entry(A5, EMPTY),
                Map.entry(B5, EMPTY),
                Map.entry(C5, EMPTY),
                Map.entry(D5, EMPTY),
                Map.entry(E5, EMPTY),
                Map.entry(F5, EMPTY),
                Map.entry(G5, EMPTY),
                Map.entry(H5, EMPTY),

                Map.entry(A4, EMPTY),
                Map.entry(B4, EMPTY),
                Map.entry(C4, EMPTY),
                Map.entry(D4, EMPTY),
                Map.entry(E4, EMPTY),
                Map.entry(F4, EMPTY),
                Map.entry(G4, EMPTY),
                Map.entry(H4, EMPTY),

                Map.entry(A3, EMPTY),
                Map.entry(B3, EMPTY),
                Map.entry(C3, EMPTY),
                Map.entry(D3, EMPTY),
                Map.entry(E3, EMPTY),
                Map.entry(F3, EMPTY),
                Map.entry(G3, EMPTY),
                Map.entry(H3, EMPTY),

                Map.entry(A2, WHITE_PAWN),
                Map.entry(B2, WHITE_PAWN),
                Map.entry(C2, WHITE_PAWN),
                Map.entry(D2, WHITE_PAWN),
                Map.entry(E2, WHITE_PAWN),
                Map.entry(F2, WHITE_PAWN),
                Map.entry(G2, WHITE_PAWN),
                Map.entry(H2, WHITE_PAWN),

                Map.entry(A1, WHITE_ROOK),
                Map.entry(B1, WHITE_KNIGHT),
                Map.entry(C1, WHITE_BISHOP),
                Map.entry(D1, WHITE_QUEEN),
                Map.entry(E1, WHITE_KING),
                Map.entry(F1, WHITE_BISHOP),
                Map.entry(G1, WHITE_KNIGHT),
                Map.entry(H1, WHITE_ROOK)
        );
    }
}
