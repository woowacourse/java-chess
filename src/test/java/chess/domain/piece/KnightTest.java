package chess.domain.piece;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.TestStateInitiatorFactory;
import chess.domain.Turn;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.piece.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @Test
    void getMovable() {
        Knight knight = Knight.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, knight, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());

        assertThat(knight.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b3"))).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("d2"),
                ChessCoordinate.valueOf("d4"),
                ChessCoordinate.valueOf("c5"),
                ChessCoordinate.valueOf("a5")
        );

    }
    @Test
    void getMovable_enemy() {
        Knight knight = Knight.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, knight, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());

        assertThat(knight.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b6"))).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a8"),
                ChessCoordinate.valueOf("c8"),
                ChessCoordinate.valueOf("d7"),
                ChessCoordinate.valueOf("d5"),
                ChessCoordinate.valueOf("a4"),
                ChessCoordinate.valueOf("c4")
        );

    }
}