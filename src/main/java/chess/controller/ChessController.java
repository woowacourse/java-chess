package chess.controller;

import chess.controller.dto.CommandDto;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.domain.position.Position;
import chess.service.ChessGameService;
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

public final class ChessController {

    private final ErrorController errorController;
    private final Map<Command, Action> commandMapper;
    private final ChessGameService chessGameService;

    public ChessController(final ErrorController errorController, final ChessGameService chessGameService) {
        this.errorController = errorController;
        this.commandMapper = Map.of(
                START, this::start,
                MOVE, this::move,
                STATUS, this::status
        );
        this.chessGameService = chessGameService;
    }

    public void run() {
        OutputView.printInitialMessage();
        CommandDto commandDto = readCommand(List.of(START, END));
        Command command = commandDto.getCommand();

        while (command.isEnd()) {
            commandDto = commandMapper.get(command).act(commandDto);
            command = commandDto.getCommand();
        }
    }

    private CommandDto readCommand(final List<Command> possibleCommands) {
        CommandDto commandDto;
        do {
            commandDto = errorController.RetryIfThrowsException(() ->
                    InputRenderer.toCommandDto(InputView.readCommand()));
        } while (!possibleCommands.contains(commandDto.getCommand()));
        return commandDto;
    }

    public CommandDto start(final CommandDto commandDto) {
        OutputView.printBoard(OutputRenderer.toBoardDto(chessGameService.getBoard()));
        OutputView.printTurn(OutputRenderer.toTeamDto(chessGameService.getTurn()));
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto move(final CommandDto commandDto) {
        errorController.tryCatchStrategy(() -> {
            List<Integer> source = commandDto.getSource();
            List<Integer> target = commandDto.getTarget();
            Position sourcePosition = new Position(source.get(0), source.get(1));
            Position targetPosition = new Position(target.get(0), target.get(1));
            chessGameService.move(sourcePosition, targetPosition);
            OutputView.printBoard(OutputRenderer.toBoardDto(chessGameService.getBoard()));
            OutputView.printTurn(OutputRenderer.toTeamDto(chessGameService.getTurn()));
        });

        return checkGameOver();
    }

    private CommandDto checkGameOver() {
        if (chessGameService.isGameEnd()) {
            OutputView.printFinishMessage();
            inquireStatus();
            chessGameService.delete();
            return new CommandDto(END);
        }
        return readCommand(List.of(MOVE, STATUS, END));
    }

    private void inquireStatus() {
        OutputView.printStatus(OutputRenderer.toStatusDto(WHITE, chessGameService.getTotalScore(WHITE)));
        OutputView.printStatus(OutputRenderer.toStatusDto(BLACK, chessGameService.getTotalScore(BLACK)));
        OutputView.printWinTeam(OutputRenderer.toTeamDto(chessGameService.getWinTeam()));
    }

    public CommandDto status(final CommandDto commandDto) {
        inquireStatus();
        return readCommand(List.of(MOVE, STATUS, END));
    }
}
