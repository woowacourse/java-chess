package chess.controller;

import chess.controller.dto.CommandDto;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class ChessController {

    private final ErrorController errorController;
    private final ChessGame chessGame;
    private final Map<Command, Action> commandMapper;

    public ChessController(ErrorController errorController) {
        this.errorController = errorController;
        this.chessGame = ChessGame.create();
        this.commandMapper = Map.of(
                START, this::start,
                MOVE, this::move,
                STATUS, this::inquireStatus
        );
    }

    public void run() {
        OutputView.printInitialMessage();
        CommandDto commandDto = readCommand(List.of(START, END));
        Command command = commandDto.getCommand();

        while (command.isPlayable()) {
            commandDto = commandMapper.get(command).act(commandDto);
            command = commandDto.getCommand();
        }
    }

    private CommandDto readCommand(List<Command> possibleCommands) {
        CommandDto commandDto;
        do {
            commandDto = errorController.RetryIfThrowsException(() ->
                    InputRenderer.toCommandDto(InputView.readCommand()));
        } while (!possibleCommands.contains(commandDto.getCommand()));
        return commandDto;
    }

    public CommandDto start(CommandDto commandDto) {
        OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto move(CommandDto commandDto) {
        List<Integer> source = commandDto.getSource();
        List<Integer> target = commandDto.getTarget();
        Position sourcePosition = new Position(source.get(0), source.get(1));
        Position targetPosition = new Position(target.get(0), target.get(1));
        errorController.tryCatchStrategy(() -> {
            chessGame.movePiece(sourcePosition, targetPosition);
            OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
        });

        if (chessGame.isGameEnd()) {
            OutputView.printFinishMessage();
            return readCommand(List.of(STATUS, END));
        }
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto inquireStatus(CommandDto commandDto) {
        OutputView.printStatus(OutputRenderer.toStatusDto(WHITE, chessGame.getTotalScore(WHITE)));
        OutputView.printStatus(OutputRenderer.toStatusDto(BLACK, chessGame.getTotalScore(BLACK)));
        OutputView.printWinTeam(OutputRenderer.toResultDto(chessGame.getWinTeam()));
        return readCommand(List.of(MOVE, STATUS, END));
    }
}
