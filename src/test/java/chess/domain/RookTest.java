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

        CoordinatePair from = CoordinatePair.from("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestBoardStateFactory(boardState))::getTeamAt, from)).containsExactlyInAnyOrder(
            CoordinatePair.from("a2").get(),
            CoordinatePair.from("a3").get(),
            CoordinatePair.from("a4").get(),
            CoordinatePair.from("a5").get(),
            CoordinatePair.from("a6").get(),
            CoordinatePair.from("a7").get(),
            CoordinatePair.from("a8").get(),
            CoordinatePair.from("b1").get(),
            CoordinatePair.from("c1").get(),
            CoordinatePair.from("d1").get(),
            CoordinatePair.from("e1").get(),
            CoordinatePair.from("f1").get(),
            CoordinatePair.from("g1").get()
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

        CoordinatePair from = CoordinatePair.from("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestBoardStateFactory(boardState))::getTeamAt, from))
            .containsExactlyInAnyOrder(
                CoordinatePair.from("a2").get(),
                CoordinatePair.from("a3").get(),
                CoordinatePair.from("b1").get(),
                CoordinatePair.from("c1").get()
            );
    }
}