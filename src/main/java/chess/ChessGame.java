package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
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
        commands.put(GameCommand.MOVE, args -> move(args.get(1), args.get(2)));
        commands.put(GameCommand.START, args -> start());
        commands.put(GameCommand.END, args -> end());
    }

    private void playChess() {
        OutputView.printGameStartMessage();

        while (gameState.isPlaying()) {
            executeCommand();
        }
    }

    private void executeCommand() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);
        commands.get(gameCommand).execute(inputCommand);
    }

    private void start() {
        gameState = gameState.start();
        OutputView.printChessBoard(board);
    }

    private void move(String inputSource, String inputTarget) {
        Position source = Position.convert(inputSource);
        Position target = Position.convert(inputTarget);
        gameState = gameState.move(board, source, target);
        OutputView.printChessBoard(board);
    }

    private void end() {
        gameState = gameState.end();
    }
}
