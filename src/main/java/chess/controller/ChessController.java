package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandType;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.domain.position.Position;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.command.CommandType.END;
import static chess.controller.command.CommandType.MOVE;
import static chess.controller.command.CommandType.START;
import static chess.controller.command.CommandType.STATUS;
import static chess.controller.command.CommandType.WAIT;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public final class ChessController {

    private final ErrorController errorController;
    private final Map<CommandType, Action> commandMapper;
    private final ChessGameService chessGameService;

    public ChessController(final ErrorController errorController, final ChessGameService chessGameService) {
        this.errorController = errorController;
        this.commandMapper = Map.of(
                START, (ignore) -> start(),
                MOVE, (command) -> move(command),
                STATUS, (ignore) -> status(),
                END, (ignore) -> end()
        );
        this.chessGameService = chessGameService;
    }

    public void run() {
        OutputView.printInitialMessage();
        CommandType commandType = WAIT;

        while (commandType != END) {
            List<CommandType> possibleCommands = commandType.getNextPossibleCommandTypes(chessGameService.isGameEnd());
            Command command = readCommand(possibleCommands);
            commandType = command.getCommandType();
            commandMapper.get(commandType).act(command);
        }
    }

    private Command readCommand(final List<CommandType> possibleCommands) {
        return errorController.retryIfThrowsException(() -> {
            Command command = InputRenderer.toCommand(InputView.readCommand());
            if (!possibleCommands.contains(command.getCommandType())) {
                throw new IllegalArgumentException("게임 흐름에 맞는 명령어를 사용해주세요.");
            }
            return command;
        });
    }

    public void start() {
        OutputView.printBoard(OutputRenderer.toBoardDto(chessGameService.getBoard()));
        OutputView.printTurn(OutputRenderer.toTeamDto(chessGameService.getTurn()));
    }

    public void move(final Command command) {
        errorController.tryCatchStrategy(() -> {
            List<Integer> arguments = command.getArguments();
            Position sourcePosition = new Position(arguments.get(0), arguments.get(1));
            Position targetPosition = new Position(arguments.get(2), arguments.get(3));
            chessGameService.move(sourcePosition, targetPosition);
            OutputView.printBoard(OutputRenderer.toBoardDto(chessGameService.getBoard()));
            OutputView.printTurn(OutputRenderer.toTeamDto(chessGameService.getTurn()));
            checkGameOver();
        });
    }

    public void status() {
        OutputView.printStatus(OutputRenderer.toStatusDto(WHITE, chessGameService.getTotalScore(WHITE)));
        OutputView.printStatus(OutputRenderer.toStatusDto(BLACK, chessGameService.getTotalScore(BLACK)));
        OutputView.printWinTeam(OutputRenderer.toTeamDto(chessGameService.getWinTeam()));
    }

    private void end() {
        OutputView.printFinishMessage();
        if (chessGameService.isGameEnd()) {
            chessGameService.delete();
        }
    }

    private void checkGameOver() {
        if (chessGameService.isGameEnd()) {
            OutputView.printKingDeadMessage();
            status();
        }
    }
}
