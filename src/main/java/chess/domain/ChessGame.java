package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.status.Status;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private Turn turn;
    private Status status;
    private boolean isEnd;

    private ChessGame(Board board, Status status, Turn turn, boolean isEnd) {
        this.board = board;
        this.status = status;
        this.turn = turn;
        this.isEnd = isEnd;
    }

    public static ChessGame start() {
        Board board = Board.of(new DefaultBoardInitializer());
        return new ChessGame(board, Status.of(board), Turn.from(Player.WHITE), false);
    }

    public void move(MoveParameter moveParameter) {
        if (!isEnd) {
            board.move(moveParameter.getSource(), moveParameter.getTarget(), turn);
            status.update(board);
            if (board.isLost(Player.WHITE) || board.isLost(Player.BLACK)) {
                isEnd = true;
            }
            return;
        }
        throw new UnsupportedOperationException("이미 게임이 종료되었습니다.");
    }

    public void end() {
        isEnd = true;
    }

    public void status() {
        status.update(board);
    }

    public Map<Position, String> getBoard() {
        return board.getBoard();
    }

    public Status getStatus() {
        return status;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
