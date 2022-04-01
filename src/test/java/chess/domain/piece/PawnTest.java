package chess.domain.piece;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    @Test
    @DisplayName("출발위치와 도착위치가 주어지면 경로를 반환")
    void getPathTest() {
        Pawn pawn = new Pawn(Team.BLACK, List.of(ChessBoardPosition.ofDirection(0, -1)), Collections.emptyList());
        ChessBoardPosition source = ChessBoardPosition.of(1, 7);
        ChessBoardPosition target = ChessBoardPosition.of(1, 5);
        List<ChessBoardPosition> path = pawn.getPath(source, target);
        assertThat(path.get(0)).isEqualTo(ChessBoardPosition.of(1, 6));
    }
}
