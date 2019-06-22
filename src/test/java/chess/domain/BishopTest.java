package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @Test
    void getMovable() {
        Bishop bishop = Bishop.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
            Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty,
                empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, bishop, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );

        ChessGame board = new ChessGame(new TestBoardStateFactory(boardState));
        assertThat(bishop.getMovableCoordinates(board::getTeamAt, CoordinatePair.from("b4").get()))
            .containsExactlyInAnyOrder(
                CoordinatePair.from("a3").get(),
                CoordinatePair.from("a5").get(),
                CoordinatePair.from("c5").get(),
                CoordinatePair.from("d6").get(),
                CoordinatePair.from("e7").get(),
                CoordinatePair.from("f8").get(),
                CoordinatePair.from("c3").get(),
                CoordinatePair.from("d2").get(),
                CoordinatePair.from("e1").get()
            );

    }

}