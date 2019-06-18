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

        ChessCoordinate from = ChessCoordinate.valueOf("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessBoard(boardState)::getTeamAt, from)).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a2").get(),
                ChessCoordinate.valueOf("a3").get(),
                ChessCoordinate.valueOf("a4").get(),
                ChessCoordinate.valueOf("a5").get(),
                ChessCoordinate.valueOf("a6").get(),
                ChessCoordinate.valueOf("a7").get(),
                ChessCoordinate.valueOf("a8").get(),
                ChessCoordinate.valueOf("b1").get(),
                ChessCoordinate.valueOf("c1").get(),
                ChessCoordinate.valueOf("d1").get(),
                ChessCoordinate.valueOf("e1").get(),
                ChessCoordinate.valueOf("f1").get(),
                ChessCoordinate.valueOf("g1").get()
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

        ChessCoordinate from = ChessCoordinate.valueOf("a1").get();

        assertThat(rook.getMovableCoordinates(new ChessBoard(boardState)::getTeamAt, from)).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a2").get(),
                ChessCoordinate.valueOf("a3").get(),
                ChessCoordinate.valueOf("b1").get(),
                ChessCoordinate.valueOf("c1").get()
        );
    }
}