package chess.domain.piece;

import chess.domain.piece.normal.Bishop;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {


    public static final Position B4 = new Position(File.B, Rank.FOUR);
    public static final Position E7 = new Position(File.E, Rank.SEVEN);
    public static final Position C5 = new Position(File.C, Rank.FIVE);
    public static final Position D6 = new Position(File.D, Rank.SIX);
    public static final Position G1 = new Position(File.G, Rank.ONE);
    public static final Position D4 = new Position(File.D, Rank.FOUR);
    public static final Position E3 = new Position(File.E, Rank.THREE);
    public static final Position F2 = new Position(File.F, Rank.TWO);
    public static final Position F8 = new Position(File.F, Rank.EIGHT);

    @Test
    @DisplayName("비숍이 갈 수 있는 경로를 계산한다.")
    void computePath_legal_set() {
        var bishop = new Bishop(Color.BLACK);
        final var source = B4;
        final var target = F8;

        Set<Position> positions = bishop.computePath(source, target);

        assertThat(positions).containsExactlyInAnyOrder(F8, E7, D6, C5);
    }

    @Test
    @DisplayName("비숍이 갈 수 있는 경로를 계산한다.")
    void computePath_legal_set2() {
        var bishop = new Bishop(Color.BLACK);
        final var source = C5;
        final var target = G1;

        Set<Position> positions = bishop.computePath(source, target);

        assertThat(positions).containsExactlyInAnyOrder(D4, E3, F2, G1);
    }
}
