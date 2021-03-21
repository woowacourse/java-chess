package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackQueen;
import chess.domain.piece.white.WhiteKnight;
import chess.domain.piece.white.WhitePawn;
import chess.domain.piece.white.WhiteQueen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class QueenMoveConditionTest {

    @DisplayName("퀸이 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfyBy() {
        QueenMoveCondition condition = new QueenMoveCondition();
        Board board = new Board(Collections.singletonList(
                WhiteQueen.createWithCoordinate(4, 4)
        ));
        boolean rightActual = condition.isSatisfyBy(board, WhiteQueen.createWithCoordinate(4, 4),
                new Position(7, 4));

        boolean falseActual = condition.isSatisfyBy(board, WhiteQueen.createWithCoordinate(4, 4),
                new Position(7, 5));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("퀸의 이동 경로에 장애물이 있으면 안된다.")
    @Test
    void isSatisfyBy_notAllowObstaclesOnPath() {
        QueenMoveCondition condition = new QueenMoveCondition();
        Board board = new Board(Arrays.asList(
                WhiteQueen.createWithCoordinate(4, 4),
                WhitePawn.createWithCoordinate(5, 4),
                WhitePawn.createWithCoordinate(5, 5)
        ));

        boolean actualCross = condition.isSatisfyBy(board, WhiteQueen.createWithCoordinate(4, 4),
                new Position(7, 4));
        boolean actualX = condition.isSatisfyBy(board, WhiteQueen.createWithCoordinate(4, 4), new Position(6, 6));

        assertThat(actualCross).isFalse();
        assertThat(actualX).isFalse();
    }

    @DisplayName("퀸의 붙어있는 적 먹기 테스트")
    @Test
    void isSatisfyBy_catchAttachOtherColors() {
        QueenMoveCondition condition = new QueenMoveCondition();
        ChessPiece queen = BlackQueen.createWithCoordinate(4, 4);

        int[] row = {1, -1, 0, 0, -1, -1, 1, 1};
        int[] col = {0, 0, 1, -1, 1, -1, 1, -1};

        for (int i = 0; i < row.length; i++) {
            int dr = row[i] + 4;
            int dc = col[i] + 4;

            Board board = new Board(Arrays.asList(
                    queen,
                    WhiteKnight.createWithCoordinate(dr, dc)
            ));

            assertThat(condition.isSatisfyBy(board, queen, new Position(dr, dc))).isTrue();
        }
    }

    @DisplayName("퀸의 떨어져있는 적 먹기 테스트")
    @Test
    void isSatisfyBy_catchRemoteOtherColors() {
        QueenMoveCondition condition = new QueenMoveCondition();
        ChessPiece queen = BlackQueen.createWithCoordinate(4, 4);

        int[] row = {2, -2, 0, 0, -2, -2, 2, 2};
        int[] col = {0, 0, 2, -2, 2, -2, 2, -2};

        for (int i = 0; i < row.length; i++) {
            int dr = row[i] + 4;
            int dc = col[i] + 4;

            Board board = new Board(Arrays.asList(
                    queen,
                    WhiteKnight.createWithCoordinate(dr, dc)
            ));

            assertThat(condition.isSatisfyBy(board, queen, new Position(dr, dc))).isTrue();
        }
    }

}