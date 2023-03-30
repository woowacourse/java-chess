package chess.domain;

import static chess.domain.piece.Shape.BISHOP;
import static chess.domain.piece.Shape.KING;
import static chess.domain.piece.Shape.PAWN;
import static chess.domain.piece.Shape.QUEEN;
import static chess.domain.piece.Shape.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class PiecesTest {

    Pieces whitePieces;
    Pieces blackPieces;

    @BeforeEach
    void setUp() {
        Pieces pieces = new Pieces();
        this.whitePieces = pieces.createWhitePieces();
        blackPieces = pieces.createBlackPieces();
    }

    @Test
    @DisplayName("체스 판 기물들이 올바른 개수로 생성되어야 한다.")
    void createWhitePieces() {
        // when, then
        assertAll(
                () -> {
                    long count = whitePieces.getShapeCount(PAWN);
                    assertThat(count).isEqualTo(8);
                },

                () -> {
                    long count = whitePieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(ROOK);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = whitePieces.getShapeCount(KING);
                    assertThat(count).isEqualTo(1);
                },

                () -> {
                    long count = whitePieces.getShapeCount(QUEEN);
                    assertThat(count).isEqualTo(1);
                }
        );
    }

    @Test
    @DisplayName("기물 리스트로 Pieces를 생성할 수 있다.")
    void createByPieceList() {
        List<Piece> elements = List.of(Piece.from(1, 'a', ROOK), Piece.from(2, 'a', PAWN));
        final var pieces = Pieces.from(elements);
        assertThat(pieces)
                .isNotNull()
                .isInstanceOf(Pieces.class);
    }

}
