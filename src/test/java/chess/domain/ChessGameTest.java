package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("현재 턴의 플레이어가 기물을 움직인다.")
    void move() {
        Map<Point, Piece> board = BoardFactory.createInitialChessBoard();
        ChessGame chessGame = new ChessGame(board);

        Point departure = new Point('b', 2);
        Point destination = new Point('b', 3);
        chessGame.move(departure, destination);

        assertThat(board.get(departure)).isEqualTo(new Empty(Team.EMPTY));
        assertThat(board.get(destination)).isInstanceOf(Pawn.class);
    }
}