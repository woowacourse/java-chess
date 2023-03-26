package chess.controller;

import chess.service.ChessGameService;
import chess.view.ChessRequest;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService chessGameService;

    private static final Map<GameCommand, Action> commands = new EnumMap<>(GameCommand.class);

    static {
        commands.put(GameCommand.START, new StartAction());
        commands.put(GameCommand.END, new EndAction());
        commands.put(GameCommand.MOVE, new MoveAction());
        commands.put(GameCommand.STATUS, new StatusAction());
        commands.put(GameCommand.CLEAR, new ClearAction());
    }

    public ChessController(OutputView outputView, InputView inputView, ChessGameService chessGameService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGameService = chessGameService;
    }

    public void run() {
        outputView.printStart();
        GameCommand gameCommand = GameCommand.EMPTY;
        while (gameCommand != GameCommand.END) {
            gameCommand = play();
        }
    }

    private GameCommand play() {
        try {
            ChessRequest chessRequest = inputView.readGameCommand();
            GameCommand gameCommand = chessRequest.getCommand();
            Action action = commands.get(gameCommand);
            return action.execute(chessGameService, outputView, chessRequest);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return play();
        }
    }
}
