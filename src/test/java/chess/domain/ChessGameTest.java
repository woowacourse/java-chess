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

        ChessGame chessGame = new ChessGame(new TestStateInitiatorFactory(boardState));
        ChessYCoordinate.allAscendingCoordinates()
                .forEach(y ->
                        ChessXCoordinate.allAscendingCoordinates().forEach(x -> {
                            assertThat(chessGame.getBoard().get(ChessCoordinate.valueOf(x, y).get()))
                                    .isEqualTo(expectedBoardState.get(y.getIndex()).get(x.getIndex()));
                        }));
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

        ChessGame board = new ChessGame(new TestStateInitiatorFactory(boardState));
        ChessCoordinate from = ChessCoordinate.valueOf("a1").get();
        ChessCoordinate to = ChessCoordinate.valueOf("a8").get();
        board.move(from, to);
        assertThat(board.getBoard()).isEqualTo(new ChessGame(new TestStateInitiatorFactory(toBoardState)).getBoard());
    }

    @Test
    void 같은_색_이동_X() {
//        ChessGame board = new ChessGame();
//        assertThrows(AllyExistException.class, () -> board.move(ChessCoordinate.valueOf("b2").get(), ChessCoordinate.valueOf("b1").get()));
    }
}