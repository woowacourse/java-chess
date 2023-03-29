package chess.controller;

import chess.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<CommandType, PrintAction> commandToPrintAction;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.commandToPrintAction = new EnumMap<>(CommandType.class);
    }

    public void run() {
//        setPrintActions();
//
//        Command gameCommand = Command.EMPTY_COMMAND;
//
//        outputView.printInstructions();
//
//        while (gameCommand.getCommandType() != CommandType.END) {
//            try {
//                gameCommand = inputView.readCommand();
//                final PrintAction printAction = commandToPrintAction.get(gameCommand.getCommandType());
//                gameStatus = gameStatus.processCommand(gameCommand, printAction);
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }

    private void printScores(final ChessGameService chessGameService, final ChessGame chessGame) {
        if (chessGame.isGameOver()) {
            throw new IllegalArgumentException("게임이 진행 중이지 않습니다");
        }
        outputView.printScore(chessGameService.calculateScores(chessGame));
    }

    private void printResultIfGameOver(final ChessGameService chessGameService, final ChessGame chessGame) {
        if (chessGameService.isGameOver(chessGame)) {
            outputView.printResult(chessGameService.getResult(chessGame));
        }
    }

    private void setPrintActions() {
        commandToPrintAction.put(CommandType.START, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
        commandToPrintAction.put(CommandType.MOVE, chessBoardDto -> outputView.printChessBoard((ChessBoardDto) chessBoardDto));
    }
}
