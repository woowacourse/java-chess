package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChessScoreCounterTest {

    @Test
    void calculate() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
            Arrays.asList(Rook.getInstance(Team.BLACK), empty, Bishop.getInstance(Team.BLACK), empty, empty, Bishop.getInstance(Team.BLACK), empty, Rook.getInstance(Team.BLACK)),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(Pawn.getInstance(Team.BLACK), Pawn.getInstance(Team.BLACK), Pawn.getInstance(Team.BLACK), empty, empty, empty, empty, empty),
            Arrays.asList(empty, Pawn.getInstance(Team.BLACK), empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
            Arrays.asList(Rook.getInstance(Team.WHITE), empty, Bishop.getInstance(Team.WHITE), empty, empty, Bishop.getInstance(Team.WHITE), empty, Rook.getInstance(Team.WHITE))
        );
        ChessGame board = new ChessGame(new TestBoardStateFactory(boardState));

        ChessScoreCounter scoreCounter = new ChessScoreCounter(board.getBoardState());
        assertThat(scoreCounter.getScore(Team.BLACK)).isEqualTo(19);
        assertThat(scoreCounter.getScore(Team.WHITE)).isEqualTo(16);
    }
}