package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.command.CommandCondition;
import chess.domain.command.CommandExecutor;
import chess.domain.command.GameCommand;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.ReadyState;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Map<GameCommand, CommandExecutor> commands = new EnumMap<>(GameCommand.class);
    private final Board board = BoardFactory.createInitialBoard();
    private GameState gameState = new ReadyState();

    public void run() {
        try {
            registerCommands();
            playChess();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void registerCommands() {
        commands.put(GameCommand.MOVE, this::move);
        commands.put(GameCommand.START, args -> start());
        commands.put(GameCommand.END, args -> end());
    }

    private void playChess() {
        OutputView.printGameStartMessage();

        while (gameState.isPlaying()) {
            repeatUntilValidCommand(this::executeCommand);
        }
    }

    private void executeCommand() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);
        CommandExecutor commandExecutor = commands.get(gameCommand);
        commandExecutor.execute(new CommandCondition(inputCommand));
    }

    private void start() {
        gameState = gameState.start();
        OutputView.printChessBoard(board);
    }

    private void move(CommandCondition commandCondition) {
        Position source = Position.convert(commandCondition.getSource());
        Position target = Position.convert(commandCondition.getTarget());
        gameState = gameState.move(board, source, target);
        OutputView.printChessBoard(board);
    }

    private void end() {
        gameState = gameState.end();
    }

    private static void repeatUntilValidCommand(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatUntilValidCommand(runnable);
        }
    }
}
