package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import chess.domain.position.Position;
import chess.dto.ChessBoardDto;
import chess.dto.CommandRequest;
import chess.repository.ChessGameDao;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Optional;

public class ChessController {

    private static final String WRONG_START_ERROR_MESSAGE = "게임이 진행 중 일 때는 시작할 수 없습니다.";
    private static final String START_NEED_ERROR_MESSAGE = "게임이 시작되어야 합니다.";

    private final InputView inputView;
    private final OutputView outputView;
    private ChessGame chessGame;
    private final ChessGameDao chessGameDao;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.chessGameDao = new ChessGameDao();
    }

    public void run() {
        outputView.printStartMessage();
        startNewGame();
        play();
    }

    private void startNewGame() {
        boolean isNotStarted = true;
        while (isNotStarted) {
            isNotStarted = repeatStartRequest();
        }
        createGame();
        printBoard();
    }

    private boolean repeatStartRequest() {
        try {
            readStartCommand();
            return false;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
        return true;
    }

    private void readStartCommand() {
        if (inputView.requestGameCommand().getCommand() != Command.START) {
            throw new IllegalArgumentException(START_NEED_ERROR_MESSAGE);
        }
    }

    private void createGame() {
        Optional<ChessGame> game = chessGameDao.findLastGame();
        if (game.isPresent()) {
            chessGame = game.get();
            return;
        }
        createNewGame();
    }

    private void createNewGame() {
        ChessGame newGame = new ChessGame(new ChessBoard(InitialPiece.getPiecesWithPosition()));
        chessGame = chessGameDao.save(newGame);
        chessGame.saveAllPieces();
    }

    private void printBoard() {
        outputView.printBoard(ChessBoardDto.from(chessGame.getChessBoard()));
    }

    private void play() {
        CommandRequest request;
        while ((request = repeatProgressRequest()).getCommand() != Command.END) {
            progressMoveCommand(request);
            progressStatusCommand(request);
        }
    }

    private CommandRequest repeatProgressRequest() {
        CommandRequest request = null;
        while (request == null) {
            request = readProgressCommand();
        }
        return request;
    }

    private CommandRequest readProgressCommand() {
        try {
            return readCommandExceptStart();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
        return null;
    }

    private CommandRequest readCommandExceptStart() {
        CommandRequest request = inputView.requestGameCommand();
        if (request.getCommand() == Command.START) {
            throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
        }
        return request;
    }

    private void progressMoveCommand(CommandRequest request) {
        if (request.getCommand() == Command.MOVE) {
            progressMove(request);
            chessGameDao.updateTurn(chessGame.getGameId(), chessGame.getTeamColor());
        }
    }

    private void progressMove(CommandRequest request) {
        try {
            chessGame.move(Position.from(request.getSource()),
                Position.from(request.getDestination()));
            printBoard();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
    }

    private void progressStatusCommand(CommandRequest request) {
        if (request.getCommand() == Command.STATUS) {
            printGameResult();
        }
    }

    private void printGameResult() {
        outputView.printCurrentScore(chessGame.getCurrentScore());
        try {
            outputView.printWinner(chessGame.findWinningTeam().name());
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
        }
    }

}
