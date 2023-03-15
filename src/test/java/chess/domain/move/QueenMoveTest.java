package chess.domain.move;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenMoveTest {

    @Test
    @DisplayName("source에서 target으로 이동하는 방향에 기물이 존재하지 않으면서, 퀸이 이동할 수 있으면 true를 반환한다.")
    void canMoveSuccess() {
        // given
        final ChessBoard chessBoard = new ChessBoard();
        final QueenMove queenMove = new QueenMove();
        final Position source = new Position(3, 3);

        // when
        boolean actual = queenMove.canMove(source, new Position(5, 3), chessBoard);

        // then
        assertThat(actual)
                .isTrue();
    }

    @Test
    @DisplayName("source에서 target으로 이동하는 방향에 기물이 존재하면 false를 반환한다.")
    void canMoveFailWhenPieceExists() {
        // given
        final ChessBoard chessBoard = new ChessBoard();
        final QueenMove queenMove = new QueenMove();
        final Position source = new Position(0, 3);

        // when
        boolean actual = queenMove.canMove(source, new Position(0, 4), chessBoard);

        // then
        assertThat(actual)
                .isFalse();
    }

    @Test
    @DisplayName("source에서 target으로 이동하는 방향이 퀸이 갈 수 없는 위치면 false를 반환한다.")
    void canMoveFailWhenWrongTarget() {
        // given
        final ChessBoard chessBoard = new ChessBoard();
        final QueenMove queenMove = new QueenMove();
        final Position source = new Position(3, 3);

        // when
        boolean actual = queenMove.canMove(source, new Position(4, 5), chessBoard);

        // then
        assertThat(actual)
                .isFalse();
    }
}
