package chess.domain;

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

        ChessGame chessGame = new ChessGame(new TestBoardStateFactory(boardState));

        CoordinatePair.forEachCoordinate(coord -> assertThat(chessGame.getBoardState().get(coord))
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

        ChessGame board = new ChessGame(new TestBoardStateFactory(boardState));
        CoordinatePair from = CoordinatePair.from("a1").get();
        CoordinatePair to = CoordinatePair.from("a8").get();
        board.move(from, to);
        assertThat(board.getBoardState()).isEqualTo(new ChessGame(new TestBoardStateFactory(toBoardState)).getBoardState());
    }

}