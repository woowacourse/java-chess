package chess.model.board;

import static chess.model.board.PieceFixture.A1;
import static chess.model.board.PieceFixture.B2;
import static chess.model.board.PieceFixture.BLACK_KNIGHT;
import static chess.model.board.PieceFixture.WHITE_BISHOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.piece.PieceType;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceSquareTest {

    private PieceSquare pieceSquare;

    @BeforeEach
    void init() {
        pieceSquare = new PieceSquare(A1, WHITE_BISHOP);
    }

    @Test
    @DisplayName("기물이 있는 체스판 칸에 대한 객체를 생성한다")
    void constructor_givenPositionAndPiece_thenSuccess() {
        assertThat(pieceSquare).isExactlyInstanceOf(PieceSquare.class);
    }

    @Test
    @DisplayName("receivePiece()는 기물을 바꿔준다.")
    void receivePiece_() {
        // when
        final Square nextSquare = pieceSquare.receivePiece(BLACK_KNIGHT);

        // then
        assertAll(
            () -> assertThat(nextSquare).isExactlyInstanceOf(PieceSquare.class),
            () -> assertThat(nextSquare.getType()).isSameAs(PieceType.KNIGHT)
        );
    }

    @Test
    @DisplayName("movePiece()는 position을 전달하면 기물을 옮겨준다.")
    void movePiece_givenPosition_thenReturnPieceSquare() {
        // when
        final Square result = pieceSquare.movePiece(B2);

        // then
        assertAll(
            () -> assertThat(result).isExactlyInstanceOf(PieceSquare.class),
            () -> assertThat(result)
                .extracting("position", InstanceOfAssertFactories.type(Position.class))
                .isEqualTo(B2)
        );
    }

    @Test
    @DisplayName("removeSquare()는 호출하면 동일한 위치의 emptySquare를 반환한다.")
    void removeSquare_whenCall_thenReturnEmptySquare() {
        // when
        final Square emptySquare = pieceSquare.removePiece();

        // then
        assertAll(
            () -> assertThat(emptySquare).isExactlyInstanceOf(EmptySquare.class),
            () -> assertThat(emptySquare)
                .extracting("position", InstanceOfAssertFactories.type(Position.class))
                .isEqualTo(A1)
        );
    }
}
