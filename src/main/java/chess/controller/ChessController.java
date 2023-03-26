package chess.controller;

import chess.controller.dto.CommandDto;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.domain.room.Room;
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
    private final Room room;

    public ChessController(final ErrorController errorController, final Room room) {
        this.errorController = errorController;
        this.commandMapper = Map.of(
                START, this::start,
                MOVE, this::move,
                STATUS, this::inquireStatus
        );
        this.room = room;
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
        ChessGame chessGame = room.connectRoom();
        OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
        OutputView.printTurn(OutputRenderer.toTeamDto(chessGame.getTurn()));
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto move(final CommandDto commandDto) {
        List<java.lang.Integer> source = commandDto.getSource();
        List<java.lang.Integer> target = commandDto.getTarget();
        Position sourcePosition = new Position(source.get(0), source.get(1));
        Position targetPosition = new Position(target.get(0), target.get(1));
        errorController.tryCatchStrategy(() -> {
            room.updateRoom(sourcePosition, targetPosition);
            ChessGame chessGame = room.connectRoom();
            OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.getBoard()));
            OutputView.printTurn(OutputRenderer.toTeamDto(chessGame.getTurn()));
        });

        if (room.connectRoom().isGameEnd()) {
            OutputView.printFinishMessage();
            return readCommand(List.of(STATUS, END));
        }
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto inquireStatus(final CommandDto commandDto) {
        ChessGame chessGame = room.connectRoom();
        OutputView.printStatus(OutputRenderer.toStatusDto(WHITE, chessGame.getTotalScore(WHITE)));
        OutputView.printStatus(OutputRenderer.toStatusDto(BLACK, chessGame.getTotalScore(BLACK)));

        if (chessGame.isGameEnd()) {
            OutputView.printWinTeam(OutputRenderer.toTeamDto(chessGame.getWinTeam()));
            room.deleteRoom();
            return readCommand(List.of(END));
        }
        return readCommand(List.of(MOVE, STATUS, END));
    }
}
