package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void getMovable() {
        Queen qn = new Queen(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, new Pawn(Team.WHITE), empty, empty, empty, empty),
                Arrays.asList(empty, new Pawn(Team.BLACK), empty, empty, empty, new Pawn(Team.BLACK), empty, empty),
                Arrays.asList(empty, new Pawn(Team.WHITE), empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), qn, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );
        ChessBoard board = new ChessBoard(boardState);
        assertThat(qn.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("d1").get()))
                .containsExactlyInAnyOrder(
                        ChessCoordinate.valueOf("d2").get(),
                        ChessCoordinate.valueOf("d3").get(),
                        ChessCoordinate.valueOf("e1").get(),
                        ChessCoordinate.valueOf("c2").get(),
                        ChessCoordinate.valueOf("b3").get(),
                        ChessCoordinate.valueOf("e2").get(),
                        ChessCoordinate.valueOf("f3").get()
                );
    }
}