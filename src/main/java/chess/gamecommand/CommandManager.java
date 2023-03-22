package chess.gamecommand;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import chess.board.Position;
import chess.piece.Piece;

public class CommandManager {

    private final Map<GameCommand, Consumer<Commands>> executionByGameCommand = Map.of(
            GameCommand.START, none -> start(),
            GameCommand.MOVE, this::move,
            GameCommand.END, none -> end()
    );

    private CommandStatus commandStatus;

    public CommandManager() {
        this.commandStatus = new Init();
    }

    public void execute(Commands commands) {
        executionByGameCommand.get(commands.getCommand()).accept(commands);
    }

    private void start() {
        this.commandStatus = commandStatus.start();
    }

    private void move(Commands commands) {
        Position sourcePosition = commands.generateSourcePosition();
        Position targetPosition = commands.generateTargetPosition();
        this.commandStatus = commandStatus.move(sourcePosition, targetPosition);
    }

    private void end() {
        this.commandStatus = commandStatus.end();
    }

    public boolean isEnd() {
        return commandStatus.isEnd();
    }

    public List<Piece> getPieces() {
        return commandStatus.getPieces();
    }

    public String getTurnDisplayName() {
        return commandStatus.getTurnDisplayName();
    }
}
