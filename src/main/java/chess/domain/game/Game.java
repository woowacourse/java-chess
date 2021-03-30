package chess.domain.game;

import chess.domain.command.Command;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.End;
import chess.domain.state.Init;
import chess.domain.state.State;

import java.util.List;
import java.util.Map;

public class Game {
    private final Board board;
    private final Calculator calculator = new Calculator();
    private final Turn turn = new Turn();
    private State state;

    public Game(Board board) {
        this.board = board;
        state = new Init();
    }

    public void move(Position from, Position to) {
        board.action(turn.color(), from, to);
        turn.next();
    }

    public boolean isNotEnd() {
        return (!(state instanceof End));
    }

    public Color currentPlayer() {
        return turn.color();
    }

    public Map<Color, Double> score() {
        return calculator.scoreByColor(board.allPieces());
    }

    public Map<Position, Piece> allBoard() {
        return board.allPieces();
    }

    public void action(Command command) {
        if (command.isMove()) {
            List<String> positions = command.getOptions();
            move(Position.from(positions.get(0)), Position.from(positions.get(1)));
        }
        state = state.action(command);
    }
}
