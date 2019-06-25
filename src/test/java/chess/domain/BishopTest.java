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

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());
        assertThat(bishop.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b4"))).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a3"),
                ChessCoordinate.valueOf("a5"),
                ChessCoordinate.valueOf("c5"),
                ChessCoordinate.valueOf("d6"),
                ChessCoordinate.valueOf("e7"),
                ChessCoordinate.valueOf("f8"),
                ChessCoordinate.valueOf("c3"),
                ChessCoordinate.valueOf("d2"),
                ChessCoordinate.valueOf("e1")
        );

    }

}