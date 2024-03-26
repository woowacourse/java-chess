package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.player.Player;
import chess.domain.player.Players;
import chess.domain.point.Point;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private final Players players;
    private State state;

    public ChessGame(Map<Point, Piece> board) {
        this.board = new Board(board);
        this.players = new Players(new Player(Team.WHITE), new Player(Team.BLACK));
        this.state = State.READY;
    }

    public boolean isPlayable() {
        return state.isNotEnd();
    }

    public void finish() {
        state = State.END;
    }

    public void start() {
        state = State.RUNNING;
    }

    public void move(Point departure, Point destination) {
        validateGameState();

        Player player = players.currentTurnOfPlayer();
        board.move(player, departure, destination);
        players.turnOver();
    }

    private void validateGameState() {
        if (state.isNotRunning()) {
            throw new IllegalArgumentException("게임을 시작하지 않아 진행할 수 없습니다.");
        }
    }

    public Map<Point, Piece> getBoard() {
        return board.getBoard();
    }

    private enum State {
        READY,
        RUNNING,
        END;

        public boolean isNotEnd() {
            return this != END;
        }

        public boolean isNotRunning() {
            return this != RUNNING;
        }
    }
}
