package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;
import chess.domain.piece.black.BlackRook;
import chess.domain.piece.white.WhiteKnight;
import chess.domain.piece.white.WhitePawn;
import chess.domain.piece.white.WhiteRook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RookMoveConditionTest {

    @DisplayName("룩이 조건대로 움직이는지 확인한다.")
    @Test
    void isSatisfiedBy() {
        RookMoveCondition condition = new RookMoveCondition();
        Board board = new Board(Collections.singletonList(
                WhiteRook.createWithCoordinate(0, 1)
        ));
        boolean rightActual = condition.isSatisfiedBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 1));

        boolean falseActual = condition.isSatisfiedBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 0));

        assertThat(rightActual).isTrue();
        assertThat(falseActual).isFalse();
    }

    @DisplayName("룩의 이동경로에 장애물 있으면 안된다.")
    @Test
    void isSatisfiedBy_false() {
        RookMoveCondition condition = new RookMoveCondition();
        Board board = new Board(Arrays.asList(
                WhiteRook.createWithCoordinate(0, 1),
                WhitePawn.createWithCoordinate(3, 1),
                WhitePawn.createWithCoordinate(0, 2)
        ));

        boolean actualCol = condition.isSatisfiedBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(0, 4));
        boolean actualRow = condition.isSatisfiedBy(board, WhiteRook.createWithCoordinate(0, 1),
                new Position(7, 1));

        assertThat(actualCol).isFalse();
        assertThat(actualRow).isFalse();
    }

    @DisplayName("룩의 붙어있는 적 먹기 테스트")
    @Test
    void isSatisfiedBy_catchAttachOtherColors() {
        RookMoveCondition condition = new RookMoveCondition();
        ChessPiece queen = BlackRook.createWithCoordinate(4, 4);

        int[] row = {0, 0, 1, -1};
        int[] col = {1, -1, 0, 0};

        for (int i = 0; i < row.length; i++) {
            int nextRow = row[i] + 4;
            int nextColumn = col[i] + 4;

            Board board = new Board(Arrays.asList(
                    queen,
                    WhiteKnight.createWithCoordinate(nextRow, nextColumn)
            ));

            assertThat(condition.isSatisfiedBy(board, queen, new Position(nextRow, nextColumn))).isTrue();
        }
    }

    @DisplayName("룩의 떨어져있는 적 먹기 테스트")
    @Test
    void isSatisfiedBy_catchRemoteOtherColors() {
        RookMoveCondition condition = new RookMoveCondition();
        ChessPiece queen = BlackRook.createWithCoordinate(4, 4);

        int[] row = {0, 0, 1, -1};
        int[] col = {1, -1, 0, 0};

        for (int i = 0; i < row.length; i++) {
            int nextRow = row[i] + 4;
            int nextColumn = col[i] + 4;

            Board board = new Board(Arrays.asList(
                    queen,
                    WhiteKnight.createWithCoordinate(nextRow, nextColumn)
            ));

            assertThat(condition.isSatisfiedBy(board, queen, new Position(nextRow, nextColumn))).isTrue();
        }
    }

}