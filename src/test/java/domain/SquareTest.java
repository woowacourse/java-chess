package domain;

import domain.chessboard.Empty;
import domain.chessboard.Square;
import domain.piece.Color;
import domain.piece.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SquareTest {

    @Test
    @DisplayName("beEmpty 메서드를 호출하면, 빈 칸 상태로 바뀐다.")
    void callBeEmpty_thenChangeEmptyStatus() {
        //given
        final Square square = new Square(new King(Color.WHITE));

        //when
        square.beEmpty();

        //then
        assertThat(square.getSquareStatus().getClass())
                .isEqualTo(Empty.class);
    }

    @Test
    @DisplayName("bePiece 메서드를 호출하면, 해당 기물의 상태로 바뀐다.")
    void callBePiece_thenChangeStatus() {
        //given
        final Square square = new Square(new Empty());

        //when
        square.bePiece(new King(Color.WHITE));

        //then
        assertThat(square.getSquareStatus().getClass())
                .isEqualTo(King.class);
    }

//    @Test
//    @DisplayName("기물을 캐싱한다.")
//    void createPiece_thenReturnCache() {
//        //given
//        Square square = Square.of(Type.KING, Color.BLACK);
//
//        //when
//
//        //then
//        assertThat(square == Square.of(Type.KING, Color.BLACK)).isTrue();
//    }
//    @Test
//    @DisplayName("타입과 색깔을 넣어주면 기물이 생성된다.")
//    void givenColorAndType_thenCreate() {
//        //then
//        assertThat(Square.of(Type.KING, Color.BLACK))
//                .extracting("type", "color")
//                .containsExactly(Type.KING, Color.BLACK);
//    }
}
