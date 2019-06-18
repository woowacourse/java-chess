package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RookTest {
    @Test
    void getMovable() {
        Rook rook = new Rook(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, empty, empty, empty, empty, empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(rook, empty, empty, empty, empty, empty, empty, new Rook(Team.WHITE))
        );

        ChessCoordinate from = ChessCoordinate.valueOf("a1");

        assertThat(rook.getMovableCoordinates(new ChessBoard(boardState)::getTeamAt, from)).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a2"),
                ChessCoordinate.valueOf("a3"),
                ChessCoordinate.valueOf("a4"),
                ChessCoordinate.valueOf("a5"),
                ChessCoordinate.valueOf("a6"),
                ChessCoordinate.valueOf("a7"),
                ChessCoordinate.valueOf("a8"),
                ChessCoordinate.valueOf("b1"),
                ChessCoordinate.valueOf("c1"),
                ChessCoordinate.valueOf("d1"),
                ChessCoordinate.valueOf("e1"),
                ChessCoordinate.valueOf("f1"),
                ChessCoordinate.valueOf("g1")
        );
    }

    @Test
    void ally_block() {
        Rook rook = new Rook(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, empty, empty, empty, empty, empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(rook, empty, empty, new Rook(Team.WHITE), empty, empty, empty, new Rook(Team.WHITE))
        );

        ChessCoordinate from = ChessCoordinate.valueOf("a1");

        assertThat(rook.getMovableCoordinates(new ChessBoard(boardState)::getTeamAt, from)).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a2"),
                ChessCoordinate.valueOf("a3"),
                ChessCoordinate.valueOf("b1"),
                ChessCoordinate.valueOf("c1")
        );
    }
}