package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen 은 대각선으로 이동할 수 있다.")
    void moveQueenDiagonally() {
        Piece piece = new Queen(Player.WHITE);
        Position source = new Position(Row.TWO, Column.B);
        Position target = new Position(Row.THREE, Column.C);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }

    @Test
    @DisplayName("Queen 은 상하좌우로 이동할 수 있다.")
    void moveQueenUpDownRightLeft() {
        Piece piece = new Queen(Player.WHITE);
        Position source = new Position(Row.TWO, Column.B);
        Position target = new Position(Row.THREE, Column.B);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }
}
