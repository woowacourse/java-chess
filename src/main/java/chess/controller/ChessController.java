package chess.controller;

import chess.controller.dao.ChessGameDao;
import chess.controller.dto.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Supplier;

import static chess.controller.ChessGameCommand.START;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        if (readChessStartCommand() == START) {
            ChessGameDao chessGameDao = new ChessGameDao();
            ChessGame savedChessGame = chessGameDao.findAll();
            outputView.printChessBoard(ChessBoardDto.from(savedChessGame.getBoard()));
            playChessGame(savedChessGame);
        }
    }

    private ChessGameCommand readChessStartCommand() {
        return repeatUntilGetValidInput(
                () -> ChessGameCommand.generateExecuteCommand(inputView.readChessExecuteCommand())
        );
    }

    private void playChessGame(ChessGame savedChessGame) {
        try {
            repeatMove(savedChessGame);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            playChessGame(savedChessGame);
        }
    }

    private void repeatMove(ChessGame savedChessGame) {
        String gameCommandInput = inputView.readChessGameCommand();
        while (!ChessGameCommand.isEnd(gameCommandInput)) {
            MoveCommand chessMoveCommand = MoveCommand.from(gameCommandInput);
            savedChessGame.move(chessMoveCommand.getSource(), chessMoveCommand.getDestination());
            outputView.printChessBoard(ChessBoardDto.from(savedChessGame.getBoard()));
            gameCommandInput = getReadChessGameCommand(savedChessGame);
        }
    }

    private String getReadChessGameCommand(ChessGame savedChessGame) {
        System.out.println(savedChessGame.isFinished());
        if (savedChessGame.isFinished()) {
            outputView.printResult(savedChessGame.getResult());
            ChessGameDao chessGameDao = new ChessGameDao();
            chessGameDao.save(savedChessGame);
            return "end";
        }
        return inputView.readChessGameCommand();
    }

    private <T> T repeatUntilGetValidInput(final Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return repeatUntilGetValidInput(inputReader);
        }
    }
}
