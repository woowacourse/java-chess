package chess.controller;

import static chess.view.InputView.requestMoveOrEndOrStatusInput;
import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.InputView.requestStatusOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printChessGameInitInstruction;
import static chess.view.OutputView.printForceEndInstruction;
import static chess.view.OutputView.printForceQuitStatus;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printIntermediateGameResult;
import static chess.view.OutputView.printStatus;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.piece.ChessmenInitializer;
import chess.dto.ChessGameDto;
import chess.dto.CommandDto;
import chess.dto.MovePositionCommandDto;
import chess.view.boardview.BoardView;

public class ChessController {

    private boolean playerWantToEndStatus = false;

    public void run() {
        printGameInstructions();
        if (!requestStartOrEndInput()) {
            return;
        }
        ChessGame chessGame = initAndShowBoard();

        chessGame = progressGameUntilEnd(chessGame);

        endOrShowGameResult(chessGame);
    }

    private ChessGame initAndShowBoard() {
        printChessGameInitInstruction();

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
        ChessGame game = ChessGame.of(chessmenInitializer.init());

        printBoard(new BoardView(new ChessGameDto(game)));

        return game;
    }

    private ChessGame progressGameUntilEnd(ChessGame chessGame) {
        while (!chessGame.isEnd() && !playerWantToEndStatus) {
            chessGame = progressByCommand(chessGame, requestMoveOrEndOrStatusInput());
            printBoard(new BoardView(new ChessGameDto(chessGame)));
        }
        return chessGame;
    }

    private ChessGame progressByCommand(ChessGame chessGame, CommandDto commandDto) {
        Command command = commandDto.getCommand();
        if (command.isStatus()) {
            intermediateGameResult(chessGame);
            return chessGame;
        }
        if (command.isEnd()) {
            return end(chessGame);
        }
        return move(chessGame, commandDto.getFullCommand());
    }

    private ChessGame end(ChessGame chessGame) {
        printForceEndInstruction();

        playerWantToEndStatus = true;

        return chessGame;
    }

    private ChessGame move(ChessGame chessGame, String command) {
        chessGame.moveChessmen(new MovePositionCommandDto(command));
        return chessGame;
    }

    private void endOrShowGameResult(ChessGame chessGame) {
        if (playerWantToEndStatus) {
            forceQuitGameResult(chessGame);
            return;
        }

        printGameOverInstructions();

        if (requestStatusOrEndInput()) {
            normalQuitGameResult(chessGame);
        }
    }

    private void intermediateGameResult(ChessGame chessGame) {
        printIntermediateGameResult(chessGame.calculateGameResult());
    }

    private void forceQuitGameResult(ChessGame chessGame) {
        printForceQuitStatus(chessGame.calculateGameResult());
    }

    private void normalQuitGameResult(ChessGame chessGame) {
        printStatus(chessGame.calculateGameResult());
    }

}
