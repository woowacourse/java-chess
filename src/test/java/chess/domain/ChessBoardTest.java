package chess.domain;

import chess.exception.AllyExistException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static chess.domain.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ChessBoardTest {

    @Test
    void initBoard() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, empty, empty, empty, empty, empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, empty, empty, empty, empty, empty, new Rook(Team.WHITE))
        );

        List<List<PieceType>> expectedBoardState = Arrays.asList(
                Arrays.asList(ROOK_BLACK, NONE, NONE, NONE, NONE, NONE, NONE, ROOK_BLACK),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE),
                Arrays.asList(ROOK_WHITE, NONE, NONE, NONE, NONE, NONE, NONE, ROOK_WHITE)
        );

        ChessBoard chessBoard = new ChessBoard(boardState);
        assertThat(chessBoard.getBoard()).isEqualTo(expectedBoardState);
    }

    @Test
    void move() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(new Rook(Team.BLACK), empty, empty, empty, empty, empty, empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(new Rook(Team.WHITE), empty, empty, empty, empty, empty, empty, new Rook(Team.WHITE))
        );

        List<List<ChessPiece>> toBoardState = Arrays.asList(
                Arrays.asList(new Rook(Team.WHITE), empty, empty, empty, empty, empty, empty, new Rook(Team.BLACK)),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, new Rook(Team.WHITE))
        );

        ChessBoard board = new ChessBoard(boardState);
        ChessCoordinate from = ChessCoordinate.valueOf("a1");
        ChessCoordinate to = ChessCoordinate.valueOf("a8");
        board.move(from, to);
        assertThat(board.getBoard()).isEqualTo(new ChessBoard(toBoardState).getBoard());
    }

    @Test
    void 같은_색_이동_X() {
//        ChessBoard board = new ChessBoard();
//        assertThrows(AllyExistException.class, () -> board.move(ChessCoordinate.valueOf("b2"), ChessCoordinate.valueOf("b1")));
    }
}