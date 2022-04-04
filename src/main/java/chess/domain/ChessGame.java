package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Location;

import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;
import java.util.Map;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void start() {
        if (isRunning()) {
            throw new IllegalStateException("[ERROR] 게임이 이미 실행 중 입니다.");
        }
        this.state = state.start();
        Board board = state.getBoard();
        OutputView.printChessBoard(board);
    }

    public void move(Location source, Location target) {

        Team team = state.getTeam();
        Piece targetPiece = state.move(team, source, target);
        this.state = state.getNextState(targetPiece);
        OutputView.printChessBoard(state.getBoard());
    }

    public TeamScore status() {
        if (!isRunning()) {
            throw new IllegalStateException("[ERROR] 게임이 실행 중일 때만 점수를 출력할 수 있습니다.");
        }
        return state.getScore();
    }

    public void end() {
        if (!isRunning()) {
            throw new IllegalStateException("[ERROR] 게임이 이미 종료되었습니다.");
        }
        this.state = state.end();
    }

    public Map<String, Piece> toMap() {
        Board board = state.getBoard();
        return board.toMap();
    }
}
