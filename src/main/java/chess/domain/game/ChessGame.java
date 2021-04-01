package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.exception.InvalidCommandException;

public final class ChessGame {

    private State state;

    private String id;
    private String name;

    public ChessGame(State state) {
        this.state = state;
    }

    public static ChessGame initChessGame() {
        ChessGame chessGame = new ChessGame(new Ready(Board.createGamingBoard()));
        chessGame.start();
        return chessGame;
    }

    public void start() {
        state = state.start();
    }

    public void execute(Command command) {
        if (command.isMove()) {
            state = state.move(command.source(), command.target());
            return;
        }
        if (command.isEnd()) {
            state = state.finished();
            return;
        }

        throw new InvalidCommandException();
    }

    public Board board() {
        return state.board();
    }

    public Side currentTurn() {
        return state.currentTurn();
    }

    public Side winner() {
        return state.winner();
    }

    public Score score() {
        return state.score();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isGameSet() {
        return state.isGameSet();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
