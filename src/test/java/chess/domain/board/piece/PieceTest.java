package chess.domain.board.piece;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static chess.domain.board.piece.PieceType.PAWN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PieceTest {

    @Test
    void 아군_공격_시도시_예외발생() {
        Piece knight = Piece.of(WHITE, KNIGHT);
        Piece sameColorPiece = Piece.of(WHITE, KNIGHT);

        Position position1 = Position.of("a1");
        Position position2 = Position.of("b3");
        assertThatThrownBy(() -> knight.canAttack(position1, position2, sameColorPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공격할 수 없는 대상입니다.");
    }

    @Test
    void of_메서드는_캐쉬를_반환() {
        Piece actual = Piece.of(WHITE, KNIGHT);
        Piece expected = Piece.of(WHITE, KNIGHT);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 타입이_동일해도_색이_다르면_별개의_인스턴스를_캐쉬로_반환() {
        Piece pawn1 = Piece.of(WHITE, PAWN);
        Piece pawn2 = Piece.of(BLACK, PAWN);

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @Test
    void 색이_동일해도_타입이_다르면_별개의_인스턴스를_캐쉬로_반환() {
        Piece blackPiece1 = Piece.of(BLACK, KNIGHT);
        Piece blackPiece2 = Piece.of(BLACK, PAWN);

        assertThat(blackPiece1).isNotEqualTo(blackPiece2);
    }
}
