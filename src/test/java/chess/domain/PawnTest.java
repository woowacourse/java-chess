package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void getMovable() {
        Pawn pawn = new Pawn(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );
        ChessBoard board = new ChessBoard(boardState);

        assertThat(pawn.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b2").get())).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("b3").get(),
                ChessCoordinate.valueOf("b4").get()
        );

    }

    @Test
    void frontIsNotEmpty() {
        Pawn pawn = new Pawn(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, new Pawn(Team.BLACK), empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );
        ChessBoard board = new ChessBoard(boardState);

        assertThat(pawn.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b4").get())).isEmpty();
    }

    @Test
    void getMovable_enemy() {
        Pawn pawn = new Pawn(Team.WHITE);
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Pawn(Team.BLACK), new Pawn(Team.BLACK), new Pawn(Team.BLACK), empty, empty, empty, empty, empty),
                Arrays.asList(empty, pawn, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );
        ChessBoard board = new ChessBoard(boardState);

        assertThat(pawn.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b4").get())).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a5").get(),
                ChessCoordinate.valueOf("c5").get()
        );
    }
}