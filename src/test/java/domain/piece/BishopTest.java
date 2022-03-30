package domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Player;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 이동할 수 있다.")
    void moveBishopDiagonally() {
        Piece piece = new Bishop(Player.WHITE);
        Position source = Position.of(File.B, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);

        piece.generateAvailablePosition(source);
        assertDoesNotThrow(() -> piece.getAvailablePositions(source, target));
    }
}
