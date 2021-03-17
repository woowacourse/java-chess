package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @DisplayName("Pawn 객체 생성 확인")
    @Test
    void 폰_객체_생성() {
        Pawn pawn = new Pawn(Position.of('a', '7'), "P");

        assertThat(pawn.getPosition()).isEqualTo(Position.of('a', '7'));
        assertThat(pawn.getName()).isEqualTo("P");
    }

    @DisplayName("초기화된 Pawn 객체들 생성 확인")
    @Test
    void 폰_객체들_생성() {
        List<Pawn> pawns = Pawn.generate();

        assertThat(pawns.size()).isEqualTo(16);
    }
}
