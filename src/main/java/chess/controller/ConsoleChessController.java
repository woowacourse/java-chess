package chess.controller;

import static chess.console.view.InputView.requestMoveOrEndOrStatusInput;
import static chess.console.view.InputView.requestStartOrEndInput;
import static chess.console.view.InputView.requestStatusOrEndInput;

import chess.console.view.OutputView;
import chess.console.view.boardview.BoardView;
import chess.domain.game.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.MoveCommand;
import chess.domain.game.GameResult;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.dto.ChessGameDto;
import chess.dto.CommandDto;
import chess.dto.GameResultDto;

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
        chessGame.moveChessmen(new MoveCommand(command));
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
        GameResult gameResult = GameResult.calculate(chessGame.getChessmen());

        GameResultDto gameResultDto = new GameResultDto(gameResult.getWinner(), gameResult.getWhiteScore(),
            gameResult.getBlackScore());

        OutputView.printIntermediateGameResult(gameResultDto);
    }

    private void forceQuitGameResult(ChessGame chessGame) {
        GameResult gameResult = GameResult.calculate(chessGame.getChessmen());

        GameResultDto gameResultDto = new GameResultDto(gameResult.getWinner(), gameResult.getWhiteScore(),
            gameResult.getBlackScore());

        OutputView.printForceQuitStatus(gameResultDto);
    }

    private void normalQuitGameResult(ChessGame chessGame) {
        GameResult gameResult = GameResult.calculate(chessGame.getChessmen());

        GameResultDto gameResultDto = new GameResultDto(gameResult.getWinner(), gameResult.getWhiteScore(),
            gameResult.getBlackScore());

        OutputView.printStatus(gameResultDto);
    }

}
