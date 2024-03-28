package controller;

import domain.game.ChessGame;
import domain.game.GameRequest;
import domain.game.Piece;
import domain.game.TeamColor;
import domain.position.Position;
import domain.service.DBService;
import dto.BoardDto;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DBService dbService;

    public ChessController(final InputView inputView, final OutputView outputView, final DBService dbService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.dbService = dbService;
    }

    public void run() {
        outputView.printWelcomeMessage();
        GameRequest gameRequest = readUserInput(inputView::inputGameCommand).asRequest();
        while (gameRequest.isStart() || gameRequest.isLoad()) {
            startGame(gameRequest);
            outputView.printRestartMessage();
            gameRequest = readUserInput(inputView::inputGameCommand).asRequest();
        }
    }

    private <T> T readUserInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void startGame(GameRequest gameRequest) {
        ChessGame chessGame = createGame(gameRequest);
        printBoardStatus(chessGame.getPositionsOfPieces());

        while (shouldProceedGame(gameRequest, chessGame)) {
            outputView.printCurrentTurn(chessGame.currentPlayingTeam());
            gameRequest = readUserInput(inputView::inputGameCommand).asRequest();
            processRequest(gameRequest, chessGame);
        }
        finishGame(gameRequest, chessGame);
    }

    private ChessGame createGame(GameRequest gameRequest) {
        if (gameRequest.isStart()) {
            return new ChessGame();
        }
        int gameId = readUserInput(inputView::inputGameId);
        return dbService.loadGame(gameId);
    }

    private void printBoardStatus(Map<Position, Piece> positionOfPieces) {
        BoardDto boardDto = BoardDto.from(positionOfPieces);
        outputView.printBoard(boardDto);
    }

    private boolean shouldProceedGame(GameRequest gameRequest, ChessGame chessGame) {
        return gameRequest.isContinuable() && !chessGame.isGameEnd();
    }

    private void processRequest(GameRequest gameRequest, ChessGame chessGame) {
        if (gameRequest.isSave()) {
            saveCurrentStatus(chessGame);
            return;
        }
        if (gameRequest.isContinuable()) {
            playRound(gameRequest, chessGame);
        }
    }

    private void saveCurrentStatus(ChessGame chessGame) {
        int gameId = dbService.saveGame(chessGame);
        outputView.printSaveResult(gameId);
    }

    private void playRound(GameRequest gameRequest, ChessGame chessGame) {
        try {
            chessGame.move(gameRequest.source(), gameRequest.destination());
            printBoardStatus(chessGame.getPositionsOfPieces());
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private void finishGame(GameRequest gameRequest, ChessGame chessGame) {
        outputView.printGameEndMessage();
        if (gameRequest.isEnd()) {
            return;
        }

        outputView.printStatusInputMessage();
        gameRequest = readUserInput(inputView::inputGameCommand).asRequest();
        if (gameRequest.isStatus()) {
            printGameResult(chessGame);
        }
    }

    private void printGameResult(ChessGame chessGame) {
        TeamColor winner = chessGame.getWinner();
        double whiteScore = chessGame.currentScoreOf(TeamColor.WHITE);
        double blackScore = chessGame.currentScoreOf(TeamColor.BLACK);

        outputView.printWinner(winner);
        outputView.printScore(TeamColor.WHITE, whiteScore);
        outputView.printScore(TeamColor.BLACK, blackScore);
    }
}
