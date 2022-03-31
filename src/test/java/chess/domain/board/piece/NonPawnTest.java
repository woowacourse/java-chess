package chess.domain.board.piece;

import static chess.domain.board.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class NonPawnTest {

    @Test
    void 나이트는_상하좌우_한칸_이동_후_해당_방향의_대각선으로_한칸_이동가능() {
        Piece knight = new NonPawn(WHITE, PieceType.KNIGHT);

        Position from = Position.of("a3");
        Position to = Position.of("b5");

        boolean actual = knight.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 비숍은_대각선으로_자유롭게_이동가능() {
        Piece bishop = new NonPawn(WHITE, PieceType.BISHOP);

        Position from = Position.of("a1");
        Position to = Position.of("d4");

        boolean actual = bishop.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 룩은_수직방향으로_자유롭게_이동가능() {
        Piece rook = new NonPawn(WHITE, PieceType.ROOK);

        Position from = Position.of("a1");
        Position to = Position.of("a7");

        boolean actual = rook.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 퀸은_대각선으로_자유롭게_이동가능() {
        Piece queen = new NonPawn(WHITE, PieceType.QUEEN);

        Position from = Position.of("a1");
        Position to = Position.of("d4");

        boolean actual = queen.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 퀸은_수직방향으로_자유롭게_이동가능() {
        Piece queen = new NonPawn(WHITE, PieceType.QUEEN);

        Position from = Position.of("a1");
        Position to = Position.of("a5");

        boolean actual = queen.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 킹은_자유롭게_한칸_이동가능() {
        Piece king = new NonPawn(WHITE, PieceType.KING);

        Position from = Position.of("a1");
        Position to = Position.of("a2");

        boolean actual = king.canMove(from, to);

        assertThat(actual).isTrue();
    }

    @Test
    void 킹은_두칸_이상은_이동불가() {
        Piece king = new NonPawn(WHITE, PieceType.KING);

        Position from = Position.of("a1");
        Position to = Position.of("a3");

        boolean actual = king.canMove(from, to);

        assertThat(actual).isFalse();
    }
}
