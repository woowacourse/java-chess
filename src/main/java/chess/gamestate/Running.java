package chess.gamestate;

import chess.PieceOperator;
import chess.Turn;
import chess.board.Point;
import chess.command.Command;
import java.util.List;

public class Running implements GameState {

    private final PieceOperator pieceOperator;
    private final Turn turn;

    public Running(PieceOperator pieceOperator, Turn turn) {
        this.pieceOperator = pieceOperator;
        this.turn = turn;
    }

    @Override
    public GameState operateCommand(Command command, List<String> arguments) {
        if (command == Command.MOVE) {
            return updateStateWhenMove(arguments);
        }
        if (command == Command.STATUS) {
            return this;
        }
        if (command == Command.END) {
            return new Finished();
        }
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }

    private GameState updateStateWhenMove(List<String> arguments) {
        Point source = Point.of(arguments.get(0));
        Point destination = Point.of(arguments.get(1));

        pieceOperator.move(source, destination, turn.now());
        turn.next();

        if (!pieceOperator.isOnGoing()) {
            return new Finished();
        }
        return this;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
