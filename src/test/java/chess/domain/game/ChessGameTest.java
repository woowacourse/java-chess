package chess.domain.game;

import chess.domain.board.BoardFactory;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("현재 턴의 플레이어가 기물을 움직인다.")
    void move() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        ChessGame chessGame = new ChessGame(board);
        chessGame.start();

        Point departure = new Point('b', 2);
        Point destination = new Point('b', 3);
        chessGame.move(departure, destination);

        assertThat(board.get(departure)).isEqualTo(Empty.INSTANCE);
        assertThat(board.get(destination)).isInstanceOf(Pawn.class);
    }
}
