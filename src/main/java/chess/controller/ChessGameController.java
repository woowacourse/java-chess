package chess.controller;

import static chess.utils.Constant.STATUS_COMMAND;

import chess.dao.ConnectionGenerator;
import chess.dao.GameInformationDao;
import chess.dao.ProductConnectionGenerator;
import chess.domain.board.ChessBoard;
import chess.domain.board.GameInformation;
import chess.domain.piece.Color;
import chess.domain.state.End;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.vo.Score;
import chess.dto.ChessBoardDto;
import chess.dto.CurrentResultDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChessGameController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final GameInformationDao gameInformationDao = new GameInformationDao(new ProductConnectionGenerator());

    public void run() {
        ChessBoard chessBoard = prepareChessBoard(new ProductConnectionGenerator());
        outputView.printStartMessage(chessBoard.getGameId());

        playGame(chessBoard);
    }

    private void playGame(ChessBoard chessBoard) {
        GameState gameState = new Ready(chessBoard);

        while (!gameState.isEnd()) {
            GameState currentGameState = gameState;
            gameState = repeatUntilSuccess(() -> playEachTurn(currentGameState));

            printChessBoardInProgress(gameState, chessBoard);
        }
        handleKingCapture((End) gameState, chessBoard);
    }

    private ChessBoard prepareChessBoard(ConnectionGenerator connectionGenerator) {
        List<GameInformation> gameInfos = gameInformationDao.findAll();
        outputView.printGameInformation(gameInfos);

        int gameId = repeatUntilSuccess(() -> inputView.readGameId(gameInfos));
        return ChessBoard.from(gameId, connectionGenerator);
    }

    private void handleKingCapture(End gameState, ChessBoard chessBoard) {
        if (gameState.isEndByKingCaptured()) {
            printResultByKingCaptured(chessBoard);
            gameInformationDao.remove(chessBoard.getGameId());
        }
    }

    private void printResultByKingCaptured(ChessBoard chessBoard) {
        outputView.printChessBoard(new ChessBoardDto(chessBoard));
        outputView.printResultWithKingCaptured(chessBoard.findWinnerByKing());
    }

    private GameState playEachTurn(GameState gameState) {
        List<String> command = inputView.readCommand();
        if (command.get(0).equals(STATUS_COMMAND)) {
            printCurrentScore(gameState);
            return gameState;
        }
        return gameState.play(command);
    }

    private void printCurrentScore(GameState gameState) {
        Score blackScore = gameState.calculateScore(Color.BLACK);
        Score whiteScore = gameState.calculateScore(Color.WHITE);
        Color winnerColor = gameState.getWinnerColor();
        CurrentResultDto currentResultDto = new CurrentResultDto(blackScore, whiteScore, winnerColor);
        outputView.printEachTeamScore(currentResultDto);
    }

    private void printChessBoardInProgress(GameState gameState, ChessBoard chessBoard) {
        if (!gameState.isEnd()) {
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard);
            outputView.printChessBoard(chessBoardDto);
        }
    }

    private <T> T repeatUntilSuccess(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatUntilSuccess(supplier);
        }
    }
}
