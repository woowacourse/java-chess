package chess.domain.piece;

import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.TestStateInitiatorFactory;
import chess.domain.Turn;
import chess.domain.coordinate.ChessCoordinate;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyCell;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void getMovable() {
        Rook rook = Rook.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(rook, empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        ChessCoordinate from = ChessCoordinate.valueOf("a1");

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn()).getBoard()::getTeamAt, from)).containsExactlyInAnyOrder(
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
        Rook rook = Rook.getInstance(Team.WHITE);

        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(rook, empty, empty, Rook.getInstance(Team.WHITE), empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        ChessCoordinate from = ChessCoordinate.valueOf("a1");

        assertThat(rook.getMovableCoordinates(new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn()).getBoard()::getTeamAt, from))
                .containsExactlyInAnyOrder(
                        ChessCoordinate.valueOf("a2"),
                        ChessCoordinate.valueOf("a3"),
                        ChessCoordinate.valueOf("b1"),
                        ChessCoordinate.valueOf("c1")
                );
    }
}