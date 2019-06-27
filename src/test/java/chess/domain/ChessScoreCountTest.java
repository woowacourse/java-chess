package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyCell;
import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ChessScoreCountTest {

    @Test
    void calculate() {
        ChessPiece empty = EmptyCell.getInstance();
        List<List<ChessPiece>> boardState = Arrays.asList(
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, Pawn.getInstance(Team.BLACK), Pawn.getInstance(Team.BLACK), empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Pawn.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Pawn.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(empty, empty, empty, empty, empty, empty, empty, empty),
                Arrays.asList(Rook.getInstance(Team.WHITE), empty, empty, empty, empty, empty, empty, empty)
        );


        ChessScoreCount scoreCount = new ChessScoreCount(new Board(new TestStateInitiatorFactory(boardState)));

        assertThat(scoreCount.getScore(Team.BLACK)).isEqualTo(2);
        assertThat(scoreCount.getScore(Team.WHITE)).isEqualTo(6);
    }
}