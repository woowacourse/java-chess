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
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Map<GameCommand, CommandExecutor> commands = Map.of(
            GameCommand.MOVE, args -> move(args.get(1), args.get(2)),
            GameCommand.START, args -> start(),
            GameCommand.END, args -> end()
    );

    private GameState gameState = new ReadyState();

    public void run() {
        Board board = BoardFactory.createInitialBoard();
        while (gameState.isPlaying()) {
            executeCommand();
        }
        OutputView.printChessBoard(board);
    }

    private void executeCommand() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);
        commands.get(gameCommand).execute(inputCommand);
    }

    private void move(String inputSource, String inputTarget) {
        Position source = Position.convert(inputSource);
        Position target = Position.convert(inputTarget);
        System.out.println(source + " " + target);
        OutputView.printGameStartMessage();
        gameState = gameState.move();
    }

    private void start() {
        gameState = gameState.start();
        OutputView.printGameStartMessage();
    }

    private void end() {
        gameState = gameState.end();
    }
}
