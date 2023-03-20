package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static chess.domain.piece.PawnTest.B4;
import static chess.domain.piece.PawnTest.E4;
import static chess.domain.piece.QueenTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    @Test
    void computePath_legal() {
        final var knight = new Knight(Color.BLACK);
        final var source = B4;
        final var target = D3;

        Set<Position> positions = knight.computePathWithValidate(source, target);

        assertThat(positions).containsExactlyInAnyOrder(D3);
    }

    @Test
    void computePath_legal2() {
        final var knight = new Knight(Color.BLACK);
        final var source = C4;
        final var target = E3;

        Set<Position> positions = knight.computePathWithValidate(source, target);

        assertThat(positions).containsExactlyInAnyOrder(E3);
    }

    @Test
    @DisplayName("잘못된 타겟이면 예외가 발생한다")
    void computePath_illegal_exception() {
        final var kngiht = new Knight(Color.BLACK);
        final var source = B4;
        final var target = E4;

        assertThatThrownBy(() -> kngiht.computePathWithValidate(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
