package chess.domain;

import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameState state;

    public ChessGame(ChessBoard chessBoard) {
        this.state = GameState.WAITING;
        this.chessBoard = chessBoard;
    }

    public void initialize() {
        state = GameState.RUNNING;
    }

    public boolean isNotEnd() {
        return state != GameState.FINISHED;
    }

    public void executeMove(Position source, Position destination) {
        if (state != GameState.RUNNING) {
            throw new IllegalStateException("게임이 실행중이지 않습니다.");
        }
        chessBoard.move(source, destination);
        checkGameNotFinished();
    }

    private void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            end();
        }
    }

    public void end() {
        state = GameState.FINISHED;
    }

    public double calculateScore(Team team) {
        if (state == GameState.WAITING) {
            throw new IllegalStateException("게임이 시작되지 않았습니다");
        }
        return chessBoard.calculateScore(team);
    }

    public boolean isKingDead() {
        return chessBoard.isKingDead();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Team findWinningTeam() {
        if (isNotEnd()) {
            throw new IllegalStateException("게임이 끝나지 않았습니다.");
        }
        if (chessBoard.isKingDead()) {
            return chessBoard.previousTeam();
        }
        return Team.NONE;
    }

    public boolean isFirstTurn() {
        return chessBoard.isFirstTurn();
    }
}
