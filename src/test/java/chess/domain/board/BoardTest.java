package chess.domain.board;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.E6;
import static chess.domain.piece.Fixture.E7;
import static chess.domain.piece.Fixture.E8;
import static chess.domain.piece.Fixture.PAWN_WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("초기 점수는 38점으로 계산된다.")
    void getScore() {
        final Board board = BoardFactory.newInstance();
        final Map<Color, Double> score = board.getScore();
        final double scoreForWhite = score.get(Color.WHITE);
        final double scoreForBlack = score.get(Color.BLACK);
        assertAll(
                () -> assertThat(scoreForWhite).isEqualTo(38.0),
                () -> assertThat(scoreForBlack).isEqualTo(38.0)
        );
    }

    @Test
    @DisplayName("같은 파일에 있는 폰이 2개 이상일 경우, 해당 폰들은 각 0.5점 처리된다.")
    void getScoreByFourPawnsInSameFile() {
        Map<Position, Piece> testBoard = new HashMap<>();
        testBoard.put(E4, PAWN_WHITE);
        testBoard.put(E6, PAWN_WHITE);
        testBoard.put(E8, PAWN_WHITE);
        testBoard.put(E7, PAWN_WHITE);
        final Board board = BoardFactory.newInstance(testBoard);

        final Map<Color, Double> score = board.getScore();
        final double scoreForWhite = score.get(Color.WHITE);
        assertThat(scoreForWhite).isEqualTo(2.0);
    }
}
