package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {
    @DisplayName("흰색 기물들을 초기화한다.")
    @Test
    void initializeWhitePiece() {
        assertThat(PieceFactory.initializeWhitePiece()).hasSize(16);
    }

    @DisplayName("검정색 기물들을 초기화한다.")
    @Test
    void initializeBlackPiece() {
        assertThat(PieceFactory.initializeBlackPiece()).hasSize(16);
    }

    @DisplayName("흰색 기물이 제대로 들어있는지 확인한다.")
    @Test
    void containsWhitePiece() {
        List<Piece> pieces = PieceFactory.initializeWhitePiece();
        Optional<Piece> bishop = pieces.stream()
                .filter(piece -> piece.equals(Bishop.from("b", Position.emptyPosition())))
                .findAny();
        Optional<Piece> king = pieces.stream()
                .filter(piece -> piece.equals(King.from("k", Position.emptyPosition())))
                .findAny();
        Optional<Piece> knight = pieces.stream()
                .filter(piece -> piece.equals(Knight.from("n", Position.emptyPosition())))
                .findAny();
        Optional<Piece> pawn = pieces.stream()
                .filter(piece -> piece.equals(Pawn.from("p", Position.emptyPosition())))
                .findAny();
        Optional<Piece> queen = pieces.stream()
                .filter(piece -> piece.equals(Queen.from("q", Position.emptyPosition())))
                .findAny();
        Optional<Piece> rook = pieces.stream()
                .filter(piece -> piece.equals(Rook.from("r", Position.emptyPosition())))
                .findAny();

        assertThat(bishop).isNotNull();
        assertThat(king).isNotNull();
        assertThat(knight).isNotNull();
        assertThat(pawn).isNotNull();
        assertThat(queen).isNotNull();
        assertThat(rook).isNotNull();
    }

    @DisplayName("검정색 기물이 제대로 들어있는지 확인한다.")
    @Test
    void containsBlackPiece() {
        List<Piece> pieces = PieceFactory.initializeWhitePiece();
        Optional<Piece> bishop = pieces.stream()
                .filter(piece -> piece.equals(Bishop.from("B", Position.emptyPosition())))
                .findAny();
        Optional<Piece> king = pieces.stream()
                .filter(piece -> piece.equals(King.from("K", Position.emptyPosition())))
                .findAny();
        Optional<Piece> knight = pieces.stream()
                .filter(piece -> piece.equals(Knight.from("N", Position.emptyPosition())))
                .findAny();
        Optional<Piece> pawn = pieces.stream()
                .filter(piece -> piece.equals(Pawn.from("P", Position.emptyPosition())))
                .findAny();
        Optional<Piece> queen = pieces.stream()
                .filter(piece -> piece.equals(Queen.from("Q", Position.emptyPosition())))
                .findAny();
        Optional<Piece> rook = pieces.stream()
                .filter(piece -> piece.equals(Rook.from("R", Position.emptyPosition())))
                .findAny();

        assertThat(bishop).isNotNull();
        assertThat(king).isNotNull();
        assertThat(knight).isNotNull();
        assertThat(pawn).isNotNull();
        assertThat(queen).isNotNull();
        assertThat(rook).isNotNull();
    }
}
