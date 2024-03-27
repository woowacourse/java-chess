package chess.model.game;

import chess.model.position.ChessPosition;
import chess.model.position.File;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommonValueTest {

    @Test
    @DisplayName("기물 가치에 따라 점수를 계산한다.")
    void calculateScore() {
        // given
        PieceValue pieceValue = new CommonValue(5);
        List<ChessPosition> chessPositions = List.of(
                ChessPosition.of(File.A, Rank.FOUR),
                ChessPosition.of(File.A, Rank.TWO),
                ChessPosition.of(File.C, Rank.FIVE)
        );

        // when
        double score = pieceValue.calculateScore(chessPositions);

        // then
        assertThat(score).isEqualTo(15);
    }
}
