package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Ready;
import java.util.List;

public class ChessGame {

    private Turn turn;
    private final ScoreBoard scoreBoard;
    private GameState gameState;

    public ChessGame(Board board, Turn turn) {
        this.scoreBoard = new ScoreBoard(board);
        this.gameState = new Ready(board);
        this.turn = turn;
    }

    public void start() {
        turn.restart();
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

    public boolean isOngoing() {
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

    public List<Point> movablePoints(Point currentPoint) {
        return gameState.movablePoints(currentPoint, turn);
    }

    public GameState gameState() {
        return gameState;
    }
}
