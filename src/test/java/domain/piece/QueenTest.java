package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen 은 대각선으로 이동할 수 있다.")
    void moveQueenDiagonally() {
        Piece piece = new Queen(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }

    @Test
    @DisplayName("Queen 은 상하좌우로 이동할 수 있다.")
    void moveQueenUpDownRightLeft() {
        Piece piece = new Queen(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.THREE);

        assertThat(piece.isAvailableMove(source, target)).isEqualTo(true);
    }
}
