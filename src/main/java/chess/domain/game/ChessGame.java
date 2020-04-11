package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.piece.PieceState;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.domain.status.Status;

import java.util.Map;

public class ChessGame {
    private final Board board;
    private final Turn turn;
    private final Status status;
    private boolean isEnd;

    private ChessGame(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
        this.status = Status.of(board);
        this.isEnd = board.isEnd();
    }

    public static ChessGame start() {
        Board board = Board.of(new DefaultBoardInitializer());
        return new ChessGame(board, Turn.from(Player.WHITE));
    }

    public static ChessGame load(Board board, Turn turn) {
        return new ChessGame(board, turn);
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

    public Map<Position, String> getBoardAndString() {
        return board.getBoardAndString();
    }

    public Turn getTurn() {
        return turn;
    }

    public Status getStatus() {
        return status;
    }

    public Player getWinner() {
        if (board.isLost(Player.BLACK)) {
            return Player.WHITE;
        }
        return Player.BLACK;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Map<Position, PieceState> getBoard() {
        return board.getBoard();
    }
}
