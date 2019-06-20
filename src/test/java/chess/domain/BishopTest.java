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

        ChessGame board = new ChessGame(boardState);
        assertThat(bishop.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b4").get())).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a3").get(),
                ChessCoordinate.valueOf("a5").get(),
                ChessCoordinate.valueOf("c5").get(),
                ChessCoordinate.valueOf("d6").get(),
                ChessCoordinate.valueOf("e7").get(),
                ChessCoordinate.valueOf("f8").get(),
                ChessCoordinate.valueOf("c3").get(),
                ChessCoordinate.valueOf("d2").get(),
                ChessCoordinate.valueOf("e1").get()
        );

    }

}