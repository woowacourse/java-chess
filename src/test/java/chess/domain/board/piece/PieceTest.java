package chess.domain.board.piece;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PieceTest {

    @Test
    void 아군_공격_시도시_예외발생() {
        Piece knight = new NonPawn(WHITE, KNIGHT);
        Piece sameColorPiece = new NonPawn(WHITE, KNIGHT);

        Position position1 = Position.of("a1");
        Position position2 = Position.of("b3");
        assertThatThrownBy(() -> knight.canAttack(position1, position2, sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공격할 수 없는 대상입니다.");
    }

    @Test
    void 색과_타입만_같으면_동일한_인스턴스() {
        Piece actual = new NonPawn(WHITE, KNIGHT);
        Piece expected = new NonPawn(WHITE, KNIGHT);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 타입이_동일해도_색이_다르면_별개의_인스턴스() {
        Piece pawn1 = new Pawn(WHITE);
        Piece pawn2 = new Pawn(BLACK);

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @Test
    void 색과_위치가_동일한_두_인스턴스의_해쉬코드는_동일() {
        int actual = new NonPawn(WHITE, KNIGHT).hashCode();
        int expected = new NonPawn(WHITE, KNIGHT).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
