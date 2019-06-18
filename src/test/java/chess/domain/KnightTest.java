package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void getMovable() {
        Knight knight = new Knight(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, knight, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );

        ChessBoard board = new ChessBoard(boardState);

        assertThat(knight.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b3").get())).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("d2").get(),
                ChessCoordinate.valueOf("d4").get(),
                ChessCoordinate.valueOf("c5").get(),
                ChessCoordinate.valueOf("a5").get()
        );

    }
    @Test
    void getMovable_enemy() {
        Knight knight = new Knight(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, new Bishop(Team.BLACK), empty, empty, new Bishop(Team.BLACK), empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, knight, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, new Bishop(Team.WHITE), empty, empty, new Bishop(Team.WHITE), empty, new Rook(Team.WHITE))
        );

        ChessBoard board = new ChessBoard(boardState);

        assertThat(knight.getMovableCoordinates(board::getTeamAt, ChessCoordinate.valueOf("b6").get())).containsExactlyInAnyOrder(
                ChessCoordinate.valueOf("a8").get(),
                ChessCoordinate.valueOf("c8").get(),
                ChessCoordinate.valueOf("d7").get(),
                ChessCoordinate.valueOf("d5").get(),
                ChessCoordinate.valueOf("a4").get(),
                ChessCoordinate.valueOf("c4").get()
        );

    }
}