package domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import domain.pieces.Pieces;
import domain.pieces.PiecesFactory;
import domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDirectionTypeTest {

    @Test
    @DisplayName("King find 테스트")
    void find_King() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("e1"))).isEqualTo(Direction.getAllDirection());
    }

    @Test
    @DisplayName("Queen find 테스트")
    void find_Queen() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("d1"))).isEqualTo(Direction.getAllDirection());
    }

    @Test
    @DisplayName("Bishop find 테스트")
    void find_Bishop() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("c1"))).isEqualTo(Direction.getBishopDirection());
    }

    @Test
    @DisplayName("Knight find 테스트")
    void find_Knight() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("b1"))).isEqualTo(Direction.getKnightDirection());
    }

    @Test
    @DisplayName("Rook find 테스트")
    void find_Rook() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("a1"))).isEqualTo(Direction.getRookDirection());
    }

    @Test
    @DisplayName("Pawn find 테스트")
    void find_BlackPawn() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("a7"))).isEqualTo(Direction.getBlackPawnDirection());
    }

    @Test
    @DisplayName("Pawn find 테스트")
    void find_WhitePawn() {
        Pieces pieces = Pieces.of(PiecesFactory.create());
        assertThat(PieceDirectionType.find(pieces.getPieces(), Point.of("a2"))).isEqualTo(Direction.getWhitePawnDirection());
    }
}
