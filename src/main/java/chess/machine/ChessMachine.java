package chess.machine;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.generator.ChessSpaceGenerator;
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
        saveGameIfKingIsNotDead(chessGame);
        printStatus(chessGame);
    }

    private void validateFirstCommand(Command command) {
        if (command.getClass() != Start.class) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }

    private ChessGame findOrCreateGame() {
        try {
            ChessBoard chessBoard = chessBoardService.findChessBoard();
            outputView.printMessage("저장된 데이터로 게임을 시작합니다");
            return new ChessGame(chessBoard);
        } catch (IllegalArgumentException e) {
            outputView.printMessage("데이터가 없어 새로 게임을 시작합니다");
        }

        ChessBoard chessBoard = ChessBoard.create(new ChessSpaceGenerator());
        return new ChessGame(chessBoard);
    }

    private void playChess(ChessGame chessGame) {
        while (chessGame.isActive()) {
            Command command = inputView.readCommand();
            command.conductCommand(chessGame, outputView);
        }
    }

    private void saveGameIfKingIsNotDead(ChessGame chessGame) {
        if (chessGame.isEnd()) {
            chessBoardService.deleteChessBoard();
            return;
        }
        chessBoardService.saveChessBoard(chessGame.getChessBoard());
    }

    private void printStatus(ChessGame chessGame) {
        Command command = Status.of("status");
        command.conductCommand(chessGame, outputView);
    }
}
