package chess.machine;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.Turn;
import chess.domain.chessGame.generator.ChessSpaceGenerator;
import chess.domain.chessGame.generator.SpaceGenerator;
import chess.service.ChessBoardService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessMachine {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessBoardService chessBoardService;

    public ChessMachine(OutputView outputView, InputView inputView, ChessBoardService chessBoardService) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessBoardService = chessBoardService;
    }

    public void run() {
        outputView.printStartGameMessage();
        outputView.printCommandGuideMessage();

        Command command = inputView.readCommand();
        validateFirstCommand(command);
        ChessGame chessGame = findOrCreateGame();
        command.conductCommand(chessGame, outputView);

        playChess(chessGame);
        printStatus(chessGame);
    }

    private ChessGame findOrCreateGame() {
        try {
            SpaceGenerator spaceGenerator = chessBoardService.findSpaceGenerator();
            Turn turn = chessBoardService.findTurn();
            ChessBoard chessBoard = ChessBoard.of(spaceGenerator, turn);
            return new ChessGame(chessBoard);
        } catch (IllegalArgumentException e) {
            outputView.printMessage(e.getMessage());
        }

        ChessBoard chessBoard = ChessBoard.create(new ChessSpaceGenerator());
        return new ChessGame(chessBoard);
    }

    private void validateFirstCommand(Command command) {
        if (command.getClass() != Start.class) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }

    private void playChess(ChessGame chessGame) {
        while (chessGame.isActive()) {
            Command command = inputView.readCommand();
            command.conductCommand(chessGame, outputView);
        }
    }

    private void printStatus(ChessGame chessGame) {
        Command command = Status.of("status");
        command.conductCommand(chessGame, outputView);
    }
}
