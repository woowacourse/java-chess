package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @Test
    void getMovable() {
        Queen qn = Queen.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.BLACK), empty, empty, empty, Pawn.getInstance(Team.BLACK), empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), qn, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());
        assertThat(qn.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("d1")))
                .containsExactlyInAnyOrder(
                        ChessCoordinate.valueOf("d2"),
                        ChessCoordinate.valueOf("d3"),
                        ChessCoordinate.valueOf("e1"),
                        ChessCoordinate.valueOf("c2"),
                        ChessCoordinate.valueOf("b3"),
                        ChessCoordinate.valueOf("e2"),
                        ChessCoordinate.valueOf("f3")
                );
    }
}