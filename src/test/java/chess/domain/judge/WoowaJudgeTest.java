package chess.domain.judge;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Side;
import chess.domain.view.OutputView;

class WoowaJudgeTest {

    @DisplayName("점수 계산 테스트")
    @Test
    void calculateScore() {
        WoowaJudge judge = new WoowaJudge(Board.init());
        assertThat(judge.calculateScore(Side.BLACK)).isEqualTo(38);
    }

    @DisplayName("게임 종료 확인 테스트")
    @Test
    void isGameOver() {
        Board board = Board.init();
        WoowaJudge judge = new WoowaJudge(board);
        board.move(Position.of("c2"), Position.of("c3"));
        board.move(Position.of("d7"), Position.of("d6"));
        board.move(Position.of("d1"), Position.of("a4"));
        board.move(Position.of("a4"), Position.of("e8"));
        OutputView.showBoard(board);
        assertThat(judge.isGameOver()).isTrue();
    }

    @DisplayName("점수 비교 테스트")
    @Test
    void higherScorer() {
        Board board = Board.init();
        WoowaJudge judge = new WoowaJudge(board);
        assertThat(judge.winner()).isEmpty();
        board.move(Position.of("b2"), Position.of("b3"));
        board.move(Position.of("c1"), Position.of("a3"));
        board.move(Position.of("a3"), Position.of("e7"));
        assertThat(judge.winner()).isEqualTo(Optional.of(Side.WHITE));
    }
}
