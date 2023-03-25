package domain.piece;

import domain.game.PieceType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {
    @DisplayName("white pawn을 생성한다.")
    @Test
    void whitePawnFactoryTest() {
        //given
        PieceFactory pieceFactory = new PieceFactory();
        Piece whitePawn = pieceFactory.create(PieceType.PAWN, Side.WHITE);
        //then
        Assertions.assertThat(whitePawn).isEqualTo(new Pawn(Side.WHITE));
    }

    @DisplayName("black pawn을 생성한다.")
    @Test
    void blackPawnFactoryTest() {
        //given
        PieceFactory pieceFactory = new PieceFactory();
        Piece whitePawn = pieceFactory.create(PieceType.PAWN, Side.BLACK);
        //then
        Assertions.assertThat(whitePawn).isEqualTo(new Pawn(Side.BLACK));
    }

    @DisplayName("white knight를 생성한다.")
    @Test
    void whiteKnightFactoryTest() {
        //given
        PieceFactory pieceFactory = new PieceFactory();
        Piece whitePawn = pieceFactory.create(PieceType.KNIGHT, Side.WHITE);
        //then
        Assertions.assertThat(whitePawn).isEqualTo(new Knight(Side.WHITE));
    }

    @DisplayName("black knight를 생성한다.")
    @Test
    void blackKnightFactoryTest() {
        //given
        PieceFactory pieceFactory = new PieceFactory();
        Piece whitePawn = pieceFactory.create(PieceType.PAWN, Side.WHITE);
        //then
        Assertions.assertThat(whitePawn).isEqualTo(new Pawn(Side.WHITE));
    }

    @DisplayName("white pawn을 생성한다.")
    @Test
    void emptyPieceFactoryTest() {
        //given
        PieceFactory pieceFactory = new PieceFactory();
        Piece whitePawn = pieceFactory.create(PieceType.EMPTY_PIECE, Side.NEUTRAL);
        //then
        Assertions.assertThat(whitePawn).isEqualTo(new EmptyPiece());
    }
}