package chess.controller;

import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final int GAME_ACTION_COMMAND = 0;
    private static final int START_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final Map<GameCommand, GameAction> commandMapper = new EnumMap<>(GameCommand.class);
    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGame chessGame;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGame = new ChessGame(BoardFactory.createBoard(), Color.WHITE);
        initController();
    }

    private void initController() {
        commandMapper.put(GameCommand.START, this::start);
        commandMapper.put(GameCommand.MOVE, this::move);
        commandMapper.put(GameCommand.END, ignored -> {
        });
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
            List<String> commands = inputView.readGameCommand();
            GameCommand command = GameCommand.of(commands.get(GAME_ACTION_COMMAND));
            GameAction gameAction = commandMapper.get(command);
            gameAction.execute(commands);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return GameCommand.EMPTY;
        }
    }

    private void start(List<String> commands) {
        GameCommand.validateCommandSize(commands.size(), START_COMMAND_SIZE);
        outputView.printBoard(chessGame.getBoard());
    }

    private void move(List<String> commands) {
        GameCommand.validateCommandSize(commands.size(), MOVE_COMMAND_SIZE);
        String source = commands.get(SOURCE_INDEX);
        String target = commands.get(TARGET_INDEX);
        chessGame.movePieceTo(GameCommand.createPosition(source), GameCommand.createPosition(target));
        outputView.printBoard(chessGame.getBoard());
    }
}
