package chess.controller;

import static chess.console.consoleview.InputView.requestMoveOrEndOrStatusInput;
import static chess.console.consoleview.InputView.requestStartOrEndInput;
import static chess.console.consoleview.InputView.requestStatusOrEndInput;

import chess.console.consoleview.OutputView;
import chess.console.consoleview.boardview.BoardView;
import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.dto.ChessGameDto;
import chess.dto.CommandDto;
import chess.dto.MovePositionCommandDto;

public class ConsoleChessController {

    private boolean playerWantToEndStatus = false;
    private Color turn = Color.BLACK;

    public void run() {
        OutputView.printGameInstructions();
        if (!requestStartOrEndInput()) {
            return;
        }
        ChessGame chessGame = initAndShowBoard();

        chessGame = progressGameUntilEnd(chessGame);

        endOrShowGameResult(chessGame);
    }

    private ChessGame initAndShowBoard() {
        OutputView.printChessGameInitInstruction();

        ChessmenInitializer chessmenInitializer = new ChessmenInitializer();

        ChessGame game = ChessGame.of(chessmenInitializer.init());

        OutputView.printBoard(new BoardView(new ChessGameDto(game)));

        return game;
    }

    private ChessGame progressGameUntilEnd(ChessGame chessGame) {
        while (!chessGame.isEnd() && !playerWantToEndStatus) {
            chessGame = progressByCommand(chessGame, requestMoveOrEndOrStatusInput());
            OutputView.printBoard(new BoardView(new ChessGameDto(chessGame)));
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
        OutputView.printForceEndInstruction();

        playerWantToEndStatus = true;

        return chessGame;
    }

    private ChessGame move(ChessGame chessGame, String command) {
        chessGame.moveChessmen(new MovePositionCommandDto(command));
        turn = turn.nextTurn();
        return chessGame;
    }

    private void endOrShowGameResult(ChessGame chessGame) {
        if (playerWantToEndStatus) {
            forceQuitGameResult(chessGame);
            return;
        }

        OutputView.printGameOverInstructions();

        if (requestStatusOrEndInput()) {
            normalQuitGameResult(chessGame);
        }
    }

    private void intermediateGameResult(ChessGame chessGame) {
        OutputView.printIntermediateGameResult(chessGame.calculateGameResult());
    }

    private void forceQuitGameResult(ChessGame chessGame) {
        OutputView.printForceQuitStatus(chessGame.calculateGameResult());
    }

    private void normalQuitGameResult(ChessGame chessGame) {
        OutputView.printStatus(chessGame.calculateGameResult());
    }

}
