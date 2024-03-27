package chess.model.game;

import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PawnValueTest {

    @Test
    @DisplayName("Pawn의 기물 가치는 1점이다. 단, 같은 세로줄에 있으면 각 0.5점으로 계산한다.")
    void calculateScore() {
        // given
        PieceValue pawnValue = new PawnValue(1, 0.5);
        List<ChessPosition> chessPositions = List.of(
                ChessPosition.of(File.A, Rank.FOUR),
                ChessPosition.of(File.A, Rank.TWO),
                ChessPosition.of(File.C, Rank.FIVE)
        );

        // when
        double score = pawnValue.calculateScore(chessPositions);

        // then
        assertThat(score).isEqualTo(2);
    }
}
