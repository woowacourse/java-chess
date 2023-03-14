package chess;

import chess.piece.Piece;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ChessboardTest {
    @DisplayName("체스판은 64개의 Square로 이루어진다.")
    @Test
    void createChessboardSuccessTest() {
        Assertions.assertThat(new Chessboard())
                .extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Square.class, Piece.class))
                .hasSize(64);
    }
}
