package chess.controller;

import chess.domain.chessGame.ChessBoard;
import chess.dto.request.CommandDto;
import chess.dto.response.ChessBoardDto;
import chess.dto.response.StatusDto;
import chess.service.ChessService;
import chess.utils.BoardToString;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public final class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        OutputView.printStartMessage();
        CommandDto commandDto = InputView.readOnlyInitialCommand();
        if (commandDto.getGameCommand() == GameCommand.START) {
            startGame();
        }
    }

    private void startGame() {
        ChessBoard chessBoard = chessService.setUpChessBoard();
        showChessBoardStatus(chessBoard);
        repeat(() -> playGame(chessBoard));
    }

    private void showChessBoardStatus(ChessBoard chessBoard) {
        List<String> convertedBoard = BoardToString.convert(chessBoard.getChessBoard());
        ChessBoardDto chessBoardDto = ChessBoardDto.of(convertedBoard);
        OutputView.printChessBoard(chessBoardDto);

        String turn = chessBoard.getTurn();
        OutputView.printTurn(turn);
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void playGame(ChessBoard chessBoard) {
        CommandDto commandDto;
        do {
            commandDto = InputView.readPlayingCommand();
            executePlayingCommand(chessBoard, commandDto);
        } while (isGameNotEnd(commandDto, chessBoard));
    }

    private void executePlayingCommand(ChessBoard chessBoard, CommandDto commandDto) {
        GameCommand gameCommand = commandDto.getGameCommand();
        if (gameCommand == GameCommand.MOVE) {
            ChessBoard afterMove = chessService.executeMoveCommand(chessBoard, commandDto);
            showChessBoardStatus(afterMove);
        }
        if (gameCommand == GameCommand.STATUS) {
            StatusDto statusDto = chessService.getGameStatus(chessBoard);
            OutputView.printStatus(statusDto);
        }
    }

    private boolean isGameNotEnd(CommandDto commandDto, ChessBoard chessBoard) {
        if (!chessService.checkKingAlive(chessBoard)) {
            OutputView.printKingDeadMessage();
            return false;
        }
        return (commandDto.getGameCommand() != GameCommand.END);
    }
}
