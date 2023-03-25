package chess.domain.command;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import chess.controller.dto.GameResultBySideDto;
import chess.controller.dto.ScoreBySideDto;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class CommandManager {

    private final Map<GameCommand, Consumer<Commands>> executionByGameCommand = Map.of(
            GameCommand.START, none -> start(),
            GameCommand.MOVE, this::move,
            GameCommand.END, none -> end(),
            GameCommand.STATUS, none -> printGameResult()
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

    public boolean canPrintGameResult() {
        return commandStatus.canPrintGameResult();
    }

    public List<Piece> getPieces() {
        return commandStatus.getPieces();
    }

    public String getTurnDisplayName() {
        return commandStatus.getTurnDisplayName();
    }

    private void printGameResult() {
        this.commandStatus = commandStatus.printGameResult();
    }

    public ScoreBySideDto getScoreBySide() {
        return commandStatus.getScoreBySide();
    }

    public GameResultBySideDto getGameResultBySide() {
        return commandStatus.getGameResultBySide();
    }
}
