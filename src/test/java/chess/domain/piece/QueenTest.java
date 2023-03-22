package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.Pawn.from;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class QueenTest {

    @Test
    void 퀸이_정상적으로_생성된다() {
        // given
        final Queen queen = Queen.from(Color.WHITE);

        // expect
        assertThat(queen.type()).isEqualTo(PieceType.QUEEN);
    }

    @ParameterizedTest(name = "퀸이 WHITE일 때 가려는 위치에 있는 기물이 {0}인 경우 움직임 가능 여부: {1}")
    @CsvSource({"WHITE, false", "BLACK, true", "EMPTY, true"})
    void 아군의_위치로_이동_가능_여부를_확인한다(final Color color, final boolean result) {
        // given
        final Queen queen = Queen.from(WHITE);

        // when
        final boolean movable = queen.isValidTarget(from(color));

        // then
        assertThat(movable).isEqualTo(result);
    }

    @ParameterizedTest(name = "퀸이 해당 위치로 움직일 수 있다. 현재 위치: D, FOUR, 이동 위치: {0}, {1}")
    @CsvSource({
            "C, FIVE", "D, FIVE", "E, FIVE", "C, FOUR", "E, FOUR", "C, THREE", "D, THREE", "E, THREE",
            "A, SEVEN", "A, FOUR", "A, ONE", "D, ONE", "G, ONE", "H, FOUR", "H, EIGHT", "D, EIGHT"
    })
    void 퀸이_해당_위치로_움직일_수_있다(final File file, final Rank rank) {
        // given
        final Queen queen = Queen.from(WHITE);
        final Position start = Position.of(File.D, Rank.FOUR);

        // when
        final boolean result = queen.isValidMove(start, Position.of(file, rank));

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "퀸이 해당 위치로 움직일 수 없다. 현재 위치: G, SEVEN, 이동 위치: {0}, {1}")
    @CsvSource({"H, FIVE", "F, FIVE", "E, SIX", "E, EIGHT", "F, THREE", "H, THREE", "C, SIX", "C, EIGHT"})
    void 퀸이_해당_위치로_움직일_수_없다(final File file, final Rank rank) {
        // given
        final Queen queen = Queen.from(WHITE);
        final Position start = Position.of(File.G, Rank.SEVEN);

        // when
        final boolean result = queen.isValidMove(start, Position.of(file, rank));

        // then
        assertThat(result).isFalse();
    }
}
