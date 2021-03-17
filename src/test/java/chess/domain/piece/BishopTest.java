package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Location;
import chess.domain.board.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    @DisplayName("비숍 움직일 수 있는 좌표 확인")
    void movableLocationsTest() {
        Bishop bishop = new Bishop(Team.BLACK);
        List<Location> locations = bishop.movableLocations(Location.of(Horizontal.D, Vertical.FOUR));

        assertThat(locations).containsExactly(
                Location.of(Horizontal.E, Vertical.FIVE),
                Location.of(Horizontal.F, Vertical.SIX),
                Location.of(Horizontal.G, Vertical.SEVEN),
                Location.of(Horizontal.H, Vertical.EIGHT),
                Location.of(Horizontal.C, Vertical.FIVE),
                Location.of(Horizontal.B, Vertical.SIX),
                Location.of(Horizontal.A, Vertical.SEVEN),
                Location.of(Horizontal.C, Vertical.THREE),
                Location.of(Horizontal.B, Vertical.TWO),
                Location.of(Horizontal.A, Vertical.ONE),
                Location.of(Horizontal.E, Vertical.THREE),
                Location.of(Horizontal.F, Vertical.TWO),
                Location.of(Horizontal.G, Vertical.ONE)
        );
    }
}