package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Player;
import domain.directions.Direction;
import domain.position.Rank;
import domain.position.File;
import domain.position.Position;
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

        assertThat(piece.getDirection(target)).isEqualTo(Direction.NORTHEAST);
    }
}
