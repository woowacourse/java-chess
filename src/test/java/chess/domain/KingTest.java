package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @Test
    void getMovable() {
        King king = King.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.BLACK), empty, empty, empty, Pawn.getInstance(Team.BLACK), empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), Queen.getInstance(Team.WHITE), king, empty, empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(boardState);
        assertThat(king.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("e1").get()))
                .containsExactlyInAnyOrder(
                        ChessCoordinate.valueOf("d2").get(),
                        ChessCoordinate.valueOf("e2").get(),
                        ChessCoordinate.valueOf("f2").get(),
                        ChessCoordinate.valueOf("f1").get()
                );
    }
}