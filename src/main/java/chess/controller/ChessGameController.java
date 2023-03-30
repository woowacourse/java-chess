package chess.controller;

import chess.domain.Color;
import chess.domain.CommandAction;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.dto.response.PiecesResponse;
import chess.service.ChessGameService;
import chess.ui.InputView;
import chess.ui.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ChessGameController {

    private ChessGameService chessGameService;

    private final CommandAction commandAction = new CommandAction(Map.of(
            Command.START, this::start,
            Command.MOVE, this::move,
            Command.END, this::end,
            Command.STATUS, this::status)
    );

    public ChessGameController() {
        this.chessGameService = new ChessGameService();
    }

    public void run() {
        OutputView.printStartGame();
        Commands commandWithArguments;
        do {
            commandWithArguments = readCommand(InputView::getCommands);
            commandAction.execute(commandWithArguments);
        } while (commandWithArguments.isNotEnd() && !chessGameService.notEveryKingAlive());
        finishGame();
    }

    private void finishGame() {
        if (chessGameService.notEveryKingAlive()) {
            OutputView.printWinner(chessGameService.getWinnerColorName());
            PieceDao dao = PieceDaoImpl.getInstance();
            dao.deleteAll();
        }
    }

    private Commands readCommand(Supplier<List<String>> commandReader) {
        try {
            List<String> arguments = commandReader.get();
            return new Commands(arguments);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand(commandReader);
        }
    }

    private void start(final Commands commands) {
        chessGameService = new ChessGameService();
        PiecesResponse piecesResponse = new PiecesResponse(
                chessGameService.getPiecesByColor(Color.WHITE),
                chessGameService.getPiecesByColor(Color.BLACK)
        );
        OutputView.printInitializedChessBoard(piecesResponse);
    }

    private void status(final Commands commands) {
        OutputView.printStatus(chessGameService.calculateScore(), chessGameService.getCurrentTurn());
    }

    private void move(final Commands commands) {
        String inputMovablePiece = commands.getMovablePiece();
        String inputTargetPosition = commands.getTargetPosition();

        try {
            chessGameService.movePiece(inputMovablePiece, inputTargetPosition);
            OutputView.printChessBoardStatus(
                    new PiecesResponse(chessGameService.getPiecesByColor(Color.WHITE),
                            chessGameService.getPiecesByColor(Color.BLACK)));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void end(final Commands commands) {
    }

}
