package chess.controller;

import chess.boardstrategy.BoardStrategy;
import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.service.ChessGameService;
import chess.view.CommandRequest;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;
import java.util.function.BiConsumer;

import static chess.controller.Command.*;

public class Controller {
    private static final int NONE_GAME_ID = -1;
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService;
    private int gameId = NONE_GAME_ID;

    public Controller(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGameService = new ChessGameService(new ChessGameDao(), new MoveDao());
    }

    private final Map<Command, BiConsumer<CommandRequest, BoardStrategy>> actions =
            Map.of(START, this::start,
                    MOVE, this::move,
                    STATUS, this::status,
                    END, this::end);


    //todo :(리팩터링)게임이 start없이 move부터 하는 경우 예외발생 = 게임 크리에이트가 아예 안된경우(gameId가 없음 = 게임이 존재하지 않는다)
    //                                      = 게임id는 있는데, 게임이 시작 안한경우
    public void run(BoardStrategy boardStrategy) {
        outputView.printStartGuideMessage();
        Command command = startGame(boardStrategy);
        while (command != Command.END
                && !chessGameService.isFinished(gameId, boardStrategy)) {
            command = playChessByCommandRequest(boardStrategy);
        }
        finishGameByCommand(command, boardStrategy);
    }

    private Command startGame(BoardStrategy boardStrategy) {
        CommandRequest commandRequest;
        Command command;
        do {
            commandRequest = inputView.readCommandRequest();
            command = commandRequest.getCommand();
        } while (command != Command.START);
        actions.get(command).accept(commandRequest, boardStrategy);
        return command;
    }

    private Command playChessByCommandRequest(BoardStrategy boardStrategy) {
        try {
            CommandRequest commandRequest = inputView.readCommandRequest();
            Command command = commandRequest.getCommand();
            actions.get(command).accept(commandRequest, boardStrategy);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return playChessByCommandRequest(boardStrategy);
        }
    }

    public void start(CommandRequest commandRequest, BoardStrategy boardStrategy) {
        gameId = chessGameService.start();
        outputView.printBoard(chessGameService.findChessBoard(gameId, boardStrategy));
    }

    public void move(CommandRequest commandRequest, BoardStrategy boardStrategy) {
        chessGameService.move(gameId, commandRequest, boardStrategy);
        outputView.printBoard(chessGameService.findChessBoard(gameId, boardStrategy));
    }

    private void status(final CommandRequest commandRequest, final BoardStrategy boardStrategy) {
        outputView.printStatus(chessGameService.findStatus(gameId, boardStrategy));
    }

    private void end(final CommandRequest commandRequest, final BoardStrategy boardStrategy) {
        chessGameService.end(gameId, boardStrategy);
    }

    private void finishGameByCommand(Command command, BoardStrategy boardStrategy) {
        if (command == MOVE) {
            outputView.printWinner(chessGameService.findWinner(gameId, boardStrategy));
        }
    }

}
