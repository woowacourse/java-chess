package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.Ready;
import chess.dto.BoardDto;

public class ChessGame {

    private final Board board;
    private final Turn turn;
    private GameState gameState;


    public ChessGame(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
        this.gameState = new Ready(board);
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
        return board.score(team);
    }

    public Team currentTurn() {
        return turn.now();
    }

    public BoardDto boardDto() {
        return board.boardDto();
    }
}
