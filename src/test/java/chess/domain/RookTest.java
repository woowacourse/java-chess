package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void getMovable() {
        Rook rook = Rook.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
            Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(rook, empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        CoordinatePair from = CoordinatePair.valueOf("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestStateInitiatorFactory(boardState))::getTeamAt, from)).containsExactlyInAnyOrder(
            CoordinatePair.valueOf("a2").get(),
            CoordinatePair.valueOf("a3").get(),
            CoordinatePair.valueOf("a4").get(),
            CoordinatePair.valueOf("a5").get(),
            CoordinatePair.valueOf("a6").get(),
            CoordinatePair.valueOf("a7").get(),
            CoordinatePair.valueOf("a8").get(),
            CoordinatePair.valueOf("b1").get(),
            CoordinatePair.valueOf("c1").get(),
            CoordinatePair.valueOf("d1").get(),
            CoordinatePair.valueOf("e1").get(),
            CoordinatePair.valueOf("f1").get(),
            CoordinatePair.valueOf("g1").get()
        );
    }

    @Test
    void ally_block() {
        Rook rook = Rook.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
            Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(rook, empty, empty, Rook.getInstance(Team.WHITE), empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        CoordinatePair from = CoordinatePair.valueOf("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestStateInitiatorFactory(boardState))::getTeamAt, from))
            .containsExactlyInAnyOrder(
                CoordinatePair.valueOf("a2").get(),
                CoordinatePair.valueOf("a3").get(),
                CoordinatePair.valueOf("b1").get(),
                CoordinatePair.valueOf("c1").get()
            );
    }
}