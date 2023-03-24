package chess.controller;

import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.ReadyChessGame;
import chess.domain.command.Command;
import chess.domain.command.CommandType;
import chess.domain.command.MoveCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private final Map<CommandType, CommandAction> commandMapper;

    public ChessGameController() {
        this.commandMapper = new EnumMap<>(CommandType.class);
        commandMapper.put(CommandType.START, this::start);
        commandMapper.put(CommandType.MOVE, this::move);
        commandMapper.put(CommandType.END, this::end);
    }

    public void run() {
        ChessGame chessGame = new ReadyChessGame();
        playChessGame(chessGame);
    }

    private void playChessGame(ChessGame chessGame) {
        OutputView.printStartMessage();
        do {
            chessGame = executeCorrectCommand(chessGame);
        } while (chessGame.isPlaying());
    }

    private ChessGame executeCorrectCommand(ChessGame chessGame) {
        ChessGame newChessGame = null;
        try {
            List<String> inputCommand = InputView.inputCommand();
            newChessGame = executeCommand(chessGame, inputCommand);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return executeCorrectCommand(chessGame);
        }
        return newChessGame;
    }

    private ChessGame executeCommand(ChessGame chessGame, List<String> commands) {
        CommandType commandType = CommandType.getCommandType(commands);
        Command command = commandType.getCommand(commands);
        CommandAction commandAction = commandMapper.get(commandType);
        return commandAction.execute(chessGame, command);
    }

    public ChessGame start(ChessGame chessGame, Command command) {
        ChessGame newChessGame = chessGame.start();
        printBoard(newChessGame);
        return newChessGame;
    }

    public ChessGame move(ChessGame chessGame, Command command) {
        MoveCommand moveCommand = (MoveCommand) command;
        String currentPosition = moveCommand.getCurrentPosition();
        String nextPosition = moveCommand.getNextPosition();
        ChessGame newChessGame = chessGame.move(currentPosition, nextPosition);
        printBoard(newChessGame);
        return newChessGame;
    }

    public ChessGame end(ChessGame chessGame, Command command) {
        return chessGame.end();
    }

    public void printBoard(ChessGame chessGame) {
        OutputView.printBoard(chessGame.getPrintingBoard());
    }
}

interface CommandAction {
    ChessGame execute(ChessGame chessGame, Command command);
}