package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    @DisplayName("Rook 은 상하좌우로 이동할 수 있다.")
    void moveRookUpDownRightLeft() {
        Piece piece = new Rook(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.THREE);

        assertDoesNotThrow(() -> piece.move(source, target));
    }

    @Test
    @DisplayName("Rook은 Target을 상하좌우로 움직일 수 있다.")
    void moveRookUpDownRightLeftTarget() {
        Piece piece = new Rook(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.B, Rank.SEVEN);

        assertDoesNotThrow(() -> piece.move(source, target));
    }
}
