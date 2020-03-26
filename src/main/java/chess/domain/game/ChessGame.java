package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.state.State;

public class ChessGame {
    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void command(String input) {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("start")) {
            start();
        }
        if (tokens[0].equals("end")) {
            end();
        }
        if (tokens[0].equals("move")) {
            move(Position.from(tokens[1]), Position.from(tokens[2]));
        }
    }

    public void start() {
        state = state.start();
    }

    public void move(Position source, Position target) {
        state = state.move(source, target);
    }

    public void end() {
        state = state.end();
    }

    public Board board() {
        return state.board();
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
