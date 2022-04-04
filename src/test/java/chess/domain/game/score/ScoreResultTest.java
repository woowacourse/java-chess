package chess.domain.game.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreResultTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.createInitializedBoard();
    }

    @DisplayName("생성자에 Board를 전달 받아 생성된다.")
    @Test
    void constructor_returnsNewScoreResultWithBoard() {
        // given & when
        ScoreResult actual = new ScoreResult(board);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("생성된 ScoreResult는 전달된 Board 를 통해 각 팀의 점수로 초기화된다.")
    @ParameterizedTest
    @ValueSource(strings = {"WHITE", "BLACK"})
    void constructor_initializeScoreOfEachPieceColorByBoard(String input) {
        // given
        ScoreResult scoreResult = new ScoreResult(board);

        // when
        Score actual = scoreResult.getScoreByPieceColor(PieceColor.valueOf(input));

        // then
        assertThat(actual).isEqualTo(Score.from(38));
    }

    @DisplayName("폰이 같은 열에 있을 경우 해당 열의 모든 폰은 0.5점으로 계산된다.")
    @Test
    void constructor_withWhitePawnSameColumn() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.B, YAxis.SEVEN), Position.of(XAxis.B, YAxis.FIVE), PieceColor.BLACK);
        board.executeCommand(Position.of(XAxis.B, YAxis.FIVE), Position.of(XAxis.B, YAxis.FOUR), PieceColor.BLACK);
        board.executeCommand(Position.of(XAxis.B, YAxis.FOUR), Position.of(XAxis.B, YAxis.THREE), PieceColor.BLACK);
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.B, YAxis.THREE), PieceColor.WHITE);

        ScoreResult scoreResult = new ScoreResult(board);
        Score actual = scoreResult.getScoreByPieceColor(PieceColor.WHITE);

        // then
        assertThat(actual).isEqualTo(Score.from(37));
    }
}
