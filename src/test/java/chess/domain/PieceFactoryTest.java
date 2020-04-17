package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class PieceFactoryTest {

    @Test
    @DisplayName("PieceFactory가 제대로 32개의 말들을 생성하는지")
    void size() {
        PieceFactory pieceFactory = PieceFactory.create();
        Map<Position, Piece> pieces1 = pieceFactory.getPieces();
        Pieces pieces = new Pieces(pieces1);
        assertThat(pieces.getAlivePieces().size()).isEqualTo(32);
    }
}
