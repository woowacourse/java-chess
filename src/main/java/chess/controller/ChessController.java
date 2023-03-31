package chess.controller;

import static chess.controller.GameCommand.CLEAR;
import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;

import chess.dto.ChessRequest;
import chess.service.ChessGameService;
import chess.service.Move;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService chessGameService;
    private final Map<GameCommand, GameAction> commandMapper = new EnumMap<>(GameCommand.class);

    public ChessController(OutputView outputView, InputView inputView, ChessGameService chessGameService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGameService = chessGameService;
        init();
    }

    private void init() {
        commandMapper.put(START, this::start);
        commandMapper.put(MOVE, this::move);
        commandMapper.put(STATUS, this::status);
        commandMapper.put(CLEAR, this::clear);
        commandMapper.put(END, (chessGame, commands) -> END);
    }

    public void run() {
        outputView.printStart();
        GameCommand gameCommand = GameCommand.EMPTY;
        while (gameCommand != END) {
            gameCommand = play();
        }
    }

    private GameCommand play() {
        try {
            ChessRequest chessRequest = inputView.readGameCommand();
            GameCommand gameCommand = chessRequest.getCommand();
            GameAction gameAction = commandMapper.get(gameCommand);
            return gameAction.execute(chessGameService, chessRequest);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return play();
        }
    }

    private GameCommand start(ChessGameService chessGameService, ChessRequest chessRequest) {
        chessGameService.start();
        outputView.printBoard(chessGameService.loadBoard());
        return GameCommand.START;
    }

    private GameCommand move(ChessGameService chessGameService, ChessRequest chessRequest) {
        validateStart(chessGameService);
        Move move = Move.of(chessRequest.getSource(), chessRequest.getTarget());
        chessGameService.move(move);
        outputView.printBoard(chessGameService.getGameResult());
        return checkGameEnd(chessGameService, outputView);
    }

    private GameCommand checkGameEnd(ChessGameService chessGameService, OutputView outputView) {
        if (chessGameService.isGameEnd()) {
            outputView.printStatus(chessGameService.getGameResult());
            outputView.printWinner(chessGameService.getGameResult());
            outputView.printEnd();
            return GameCommand.END;
        }
        return GameCommand.MOVE;
    }

    private GameCommand status(ChessGameService chessGameService, ChessRequest chessRequest) {
        validateStart(chessGameService);
        outputView.printStatus(chessGameService.getGameResult());
        return GameCommand.STATUS;
    }

    private void validateStart(ChessGameService chessGameService) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
    }

    private GameCommand clear(ChessGameService chessGameService, ChessRequest chessRequest) {
        validateStart(chessGameService);
        chessGameService.finishedGame();
        outputView.printStatus(chessGameService.getGameResult());
        outputView.printClear();
        outputView.printStart();
        return GameCommand.CLEAR;
    }
}
