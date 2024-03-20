package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.command.CommandExecutor;
import chess.domain.command.GameCommand;
import chess.domain.state.GameState;
import chess.domain.state.ReadyState;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Map<GameCommand, CommandExecutor> COMMANDS = Map.of(
            GameCommand.MOVE, args -> move(args.get(1), args.get(2)),
            GameCommand.START, args -> start(),
            GameCommand.END, args -> end()
    );

    private GameState gameState = new ReadyState();

    public void run() {
        List<String> inputCommand = InputView.readGameCommand();
        GameCommand gameCommand = GameCommand.from(inputCommand);

        Board board = BoardFactory.createInitialBoard();
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(board);

    }

    private void move(String source, String target) {
        // 현재 진영 턴 진행
        gameState = gameState.move();
    }

    private void start() {
        gameState = gameState.start();
    }

    private void end() {
        gameState = gameState.end();
    }
}
