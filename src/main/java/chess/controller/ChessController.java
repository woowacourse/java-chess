package chess.controller;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.ChessRequest;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGame chessGame;

    private static final Map<GameCommand, Function<List<String>, Controller>> commands =
            new EnumMap<>(GameCommand.class);

    static {
        commands.put(GameCommand.START, ignored -> new StartController());
        commands.put(GameCommand.END, ignored -> new EndController());
        commands.put(GameCommand.MOVE, MoveController::new);
        commands.put(GameCommand.STATUS, ignored -> new StatusController());
    }

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGame = new ChessGame(BoardFactory.createBoard(), Color.WHITE);
    }

    public void run() {
        outputView.printStart();
        while (chessGame.isRunning()) {
            play();
        }
    }

    private void play() {
        try {
            ChessRequest chessRequest = inputView.readGameCommand();
            GameCommand gameCommand = GameCommand.of(chessRequest.getCommand());
            Controller controller = commands.get(gameCommand).apply(chessRequest.getParameter());
            controller.execute(chessGame, outputView);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            play();
        }
    }
}
