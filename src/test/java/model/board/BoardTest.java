package model.board;

import model.piece.Piece;
import model.piece.Queen;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void getPieceAtTestA() {
        assertThat(
                new Board().getPieceAt(Position.of("c7")).get().get1DCoord()
        ).isEqualTo(Position.of("c7").get1DCoord());
    }

    @Test
    void getPieceAtTestB() {
        assertThat(
                new Board().getPieceAt(Position.of("e1")).get().get1DCoord()
        ).isEqualTo(Position.of("e1").get1DCoord());
    }

    @Test
    void getPieceAtTestC() {
        assertThat(new Board().getPieceAt(Position.of("a3")).isPresent()).isEqualTo(false);
    }

    @Test
    void changeTypeTest() {
        Board b = new Board();
        Piece p = b.getPieceAt(Position.of("a2")).get();
        assertThat(p.hasNotMoved()).isTrue();
        assertThat(b.movePieceFromTo(Position.of("a2"), Position.of("a4"))).isTrue();
        assertThat(p.hasNotMoved()).isFalse();
        try {
            assertThat(b.changeTypeOfPieceAt(Position.of("a4"), Queen.class)).isTrue();
        } catch (Exception e) {
            System.out.println(p);
        }
        Piece q = b.getPieceAt(Position.of("a4")).get();
        assertThat(q.isPawn()).isFalse();
        assertThat(q.hasNotMoved()).isFalse();
        System.out.println(q);
    }
}