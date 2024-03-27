package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("기물을 a1에서 a3으로 이동 시킬 수 있다.")
    @Test
    void move1() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE)
        ));

        board.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.THIRD));

        assertThat(board.get(Point.of(File.A, Rank.THIRD))).isEqualTo(Piece.bishopFrom(Team.WHITE));
    }

    @DisplayName("기물을 a1 에서 a3 으로 이동 시키면 a1 은 비어있다.")
    @Test
    void move2() {
        Board board = BoardFactory.createCustumBoard(Map.of(
                Point.of(File.A, Rank.FIRST), Piece.bishopFrom(Team.WHITE)
        ));

        board.move(
                Point.of(File.A, Rank.FIRST),
                Point.of(File.A, Rank.THIRD));

        assertThat(board.get(Point.of(File.A, Rank.FIRST))).isEqualTo(Piece.empty());
    }
}
