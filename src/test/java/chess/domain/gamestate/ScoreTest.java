package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    private Board board;

    @BeforeEach
    void initializeBoard() {
        this.board = BoardInitializer.get();
        Camp.initializeTurn();
    }

    @DisplayName("초기 상태의 체스판에서 흑색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfBlack_38() {
        Score scoreOfWhite = Score.of(board).get(Camp.WHITE);
        assertThat(scoreOfWhite.getValue()).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 흑색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfBlack_37() {
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        Position d2 = Position.of(Column.D, Row.TWO);
        Position d4 = Position.of(Column.D, Row.FOUR);

        board.move(b2, b4);
        board.move(c7, c5);
        board.move(d2, d4);
        board.move(c5, b4);

        Score scoreOfBlack = Score.of(board).get(Camp.BLACK);
        assertThat(scoreOfBlack.getValue()).isEqualTo(37);
    }

    @DisplayName("초기 상태의 체스판에서 백색 진영의 점수는 38점이다.")
    @Test
    void calculateScoreOfWhite_38() {
        Score scoreOfWhite = Score.of(board).get(Camp.WHITE);
        assertThat(scoreOfWhite.getValue()).isEqualTo(38);
    }

    @DisplayName("체스판에서 두 pawn이 한 열에 있을 떄 백색 진영의 점수는 37점이다.")
    @Test
    void calculateScoreOfWhite_37() {
        Position b2 = Position.of(Column.B, Row.TWO);
        Position b4 = Position.of(Column.B, Row.FOUR);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        board.move(b2, b4);
        board.move(c7, c5);
        board.move(b4, c5);

        Score scoreOfWhite = Score.of(board).get(Camp.WHITE);
        assertThat(scoreOfWhite.getValue()).isEqualTo(37);
    }

    @DisplayName("초기 상태의 체스판에서 결과는 무승부이다.")
    @Test
    void winnerOf_none() {
        assertThat(Score.winnerOf(board)).isEqualTo(Camp.NONE);
    }

    @DisplayName("흑색 진영의 킹이 잡히면 백색 진영의 승리이다.")
    @Test
    void winnerOf_white() {
        Position g2 = Position.of(Column.G, Row.TWO);
        Position g4 = Position.of(Column.G, Row.FOUR);
        Position f7 = Position.of(Column.F, Row.SEVEN);
        Position f5 = Position.of(Column.F, Row.FIVE);
        Position c7 = Position.of(Column.C, Row.SEVEN);
        Position c5 = Position.of(Column.C, Row.FIVE);
        Position f6 = Position.of(Column.F, Row.SIX);
        Position c4 = Position.of(Column.C, Row.FOUR);
        Position c3 = Position.of(Column.C, Row.THREE);
        Position e8 = Position.of(Column.E, Row.EIGHT);
        board.move(g2, g4);
        board.move(f7, f5);
        board.move(g4, f5);
        board.move(c7, c5);
        board.move(f5, f6);
        board.move(c5, c4);
        board.move(f6, f7);
        board.move(c4, c3);
        board.move(f7, e8);

        assertThat(Score.winnerOf(board)).isEqualTo(Camp.WHITE);
    }
}
