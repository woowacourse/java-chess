package chess.domain.position;

import static chess.domain.position.File.A;
import static chess.domain.position.Rank.ONE;
import static chess.domain.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.movepattern.PawnMovePattern;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    @Test
    void 위치는_캐싱된_값만_가져오기_때문에_값이_같다면_항상_동일하다() {
        final Position position = Position.of(A, ONE);
        final Position comparedPosition = Position.of(A, ONE);

        assertThat(position).isEqualTo(comparedPosition);
    }

    @Test
    void 위치를_움직일_수_있다() {
        final Position position = Position.of(A, ONE);

        final Position movedPosition = position.move(PawnMovePattern.UP);

        assertThat(movedPosition).isEqualTo(Position.of(A, TWO));
    }

    @Test
    void 위치를_더_움직일_수_없다면_원래_위치를_반환한다() {
        final Position position = Position.of(A, ONE);

        final Position movedPosition = position.move(PawnMovePattern.DOWN);

        assertThat(movedPosition).isEqualTo(position);
    }
}
