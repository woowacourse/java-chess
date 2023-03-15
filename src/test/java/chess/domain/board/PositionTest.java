package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    @Test
    void Rank_와_File_을_받아_정상적으로_생성된다() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(Rank.A, File.ONE));
    }
}
