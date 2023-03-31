package chess.controller;

import chess.controller.mapper.request.ChessCommandTypeMapper;
import chess.controller.mapper.request.PositionMapper;
import chess.domain.PlayChessGameService;
import chess.domain.LoadChessGameService;
import chess.domain.game.ChessCommandType;
import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.ReadyGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.output.board.ChessBoardStateFormatter;
import chess.view.output.result.GameResultFormatter;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SIMPLE_COMMAND_SIZE = 1;
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();

    private final Map<ChessCommandType, ChessAction> actions = Map.of(
            ChessCommandType.START, new ChessAction(this::start),
            ChessCommandType.PAUSE, new ChessAction(this::end),
            ChessCommandType.MOVE, new ChessAction(this::move),
            ChessCommandType.STATUS, new ChessAction(this::status),
            ChessCommandType.FETCH, new ChessAction(this::load)
    );
    private final PlayChessGameService playChessGameService;
    private final LoadChessGameService loadChessGameService;

    public ChessController(PlayChessGameService playChessGameService, LoadChessGameService loadChessGameService) {
        this.playChessGameService = playChessGameService;
        this.loadChessGameService = loadChessGameService;
    }

    public void run() {
        outputView.printStartPrefix(loadChessGameService.isGameExist());
        ChessGame chessGame = new ReadyGame();

        do {
            chessGame = play(chessGame);
            printChessGameResult(chessGame);
        } while (chessGame.isRunnableGame());

        printResult(chessGame);
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
        if (gameResult.getMatchResult() == MatchResult.PAUSE) {
            outputView.printPausedMessage();
            return;
        }

        loadChessGameService.cleanUpGame();
        List<String> results = GameResultFormatter.convertToGameResult(gameResult);
        outputView.printResult(results);
    }

    private ChessGame start(List<String> commands, ChessGame chessGame) {
        validateInputCommandSize(commands, SIMPLE_COMMAND_SIZE);
        return playChessGameService.start(chessGame);
    }

    private ChessGame move(List<String> commands, ChessGame chessGame) {
        validateInputCommandSize(commands, MOVE_COMMAND_SIZE);

        Position from = PositionMapper.toPosition(commands.get(1));
        Position to = PositionMapper.toPosition(commands.get(2));
        return playChessGameService.move(chessGame, from, to);
    }

    private ChessGame end(List<String> commands, ChessGame chessGame) {
        validateInputCommandSize(commands, SIMPLE_COMMAND_SIZE);
        return playChessGameService.end(chessGame);
    }

    private ChessGame status(List<String> commands, ChessGame chessGame) {
        validateInputCommandSize(commands, SIMPLE_COMMAND_SIZE);
        return playChessGameService.status(chessGame);
    }

    private ChessGame load(List<String> commands, ChessGame ignore) {
        validateInputCommandSize(commands, SIMPLE_COMMAND_SIZE);
        return loadChessGameService.loadExistGame();
    }

    private void validateInputCommandSize(List<String> commands, int size) {
        if (commands.size() == size) {
            return;
        }

        throw new IllegalArgumentException("옳바른 명령을 입력하세요. ex) start, move a2 a3, fetch");
    }
}
