package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void 랭크는_8칸의_빈_스퀘어로_생성된다() {
        assertThat(new Rank())
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(Square.class))
                .hasSize(8);
    }
}
