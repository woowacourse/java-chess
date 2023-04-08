package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
//        outputView.printInstructions();
//
//        ChessGameService chessGameService = new ChessGameService();
//        ChessGame chessGame = ChessGame.getUnplayableGame();
//        Command command = Command.EMPTY_COMMAND;
//        execute(chessGameService, chessGame, command);

    }

    private void execute(final ChessGameService chessGameService, ChessGame chessGame, Command command) {

        while (!command.isEnd()) {
            try {
                command = inputView.readCommand();

                if (command.isStart()) {
                    chessGame = startGame(chessGameService, chessGame);
                }
                if (command.isMove()) {
                    movePiece(command, chessGameService, chessGame);
                }
                if (command.isStatus()) {
                    printScores(chessGameService, chessGame);
                }

                printResultIfGameOver(chessGameService, chessGame);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private ChessGame startGame(final ChessGameService chessGameService, ChessGame chessGame) {
//        if (!chessGame.isGameOver()) {
//            throw new IllegalArgumentException("게임이 진행 중입니다");
//        }
//        chessGame = chessGameService.readGame();
//        outputView.printChessBoard(chessGameService.getChessBoard(chessGame));
//        return chessGame;
        return null;
    }

    private void movePiece(final Command command, final ChessGameService chessGameService, final ChessGame chessGame) {
        final MoveCommand moveCommand = new MoveCommand(command);

//        chessGameService.moveWithCapture(chessGame, moveCommand);
    }

    private void printScores(final ChessGameService chessGameService, final ChessGame chessGame) {
//        if (chessGame.isGameOver()) {
//            throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
//        }
//        outputView.printScore(chessGameService.calculateScores(chessGame));
    }

    private void printResultIfGameOver(final ChessGameService chessGameService, final ChessGame chessGame) {
//        if (chessGameService.isGameOver(chessGame)) {
//            outputView.printResult(chessGameService.getResult(chessGame));
//        }
    }
}
