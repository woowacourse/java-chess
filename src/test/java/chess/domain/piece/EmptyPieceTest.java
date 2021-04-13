package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.pawn.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmptyPieceTest {

    private Piece emptyPiece;

    @BeforeEach
    void setUp() {
        emptyPiece = EmptyPiece.getInstance();
    }

    @Test
    @DisplayName("Empty 객체 생성된다.")
    void getInstanceTest() {
        assertThat(emptyPiece).isInstanceOf(EmptyPiece.class);
    }

    @Test
    void maxDistanceTest() {
        //given
        int emptyMaxDistance = emptyPiece.maxDistance();

        //then
        assertThat(emptyMaxDistance).isEqualTo(0);
    }

    @Test
    @DisplayName("EmptyPiece 는 isReachable 메서드를 사용할 수 없다.")
    void isReachableTest() {
        assertThatThrownBy(() -> {
            emptyPiece.isReachable(Position.of("a1"), Position.of("a2"), Pawn.getInstanceOf(Owner.WHITE));
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isEmptyPieceTest() {
        //given
        boolean isTrue = emptyPiece.isEmptyPiece();

        //then
        assertThat(isTrue).isTrue();
    }

    @Test
    void getSymbolTest() {
        //given
        String emptySymbol = emptyPiece.getSymbol();

        //then
        assertThat(emptySymbol).isEqualTo(".");
    }
}