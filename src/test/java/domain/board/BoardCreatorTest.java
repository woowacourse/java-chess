package domain.board;

import static fixture.PositionFixture.A1;
import static fixture.PositionFixture.A2;
import static fixture.PositionFixture.A3;
import static fixture.PositionFixture.A4;
import static fixture.PositionFixture.A5;
import static fixture.PositionFixture.A6;
import static fixture.PositionFixture.A7;
import static fixture.PositionFixture.A8;
import static fixture.PositionFixture.B1;
import static fixture.PositionFixture.B2;
import static fixture.PositionFixture.B3;
import static fixture.PositionFixture.B4;
import static fixture.PositionFixture.B5;
import static fixture.PositionFixture.B6;
import static fixture.PositionFixture.B7;
import static fixture.PositionFixture.B8;
import static fixture.PositionFixture.C1;
import static fixture.PositionFixture.C2;
import static fixture.PositionFixture.C3;
import static fixture.PositionFixture.C4;
import static fixture.PositionFixture.C5;
import static fixture.PositionFixture.C6;
import static fixture.PositionFixture.C7;
import static fixture.PositionFixture.C8;
import static fixture.PositionFixture.D1;
import static fixture.PositionFixture.D2;
import static fixture.PositionFixture.D3;
import static fixture.PositionFixture.D4;
import static fixture.PositionFixture.D5;
import static fixture.PositionFixture.D6;
import static fixture.PositionFixture.D7;
import static fixture.PositionFixture.D8;
import static fixture.PositionFixture.E1;
import static fixture.PositionFixture.E2;
import static fixture.PositionFixture.E3;
import static fixture.PositionFixture.E4;
import static fixture.PositionFixture.E5;
import static fixture.PositionFixture.E6;
import static fixture.PositionFixture.E7;
import static fixture.PositionFixture.E8;
import static fixture.PositionFixture.F1;
import static fixture.PositionFixture.F2;
import static fixture.PositionFixture.F3;
import static fixture.PositionFixture.F4;
import static fixture.PositionFixture.F5;
import static fixture.PositionFixture.F6;
import static fixture.PositionFixture.F7;
import static fixture.PositionFixture.F8;
import static fixture.PositionFixture.G1;
import static fixture.PositionFixture.G2;
import static fixture.PositionFixture.G3;
import static fixture.PositionFixture.G4;
import static fixture.PositionFixture.G5;
import static fixture.PositionFixture.G6;
import static fixture.PositionFixture.G7;
import static fixture.PositionFixture.G8;
import static fixture.PositionFixture.H1;
import static fixture.PositionFixture.H2;
import static fixture.PositionFixture.H3;
import static fixture.PositionFixture.H4;
import static fixture.PositionFixture.H5;
import static fixture.PositionFixture.H6;
import static fixture.PositionFixture.H7;
import static fixture.PositionFixture.H8;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardCreator;
import chess.domain.square.Square;
import chess.domain.piece.Side;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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

        Map<Square, Piece> board = boardCreator.create();

        assertThat(board).containsExactly(
                Map.entry(A8, new Rook(Side.BLACK)),
                Map.entry(B8, new Knight(Side.BLACK)),
                Map.entry(C8, new Bishop(Side.BLACK)),
                Map.entry(D8, new Queen(Side.BLACK)),
                Map.entry(E8, new King(Side.BLACK)),
                Map.entry(F8, new Bishop(Side.BLACK)),
                Map.entry(G8, new Knight(Side.BLACK)),
                Map.entry(H8, new Rook(Side.BLACK)),

                Map.entry(A7, new Pawn(Side.BLACK)),
                Map.entry(B7, new Pawn(Side.BLACK)),
                Map.entry(C7, new Pawn(Side.BLACK)),
                Map.entry(D7, new Pawn(Side.BLACK)),
                Map.entry(E7, new Pawn(Side.BLACK)),
                Map.entry(F7, new Pawn(Side.BLACK)),
                Map.entry(G7, new Pawn(Side.BLACK)),
                Map.entry(H7, new Pawn(Side.BLACK)),

                Map.entry(A6, new Empty()),
                Map.entry(B6, new Empty()),
                Map.entry(C6, new Empty()),
                Map.entry(D6, new Empty()),
                Map.entry(E6, new Empty()),
                Map.entry(F6, new Empty()),
                Map.entry(G6, new Empty()),
                Map.entry(H6, new Empty()),

                Map.entry(A5, new Empty()),
                Map.entry(B5, new Empty()),
                Map.entry(C5, new Empty()),
                Map.entry(D5, new Empty()),
                Map.entry(E5, new Empty()),
                Map.entry(F5, new Empty()),
                Map.entry(G5, new Empty()),
                Map.entry(H5, new Empty()),

                Map.entry(A4, new Empty()),
                Map.entry(B4, new Empty()),
                Map.entry(C4, new Empty()),
                Map.entry(D4, new Empty()),
                Map.entry(E4, new Empty()),
                Map.entry(F4, new Empty()),
                Map.entry(G4, new Empty()),
                Map.entry(H4, new Empty()),

                Map.entry(A3, new Empty()),
                Map.entry(B3, new Empty()),
                Map.entry(C3, new Empty()),
                Map.entry(D3, new Empty()),
                Map.entry(E3, new Empty()),
                Map.entry(F3, new Empty()),
                Map.entry(G3, new Empty()),
                Map.entry(H3, new Empty()),

                Map.entry(A2, new Pawn(Side.WHITE)),
                Map.entry(B2, new Pawn(Side.WHITE)),
                Map.entry(C2, new Pawn(Side.WHITE)),
                Map.entry(D2, new Pawn(Side.WHITE)),
                Map.entry(E2, new Pawn(Side.WHITE)),
                Map.entry(F2, new Pawn(Side.WHITE)),
                Map.entry(G2, new Pawn(Side.WHITE)),
                Map.entry(H2, new Pawn(Side.WHITE)),

                Map.entry(A1, new Rook(Side.WHITE)),
                Map.entry(B1, new Knight(Side.WHITE)),
                Map.entry(C1, new Bishop(Side.WHITE)),
                Map.entry(D1, new Queen(Side.WHITE)),
                Map.entry(E1, new King(Side.WHITE)),
                Map.entry(F1, new Bishop(Side.WHITE)),
                Map.entry(G1, new Knight(Side.WHITE)),
                Map.entry(H1, new Rook(Side.WHITE))
        );
    }
}
