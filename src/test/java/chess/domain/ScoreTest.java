package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @ParameterizedTest
    @CsvSource({"1,1","2,1","3,1.5","4,2","5,2.5"})
    @DisplayName("같은 세로줄에 폰이 있으면 각 0.5점씩 계산되어야 한다.")
    void getTeamScore_Same_X_Pawn(int pawnCount, double expect) {
        // given
        Map<Position, Piece> squares = BoardFactory.createEmptyBoard();
        for (int i = 1; i <= pawnCount; i++) {
            squares.put(Position.of(0, i), Pawn.of(Team.WHITE));
        }

        // when
        Score whiteScore = Score.of(squares);

        // then
        assertThat(whiteScore.getScore())
                .isEqualTo(expect);
    }
}
