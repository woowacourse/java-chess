package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    void getMovable() {
        Pawn pawn = Pawn.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());

        assertThat(pawn.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b2"))).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("b3"),
                ChessCoordinate.valueOf("b4")
        );

    }

    @Test
    void frontIsNotEmpty() {
        Pawn pawn = Pawn.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());

        assertThat(pawn.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b4"))).isEmpty();
    }

    @Test
    void getMovable_enemy() {
        Pawn pawn = Pawn.getInstance(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Pawn.getInstance(Team.BLACK), Pawn.getInstance(Team.BLACK), Pawn.getInstance(Team.BLACK), empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());

        assertThat(pawn.getMovableCoordinates(board.getBoard()::getTeamAt, ChessCoordinate.valueOf("b4"))).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a5"),
                ChessCoordinate.valueOf("c5")
        );
    }
}