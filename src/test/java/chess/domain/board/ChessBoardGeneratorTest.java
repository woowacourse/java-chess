package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.type.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ChessBoardGeneratorTest {

    @DisplayName("컬러를 입력하면 해당 컬러의 피스들을 만들어준다")
    @Test
    void generatePiecesByColor() {
        // when
        ChessBoardGenerator chessBoardGenerator = ChessBoardGenerator.getInstance();
        Map<Position, Piece> pieces = chessBoardGenerator.generate();

        // then
        assertThat(pieces).hasSize(32);

        assertThat(countPiece(pieces, Pawn.class)).isEqualTo(16);
        assertThat(countPiece(pieces, Rook.class)).isEqualTo(4);
        assertThat(countPiece(pieces, Knight.class)).isEqualTo(4);
        assertThat(countPiece(pieces, Bishop.class)).isEqualTo(4);
        assertThat(countPiece(pieces, King.class)).isEqualTo(2);
        assertThat(countPiece(pieces, Queen.class)).isEqualTo(2);
    }

    private int countPiece(Map<Position, Piece> pieces, Class<? extends Piece> pieceClass) {
        return (int) pieces.values().stream()
                .filter(pieceClass::isInstance)
                .count();
    }
}
