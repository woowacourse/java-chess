package chess.controller;

import chess.controller.mapper.request.ChessCommandTypeMapper;
import chess.controller.mapper.request.PositionMapper;
import chess.domain.ChessGameService;
import chess.domain.game.ChessCommandType;
import chess.domain.game.result.GameResult;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.ReadyGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.output.ChessBoardStateFormatter;
import chess.view.output.GameResultFormatter;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();

    private final Map<ChessCommandType, ChessAction> actions = Map.of(
            ChessCommandType.START, new ChessAction(this::start),
            ChessCommandType.END, new ChessAction(this::end),
            ChessCommandType.MOVE, new ChessAction(this::move),
            ChessCommandType.STATUS, new ChessAction(this::status)
    );
    private final ChessGameService gameService;

    public ChessController(ChessGameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        outputView.printStartPrefix();
        ChessGame chessGame = new ReadyGame();

        if (gameService.isGameExist()) {
            chessGame = requestNewGame();
            printChessGameResult(chessGame);
        }

        do {
            chessGame = play(chessGame);
            printChessGameResult(chessGame);
        } while (chessGame.isRunnableGame());

        printResult(chessGame);
    }

    private ChessGame requestNewGame() {
        String command = inputView.readLoadCommand();
        if (command.equals("y")) {
            return gameService.loadExistGame();
        }

        return new ReadyGame().startGame();
    }

    private ChessGame play(ChessGame chessGame) {
        try {
            List<String> commandInputs = inputView.readCommands();
            ChessAction chessAction = matchAction(commandInputs);
            return chessAction.execute(commandInputs, chessGame);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return play(chessGame);
        }
    }

    private ChessAction matchAction(List<String> commandInputs) {
        ChessCommandType commandType = ChessCommandTypeMapper.toChessCommandType(commandInputs.get(0));
        return actions.get(commandType);
    }

    private void printChessGameResult(ChessGame chessGame) {
        Map<Position, Piece> piecesPosition = chessGame.getPiecesPosition();
        List<List<String>> consoleViewBoard =
                ChessBoardStateFormatter.convertToConsoleViewBoard(piecesPosition);

        outputView.printChessState(consoleViewBoard);
    }

    private void printResult(ChessGame chessGame) {
        GameResult gameResult = chessGame.calculateResult();
        List<String> results =
                GameResultFormatter.convertToGameResult(gameResult);

        outputView.printResult(results);
    }

    private ChessGame start(List<String> commands, ChessGame chessGame) {
        return gameService.start(chessGame);
    }

    private ChessGame move(List<String> commands, ChessGame chessGame) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("이동 명령은 명령어를 포함하여 시작점과 도착점이 존재해야 합니다.");
        }

        Position from = PositionMapper.toPosition(commands.get(1));
        Position to = PositionMapper.toPosition(commands.get(2));
        return gameService.move(chessGame, from, to);
    }

    private ChessGame end(List<String> commands, ChessGame chessGame) {
        return gameService.end(chessGame);
    }

    private ChessGame status(List<String> commands, ChessGame chessGame) {
        return gameService.status(chessGame);
    }
}
