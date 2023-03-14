package chess.model.board;

import static chess.model.board.PieceFixture.A1;
import static chess.model.board.PieceFixture.B2;
import static chess.model.board.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptySquareTest {

    private EmptySquare emptySquare;

    @BeforeEach
    void init() {
        emptySquare = new EmptySquare(A1);
    }

    @Test
    @DisplayName("비어 있는 체스판 칸에 대한 객체를 생성한다")
    void constructor_givenPosition_thenSuccess() {
        assertThat(emptySquare).isExactlyInstanceOf(EmptySquare.class);
    }

    @Test
    @DisplayName("receivePiece()는 이동할 기물을 받으면 기물이 있는 상태로 바뀐다.")
    void receivePiece_givenPiece_thenReturnPieceSquare() {
        // when
        final Square pieceSquare = emptySquare.receivePiece(WHITE_PAWN);

        // then
        assertThat(pieceSquare.getType()).isSameAs(Type.PAWN);
    }

    @Test
    @DisplayName("movePiece()를 호출하면 예외가 발생한다")
    void movePiece_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(() -> emptySquare.movePiece(B2))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("removePiece()를 호출하면 예외가 발생한다")
    void removePiece_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(emptySquare::removePiece)
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("getType()을 호출하면 예외가 발생한다.")
    void getType_whenCall_thenFail() {
        // when, then
        assertThatThrownBy(emptySquare::getType)
            .isInstanceOf(UnsupportedOperationException.class);
    }
}
