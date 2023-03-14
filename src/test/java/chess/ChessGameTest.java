package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Piece;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("기물 32개를 생성한다.")
    void generateInitialPieces() {
        // given
        final ChessGame chessGame = new ChessGame();

        // when
        List<Piece> pieces = chessGame.generateInitialPieces();

        // then
        assertThat(pieces.size()).isEqualTo(32);
    }
}
