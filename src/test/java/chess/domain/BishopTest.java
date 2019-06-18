package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void getMovable() {
        Bishop bishop = new Bishop(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, bishop, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, empty, empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );

        ChessBoard board = new ChessBoard(boardState);
        assertThat(bishop.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b4"))).containsExactlyInAnyOrder(
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