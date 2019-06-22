package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    void getMovable() {
        Queen qn = Queen.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
            Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty),
            Arrays.asList(empty, Pawn.getInstance(Team.BLACK), empty, empty, empty, Pawn.getInstance(Team.BLACK), empty, empty),
            Arrays.asList(empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty),
            Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), qn, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState));
        assertThat(qn.getMovableCoordinates(board::getTeamAt, CoordinatePair.valueOf("d1").get()))
            .containsExactlyInAnyOrder(
                CoordinatePair.valueOf("d2").get(),
                CoordinatePair.valueOf("d3").get(),
                CoordinatePair.valueOf("e1").get(),
                CoordinatePair.valueOf("c2").get(),
                CoordinatePair.valueOf("b3").get(),
                CoordinatePair.valueOf("e2").get(),
                CoordinatePair.valueOf("f3").get()
            );
    }
}