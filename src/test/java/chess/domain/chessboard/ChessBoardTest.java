package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {

    @Test
    void 체스판은_8줄의_랭크를_가진다() {
        assertThat(new ChessBoard())
                .extracting("ranks")
                .asInstanceOf(InstanceOfAssertFactories.list(Rank.class))
                .hasSize(8);
    }
}
