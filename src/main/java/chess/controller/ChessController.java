package chess.controller;

import static chess.view.InputView.requestStartOrEndInput;
import static chess.view.InputView.requestStartOrMoveOrEndInput;
import static chess.view.InputView.requestStatusOrEndInput;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printChessGameInitInstruction;
import static chess.view.OutputView.printForceEndInstruction;
import static chess.view.OutputView.printForceQuitStatus;
import static chess.view.OutputView.printGameInstructions;
import static chess.view.OutputView.printGameOverInstructions;
import static chess.view.OutputView.printStatus;

import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.dto.MovePositionCommandDto;

public class ChessController {

    private static final String START = "start";
    private static final String END = "end";

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

        printBoard(new BoardDto(game));

        return game;
    }

    private ChessGame progressGameUntilEnd(ChessGame chessGame) {
        while (!chessGame.isEnd() && !playerWantToEndStatus) {
            chessGame = progressByCommand(chessGame, requestStartOrMoveOrEndInput());
        }
        return chessGame;
    }

    private ChessGame progressByCommand(ChessGame chessGame, CommandDto commandDto) {
        String state = commandDto.gameState();
        if (state.equals(START)) {
            return initAndShowBoard();
        }
        if (state.equals(END)) {
            return end(chessGame);
        }
        return move(chessGame, commandDto.command());
    }

    private ChessGame end(ChessGame chessGame) {
        printForceEndInstruction();

        playerWantToEndStatus = true;

        return chessGame;
    }

    private ChessGame move(ChessGame chessGame, String command) {
        chessGame.moveChessmen(new MovePositionCommandDto(command));
        printBoard(new BoardDto(chessGame));
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

    private void forceQuitGameResult(ChessGame chessGame) {
        printForceQuitStatus(chessGame.calculateGameResult());
    }

    private void normalQuitGameResult(ChessGame chessGame) {
        printStatus(chessGame.calculateGameResult());
    }

}
