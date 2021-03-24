package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Ready;

public class ChessGame {

    private final Turn turn;
    private final ScoreBoard scoreBoard;
    private GameState gameState;

    public ChessGame(Board board, Turn turn) {
        this.scoreBoard = new ScoreBoard(board);
        this.gameState = new Ready(board);
        this.turn = turn;
    }

    public void start() {
        gameState = gameState.start();
    }

    public void end() {
        gameState = gameState.end();
    }

    public void move(Point source, Point destination) {
        gameState = gameState.move(source, destination, turn);
    }

    public void status() {
        gameState = gameState.status();
    }

    public boolean isContinue() {
        return !gameState.isFinished();
    }

    public double score(Team team) {
        return scoreBoard.score(team);
    }

    public Team currentTurn() {
        return turn.now();
    }

    public Team winner() {
        return gameState.winner();
    }
}
