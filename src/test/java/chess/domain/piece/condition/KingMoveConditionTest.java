package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackKing;
import chess.domain.piece.white.WhiteKnight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class KingMoveConditionTest {

    @DisplayName("킹이 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfyBy() {
        KingMoveCondition condition = new KingMoveCondition();
        ChessPiece king = BlackKing.createWithCoordinate(4, 4);
        Board board = new Board(Collections.singletonList(
                king
        ));

        int[] row = {1, -1, 0, 0, -1, -1, 1, 1};
        int[] col = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int i = 0; i < row.length; i++) {
            int dr = row[i] + 4;
            int dc = col[i] + 4;

            assertThat(condition.isSatisfyBy(board, king, new Position(dr, dc))).isTrue();
        }

        assertThat(condition.isSatisfyBy(board, king, new Position(0, 0))).isFalse();
    }

    @DisplayName("킹의 이동 경로에 우리팀 말이 있으면 안된다.")
    @Test
    void isSatisfyBy_sameColorNotAllowOnPath() {
        KingMoveCondition condition = new KingMoveCondition();
        Board board = new Board(Arrays.asList(
                BlackKing.createWithCoordinate(4, 4),
                BlackKing.createWithCoordinate(4, 3)
        ));

        boolean actual = condition.isSatisfyBy(board, BlackKing.createWithCoordinate(4, 4), new Position(4, 3));

        assertThat(actual).isFalse();
    }

    @DisplayName("킹의 이동 경로에 상대팀 말이 있으면 이동.")
    @Test
    void isSatisfyBy_otherColorAloowOnPath() {
        KingMoveCondition condition = new KingMoveCondition();
        ChessPiece king = BlackKing.createWithCoordinate(4, 4);

        int[] row = {1, -1, 0, 0, -1, -1, 1, 1};
        int[] col = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int i = 0; i < row.length; i++) {
            int dr = row[i] + 4;
            int dc = col[i] + 4;

            Board board = new Board(Arrays.asList(
                    king,
                    WhiteKnight.createWithCoordinate(dr, dc)
            ));

            assertThat(condition.isSatisfyBy(board, king, new Position(dr, dc))).isTrue();
        }
    }

}