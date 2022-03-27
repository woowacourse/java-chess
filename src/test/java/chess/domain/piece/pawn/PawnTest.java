package chess.domain.piece.pawn;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Position;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class PawnTest {

    @Test
    void 위치만_같으면_동일한_인스턴스() {
        Pawn actual = new WhitePawn(Position.of("a2"));
        Pawn expected = new WhitePawn(Position.of("a2"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 색이_동일해도_위치가_다르면_별개의_인스턴스() {
        Pawn pawn1 = new WhitePawn(Position.of("a2"));
        Pawn pawn2 = new WhitePawn(Position.of("b2"));

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @Test
    void 색과_위치가_동일한_두_인스턴스의_해쉬코드는_동일() {
        int actual = new WhitePawn(Position.of("a2")).hashCode();
        int expected = new WhitePawn(Position.of("a2")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
