package chess.domain;

import chess.domain.coordinate.ChessCoordinate;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyCell;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static chess.domain.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;


class ChessGameTest {

    @Test
    void initBoard() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        List<List<PieceType>> expectedBoardState = Arrays.asList(
                Arrays.asList(ROOK_BLACK, NONE, NONE, NONE, NONE, NONE, NONE, ROOK_BLACK),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(ROOK_WHITE, NONE, NONE, NONE, NONE, NONE, NONE, ROOK_WHITE)
        );

        ChessGame chessGame = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());
        Board board = chessGame.getBoard();
        ChessCoordinate.forEachCoordinate(coord -> assertThat(board.getBoardState().get(coord))
                .isEqualTo(expectedBoardState.get(coord.getY().getIndex()).get(coord.getX().getIndex())));
    }

    @Test
    void move() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        List<List<ChessPiece>> toBoardState = Arrays.asList(
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, Rook.getInstance(Team.WHITE))
        );

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState), Turn.firstTurn());
        ChessCoordinate from = ChessCoordinate.valueOf("a1");
        ChessCoordinate to = ChessCoordinate.valueOf("a8");
        board.move(from, to);
        assertThat(board.getBoard()).isEqualTo(new ChessGame(new TestStateInitiatorFactory(toBoardState), Turn.firstTurn()).getBoard());
    }

}