package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void getMovable() {
        King king = new King(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, new Pawn(Team.WHITE), empty, empty, empty, empty),
                Arrays.asList(empty, new Pawn(Team.BLACK), empty, empty, empty, new Pawn(Team.BLACK), empty, empty),
                Arrays.asList(empty, new Pawn(Team.WHITE), empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), new Queen(Team.WHITE), king, empty, empty, new Rook(Team.WHITE))
        );
        ChessBoard board = new ChessBoard(boardState);
        assertThat(king.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("e1").get()))
                .containsExactlyInAnyOrder(
                        ChessCoordinate.valueOf("d2").get(),
                        ChessCoordinate.valueOf("e2").get(),
                        ChessCoordinate.valueOf("f2").get(),
                        ChessCoordinate.valueOf("f1").get()
                );
    }
}