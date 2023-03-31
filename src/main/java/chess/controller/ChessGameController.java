package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandAction;
import chess.controller.command.Commands;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.dao.TurnDaoImpl;
import chess.domain.model.Score;
import chess.domain.model.position.Position;
import chess.domain.service.ChessGameService;
import chess.dto.response.PiecesResponse;
import chess.ui.InputView;
import chess.ui.OutputView;
import java.util.List;
import java.util.Map;

public final class ChessGameController {
    private final ChessGameService chessGameService;
    private final CommandAction commandAction = new CommandAction(Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status)
    );


    public ChessGameController() {
        this.chessGameService = new ChessGameService(
                new TurnDaoImpl(), new PieceDaoImpl());
    }

    public void run() {
        chessGameService.initialize();
        OutputView.printStartGame();
        boolean isCommandNotEnd = true;
        while (chessGameService.isEveryKingAlive() && isCommandNotEnd) {
            Commands command = readCommand();
            commandAction.execute(command);
            isCommandNotEnd = command.isNotEnd();
        }
        chessGameService.finishGame();
    }
    private Commands readCommand() {
        try {
            List<String> arguments = InputView.getCommands();
            return new Commands(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }

    private void start(final Commands commands) {
        PiecesResponse piecesResponse = chessGameService.start();
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void move(final Commands commands) {
        try {
            Position sourcePosition = commands.getSourcePosition();
            Position targetPosition = commands.getTargetPosition();
            PiecesResponse piecesResponse = chessGameService.move(sourcePosition, targetPosition);
            OutputView.printChessBoardStatus(piecesResponse);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final Commands commands) {
    }

    private void status(final Commands commands) {
        Score score = chessGameService.calculateScore();
        OutputView.printStatus(score.getValue());
    }


}
