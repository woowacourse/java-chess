package chess.controller;

import chess.controller.dto.CommandDto;
import chess.controller.dto.InputRenderer;
import chess.controller.dto.OutputRenderer;
import chess.dao.ChessGameDao;
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

public final class ChessController {

    private final ErrorController errorController;
    private final ChessGameDao chessGameDao;
    private final Map<Command, Action> commandMapper;

    public ChessController(final ErrorController errorController, ChessGameDao chessGameDao) {
        this.errorController = errorController;
        this.chessGameDao = chessGameDao;
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
        ChessGame chessGame = chessGameDao.select();
        if(chessGame == null){
            chessGame = ChessGame.create();
            chessGameDao.save(chessGame);
        }
        OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.board()));
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto move(final CommandDto commandDto) {
        ChessGame chessGame = chessGameDao.select();
        List<Integer> source = commandDto.getSource();
        List<Integer> target = commandDto.getTarget();
        Position sourcePosition = new Position(source.get(0), source.get(1));
        Position targetPosition = new Position(target.get(0), target.get(1));
        errorController.tryCatchStrategy(() -> {
            chessGame.movePiece(sourcePosition, targetPosition);
            OutputView.printBoard(OutputRenderer.toBoardDto(chessGame.board()));
            OutputView.printTurn(OutputRenderer.toTeamDto(chessGame.getTurn()));
            chessGameDao.update(chessGame);
        });

        if (chessGame.isGameEnd()) {
            OutputView.printFinishMessage();
            chessGameDao.delete();
            return readCommand(List.of(STATUS, END));
        }
        return readCommand(List.of(MOVE, STATUS, END));
    }

    public CommandDto inquireStatus(final CommandDto commandDto) {
        ChessGame chessGame = chessGameDao.select();
        OutputView.printStatus(OutputRenderer.toStatusDto(WHITE, chessGame.getTotalScore(WHITE)));
        OutputView.printStatus(OutputRenderer.toStatusDto(BLACK, chessGame.getTotalScore(BLACK)));
        OutputView.printWinTeam(OutputRenderer.toTeamDto(chessGame.getWinTeam()));
        return readCommand(List.of(MOVE, STATUS, END));
    }
}
