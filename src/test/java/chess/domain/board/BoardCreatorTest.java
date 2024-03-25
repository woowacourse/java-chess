package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import chess.domain.piece.Side;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.fixture.PositionFixture;
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
                Map.entry(PositionFixture.A8, new Rook(Side.BLACK)),
                Map.entry(PositionFixture.B8, new Knight(Side.BLACK)),
                Map.entry(PositionFixture.C8, new Bishop(Side.BLACK)),
                Map.entry(PositionFixture.D8, new Queen(Side.BLACK)),
                Map.entry(PositionFixture.E8, new King(Side.BLACK)),
                Map.entry(PositionFixture.F8, new Bishop(Side.BLACK)),
                Map.entry(PositionFixture.G8, new Knight(Side.BLACK)),
                Map.entry(PositionFixture.H8, new Rook(Side.BLACK)),

                Map.entry(PositionFixture.A7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.B7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.C7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.D7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.E7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.F7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.G7, new Pawn(Side.BLACK)),
                Map.entry(PositionFixture.H7, new Pawn(Side.BLACK)),

                Map.entry(PositionFixture.A6, new Empty()),
                Map.entry(PositionFixture.B6, new Empty()),
                Map.entry(PositionFixture.C6, new Empty()),
                Map.entry(PositionFixture.D6, new Empty()),
                Map.entry(PositionFixture.E6, new Empty()),
                Map.entry(PositionFixture.F6, new Empty()),
                Map.entry(PositionFixture.G6, new Empty()),
                Map.entry(PositionFixture.H6, new Empty()),

                Map.entry(PositionFixture.A5, new Empty()),
                Map.entry(PositionFixture.B5, new Empty()),
                Map.entry(PositionFixture.C5, new Empty()),
                Map.entry(PositionFixture.D5, new Empty()),
                Map.entry(PositionFixture.E5, new Empty()),
                Map.entry(PositionFixture.F5, new Empty()),
                Map.entry(PositionFixture.G5, new Empty()),
                Map.entry(PositionFixture.H5, new Empty()),

                Map.entry(PositionFixture.A4, new Empty()),
                Map.entry(PositionFixture.B4, new Empty()),
                Map.entry(PositionFixture.C4, new Empty()),
                Map.entry(PositionFixture.D4, new Empty()),
                Map.entry(PositionFixture.E4, new Empty()),
                Map.entry(PositionFixture.F4, new Empty()),
                Map.entry(PositionFixture.G4, new Empty()),
                Map.entry(PositionFixture.H4, new Empty()),

                Map.entry(PositionFixture.A3, new Empty()),
                Map.entry(PositionFixture.B3, new Empty()),
                Map.entry(PositionFixture.C3, new Empty()),
                Map.entry(PositionFixture.D3, new Empty()),
                Map.entry(PositionFixture.E3, new Empty()),
                Map.entry(PositionFixture.F3, new Empty()),
                Map.entry(PositionFixture.G3, new Empty()),
                Map.entry(PositionFixture.H3, new Empty()),

                Map.entry(PositionFixture.A2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.B2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.C2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.D2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.E2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.F2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.G2, new Pawn(Side.WHITE)),
                Map.entry(PositionFixture.H2, new Pawn(Side.WHITE)),

                Map.entry(PositionFixture.A1, new Rook(Side.WHITE)),
                Map.entry(PositionFixture.B1, new Knight(Side.WHITE)),
                Map.entry(PositionFixture.C1, new Bishop(Side.WHITE)),
                Map.entry(PositionFixture.D1, new Queen(Side.WHITE)),
                Map.entry(PositionFixture.E1, new King(Side.WHITE)),
                Map.entry(PositionFixture.F1, new Bishop(Side.WHITE)),
                Map.entry(PositionFixture.G1, new Knight(Side.WHITE)),
                Map.entry(PositionFixture.H1, new Rook(Side.WHITE))
        );
    }
}
