package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.gamestate.Finished;
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

    public void tryToMove(Point source, Point destination) {
        gameState = gameState.move(source, destination, turn);
    }

    public void status() {
        gameState = gameState.status();
    }

    public boolean isOnGoing() {
        return !(gameState instanceof Finished);
    }

    public double score(Team team) {
        return board.score(team);
    }

    public BoardDto generateBoardDto() {
        return board.boardDto();
    }
}
