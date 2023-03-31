package domain.chessboard;

import domain.piece.Color;
import domain.piece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {

    @Test
    @DisplayName("liftPiece 메서드를 호출하면, 빈 칸 상태로 바뀐다.")
    void callBeEmpty_thenChangeEmptyStatus() {
        //given
        final Square square = new Square(new King(Color.WHITE));

        //when
        square.liftPiece();

        //then
        assertThat(square.getSquareStatus().getClass())
                .isEqualTo(Empty.class);
    }

    @Test
    @DisplayName("putPiece 메서드를 호출하면, 해당 기물의 상태로 바뀐다.")
    void callBePiece_thenChangeStatus() {
        //given
        final Square square = new Square(new Empty());

        //when
        square.putPiece(new Square(new King(Color.WHITE)));

        //then
        assertThat(square.getSquareStatus().getClass())
                .isEqualTo(King.class);
    }

}
