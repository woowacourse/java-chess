package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chessboard.state.piece.Rook;
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

    @Test
    void 체스판은_각_기물을_규칙에_맞게_배치한다() {
        assertThat(new ChessBoard())
                .extracting("ranks")
                .asInstanceOf(InstanceOfAssertFactories.list(Rank.class))
                .first()
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(Square.class))
                .first()
                .extracting("pieceState")
                .isInstanceOf(Rook.class);
    }
}
