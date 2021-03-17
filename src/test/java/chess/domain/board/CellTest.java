package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.player.PlayerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CellTest {
    @DisplayName("Cell 생성 테스트")
    @Test
    void createCell() {
        Coordinate coordinate = new Coordinate(File.C, Rank.EIGHT);

        assertThatCode(() -> new Cell(coordinate))
            .doesNotThrowAnyException();
    }

    @DisplayName("Cell setter/getter 테스트")
    @Test
    void cellSetterGetter() {
        Coordinate coordinate = new Coordinate(File.C, Rank.EIGHT);
        Cell cell = new Cell(coordinate);

        assertThat(cell.isEmpty()).isTrue();

        Piece king = new King(PlayerType.WHITE);
        cell.put(king);
        assertThat(cell.getPiece().getName()).isEqualTo("K");
    }
}