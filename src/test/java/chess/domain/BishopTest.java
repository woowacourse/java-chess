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

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState));
        assertThat(bishop.getMovableCoordinates(board::getTeamAt, CoordinatePair.valueOf("b4").get()))
            .containsExactlyInAnyOrder(
                CoordinatePair.valueOf("a3").get(),
                CoordinatePair.valueOf("a5").get(),
                CoordinatePair.valueOf("c5").get(),
                CoordinatePair.valueOf("d6").get(),
                CoordinatePair.valueOf("e7").get(),
                CoordinatePair.valueOf("f8").get(),
                CoordinatePair.valueOf("c3").get(),
                CoordinatePair.valueOf("d2").get(),
                CoordinatePair.valueOf("e1").get()
            );

    }

}