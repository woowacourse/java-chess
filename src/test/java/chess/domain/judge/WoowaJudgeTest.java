package chess.domain.judge;

import chess.dao.BoardDAO;
import chess.dao.FakeBoardDAO;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class WoowaJudgeTest {

    @DisplayName("점수 계산 테스트")
    @Test
    void calculateScore() throws SQLException {
        Board board = new Board(new FakeBoardDAO());
        board.initialize();
        WoowaJudge judge = new WoowaJudge(board);

        assertThat(judge.calculateScore(Side.BLACK)).isEqualTo(38);
    }

    @DisplayName("게임 종료 확인 테스트")
    @Test
    void isGameOver() throws SQLException {
        BoardDAO boardDAO = new FakeBoardDAO(new HashMap<Position, Piece>() {{
            put(Position.of(Row.EIGHT, Column.A), Piece.of(Type.KING, Side.WHITE));
        }});
        Board board = new Board(boardDAO);
        WoowaJudge judge = new WoowaJudge(board);

        assertThat(judge.isGameOver()).isTrue();
    }

    @DisplayName("점수 비교 테스트")
    @Test
    void higherScorer() throws SQLException {
        BoardDAO boardDAO = new FakeBoardDAO(new HashMap<Position, Piece>() {{
            put(Position.of(Row.EIGHT, Column.A), Piece.of(Type.KING, Side.WHITE));
            put(Position.of(Row.EIGHT, Column.H), Piece.of(Type.KING, Side.BLACK));
            put(Position.of(Row.ONE, Column.A), Piece.of(Type.ROOK, Side.WHITE));
            put(Position.of(Row.ONE, Column.H), Piece.of(Type.ROOK, Side.BLACK));
        }});
        Board board = new Board(boardDAO);
        WoowaJudge judge = new WoowaJudge(board);
        assertThat(judge.winner()).isEmpty();

        board.move(Position.of("a1"), Position.of("h1"));
        assertThat(judge.winner()).isEqualTo(Optional.of(Side.WHITE));
    }
}
