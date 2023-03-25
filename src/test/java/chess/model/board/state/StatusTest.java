package chess.model.board.state;

import static chess.controller.GameCommand.STATUS;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.MoveDaoImpl;
import chess.dao.MoveTruncator;
import chess.model.Score;
import chess.model.Scores;
import chess.model.piece.PieceColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest extends MoveTruncator {

    @Test
    @DisplayName("calculateScores() 호출하면 첨수가 반환된다.")
    void calculateScores_whenCall_thenReturnScores() {
        // given
        final GameState status = ProgressState.of(STATUS, new MoveDaoImpl());

        // when
        final Scores scores = status.calculateScores();

        // then
        final List<Score> scoreBoards = scores.getScoreBoard();

        assertAll(
                () -> assertThat(mapPieceColors(scoreBoards))
                        .containsExactly(WHITE, BLACK),
                () -> assertThat(mapScores(scoreBoards))
                        .containsExactly(38.0, 38.0)
                );

    }

    private static List<PieceColor> mapPieceColors(final List<Score> scoreBoard) {
        return scoreBoard.stream()
                .map(Score::getColor)
                .collect(toList());
    }

    private static List<Double> mapScores(final List<Score> scoreBoards) {
        return scoreBoards.stream()
                .map(Score::getScore)
                .collect(toList());
    }
}
