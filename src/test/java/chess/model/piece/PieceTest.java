package chess.model.piece;

import chess.model.game.CommonValue;
import chess.model.game.PieceValue;
import chess.model.position.ChessPosition;
import chess.model.position.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    @Test
    @DisplayName("같은 진영인지 확인한다.")
    void isSameSide() {
        // given
        Piece piece = new Piece(Side.BLACK) {
            @Override
            public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
                return null;
            }

            @Override
            public PieceValue value() {
                return new CommonValue(0);
            }
        };
        Piece other = new Piece(Side.BLACK) {
            @Override
            public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
                return null;
            }

            @Override
            public PieceValue value() {
                return new CommonValue(0);
            }
        };

        // when
        boolean result = piece.isSameSide(other);

        // then
        assertThat(result).isTrue();
    }
}
