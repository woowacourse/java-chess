package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Night;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    final ChessBoard chessBoard = ChessBoard.init();

    @Test
    void create() {
        final Set<Piece> chessBoardDetail = chessBoard.getPieces();

        assertThat(chessBoardDetail).containsExactlyInAnyOrder(
                new Rook(Color.WHITE, new Position('a', 8)),
                new Night(Color.WHITE, new Position('b', 8)),
                new Bishop(Color.WHITE, new Position('c', 8)),
                new Queen(Color.WHITE, new Position('d', 8)),
                new King(Color.WHITE, new Position('e', 8)),
                new Bishop(Color.WHITE, new Position('f', 8)),
                new Night(Color.WHITE, new Position('g', 8)),
                new Rook(Color.WHITE, new Position('h', 8)),

                new Pawn(Color.WHITE, new Position('a', 7)),
                new Pawn(Color.WHITE, new Position('b', 7)),
                new Pawn(Color.WHITE, new Position('c', 7)),
                new Pawn(Color.WHITE, new Position('d', 7)),
                new Pawn(Color.WHITE, new Position('e', 7)),
                new Pawn(Color.WHITE, new Position('f', 7)),
                new Pawn(Color.WHITE, new Position('g', 7)),
                new Pawn(Color.WHITE, new Position('h', 7)),

                new Pawn(Color.BLACK, new Position('a', 2)),
                new Pawn(Color.BLACK, new Position('b', 2)),
                new Pawn(Color.BLACK, new Position('c', 2)),
                new Pawn(Color.BLACK, new Position('d', 2)),
                new Pawn(Color.BLACK, new Position('e', 2)),
                new Pawn(Color.BLACK, new Position('f', 2)),
                new Pawn(Color.BLACK, new Position('g', 2)),
                new Pawn(Color.BLACK, new Position('h', 2)),

                new Rook(Color.BLACK, new Position('a', 1)),
                new Night(Color.BLACK, new Position('b', 1)),
                new Bishop(Color.BLACK, new Position('c', 1)),
                new Queen(Color.BLACK, new Position('d', 1)),
                new King(Color.BLACK, new Position('e', 1)),
                new Bishop(Color.BLACK, new Position('f', 1)),
                new Night(Color.BLACK, new Position('g', 1)),
                new Rook(Color.BLACK, new Position('h', 1)));
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._룩의 경우")
    @Test
    void existInWayRook() {
        // given
        final Position currentPosition = new Position('a', 1);
        final Position nextPosition = new Position('a', 4);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 이동할 수 없습니다.");
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._나이트의 경우")
    @Test
    void existInWayNight() {
        // given
        final Position currentPosition = new Position('b', 1);
        final Position nextPosition = new Position('b', 2);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 이동할 수 없습니다.");
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다._비숍의 경우")
    @Test
    void existInWayBishop() {
        // given
        final Position currentPosition = new Position('c', 1);
        final Position nextPosition = new Position('c', 2);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 이동할 수 없습니다.");
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.킹의 경우")
    @Test
    void existInWayKing() {
        // given
        final Position currentPosition = new Position('e', 1);
        final Position nextPosition = new Position('e', 2);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 이동할 수 없습니다.");
    }

    @DisplayName("경로에 기물이 존재하면 예외를 발생시킨다.퀸의 경우")
    @Test
    void existInWayQueen() {
        // given
        final Position currentPosition = new Position('d', 1);
        final Position nextPosition = new Position('d', 3);

        // when && then
        assertThatThrownBy(() -> chessBoard.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 위치로 이동할 수 없습니다.");
    }
}
