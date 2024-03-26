package controller;

import domain.GameCommand;
import domain.game.ChessGame;
import domain.game.Piece;
import domain.game.TeamColor;
import domain.position.Position;
import dto.BoardDto;
import dto.RequestDto;
import java.util.Map;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        GameCommand command = readUserInput(inputView::inputGameStart);
        while (command.isStart()) {
            startGame(command);
            outputView.printRestartMessage();
            command = readUserInput(inputView::inputGameStart);
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

    private void startGame(GameCommand command) {
        ChessGame chessGame = new ChessGame();
        printBoardStatus(chessGame.getPositionsOfPieces());

        RequestDto requestDto = RequestDto.of(command);  // TODO: 모두 호환되도록 GameRequest 같은 것으로 변경
        while (shouldProceedGame(requestDto, chessGame)) {
            outputView.printCurrentTurn(chessGame.currentPlayingTeam());
            requestDto = readUserInput(inputView::inputGameCommand);
            playRound(requestDto, chessGame);
        }

        // TODO: "status" 명령을 받을 때 출력하도록 변경
        printGameResult(chessGame);
    }

    private boolean shouldProceedGame(RequestDto requestDto, ChessGame chessGame) {
        return requestDto.command().isContinuable() && !chessGame.isGameEnd();
    }

    private void printBoardStatus(Map<Position, Piece> positionOfPieces) {
        BoardDto boardDto = BoardDto.from(positionOfPieces);
        outputView.printBoard(boardDto);
    }

    private void playRound(RequestDto requestDto, ChessGame chessGame) {
        try {
            Position source = requestDto.source();
            Position destination = requestDto.destination();
            chessGame.move(source, destination);
            printBoardStatus(chessGame.getPositionsOfPieces());
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printErrorMessage(e.getMessage());
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
