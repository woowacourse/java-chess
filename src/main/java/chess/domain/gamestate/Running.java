package chess.domain.gamestate;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.board.Point;
import chess.domain.command.Command;
import java.util.List;

public class Running implements GameState {

    public static final int SOURCE_INDEX = 0;
    public static final int DESTINATION_INDEX = 1;
    private final ChessGame chessGame;
    private final Turn turn;

    public Running(ChessGame chessGame, Turn turn) {
        this.chessGame = chessGame;
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
        Point source = Point.of(arguments.get(SOURCE_INDEX));
        Point destination = Point.of(arguments.get(DESTINATION_INDEX));

        chessGame.move(source, destination, turn.now());
        turn.next();

        if (!chessGame.isOnGoing()) {
            return new Finished();
        }
        return this;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
