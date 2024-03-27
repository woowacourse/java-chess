package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessPieceStartingPositionTest {

    ChessPieceStartingPosition chessPieceStartingPosition = ChessPieceStartingPosition.getInstance();

    @Test
    void 주어진_칸에서_시작하는_말의_타입을_반환한다() {
        //given
        Square square1 = new Square(Lettering.A, Numbering.ONE);
        Square square2 = new Square(Lettering.A, Numbering.TWO);
        Square square3 = new Square(Lettering.C, Numbering.ONE);
        Square square4 = new Square(Lettering.D, Numbering.ONE);

        //when
        ChessPieceType expectedRook = chessPieceStartingPosition.determineChessPieceType(square1);
        ChessPieceType expectedPawn = chessPieceStartingPosition.determineChessPieceType(square2);
        ChessPieceType expectedBishop = chessPieceStartingPosition.determineChessPieceType(square3);
        ChessPieceType expectedQueen = chessPieceStartingPosition.determineChessPieceType(square4);

        //then
        assertAll(
                () -> assertThat(expectedRook).isEqualTo(ChessPieceType.ROOK),
                () -> assertThat(expectedPawn).isEqualTo(ChessPieceType.PAWN),
                () -> assertThat(expectedBishop).isEqualTo(ChessPieceType.BISHOP),
                () -> assertThat(expectedQueen).isEqualTo(ChessPieceType.QUEEN)
        );
    }

    @Test
    void 주어진_칸에서_시작하는_말이_없는경우_말의_타입은_NONE_이다() {
        //given
        Square square = new Square(Lettering.A, Numbering.THREE);

        //when
        ChessPieceType expectedNone = chessPieceStartingPosition.determineChessPieceType(square);

        //then
        assertThat(expectedNone).isEqualTo(ChessPieceType.NONE);
    }
}
