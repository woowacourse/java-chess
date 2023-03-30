package chess.controller;

import chess.controller.command.*;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GameCommandInvoker {
    private final ChessGameService chessGameService;
    private final OutputView outputView;
    private static final Map<GameCommand, Function<List<String>, GameController>> commandMapper
            = new EnumMap<>(GameCommand.class);

    static {
        commandMapper.put(GameCommand.CREATE, CreateCommand::of);
        commandMapper.put(GameCommand.START, StartCommand::of);
        commandMapper.put(GameCommand.MOVE, MoveCommand::of);
        commandMapper.put(GameCommand.STATUS, StatusCommand::of);
        commandMapper.put(GameCommand.END, EndCommand::of);
    }

    public GameCommandInvoker(final ChessGameService chessGameService, OutputView outputView) {
        this.chessGameService = chessGameService;
        this.outputView = outputView;
    }

    public void generate(GameCommand gameCommand, List<String> commandLine) {
        GameController commandInterface = commandMapper.get(gameCommand).apply(commandLine);
        commandInterface.execute(chessGameService, outputView);
    }

}
