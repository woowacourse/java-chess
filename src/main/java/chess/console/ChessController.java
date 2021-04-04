package chess.console;

import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.console.view.dto.BoardDto;

import java.util.Arrays;

public class ChessController {

    public void run() {
        Board board = initializeBoard();
        ChessGame chessGame = initializeGame(board);
        Commands commands = initializeCommands(chessGame);

        OutputView.printStartMessage();

        while (!chessGame.isFinished()) {
            turn(board, chessGame, commands);
        }
    }

    private Board initializeBoard() {
        return new Board(PieceFactory.createPieces());
    }

    private Commands initializeCommands(ChessGame chessGame) {
        return new Commands(
                Arrays.asList(
                        new StartCommand(chessGame),
                        new MoveCommand(chessGame),
                        new EndCommand(chessGame),
                        new StatusCommand(chessGame))
        );
    }

    private ChessGame initializeGame(Board board) {
        return new ChessGame(board);
    }

    private void turn(Board board, ChessGame chessGame, Commands commands) {
        try {
            String input = InputView.inputCommandFromUser();

            Command command = commands.getIfPresent(input);
            command.execute(input);

            print(board, chessGame, command);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void print(Board board, ChessGame chessGame, Command command) {
        printScoreIfStatusCommand(command);
        printBoard(chessGame, board);
        printWinner(chessGame);
    }

    private void printWinner(ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            return;
        }

        chessGame.getWinnerColorNotation()
                .ifPresent(OutputView::printWinner);
    }

    private void printBoard(ChessGame chessGame, Board board) {
        if (chessGame.isFinished()) {
            return;
        }

        OutputView.drawBoard(new BoardDto(board));
    }

    private void printScoreIfStatusCommand(Command command) {
        if (!command.isStatus()) {
            return;
        }

        StatusCommand statusCommand = (StatusCommand) command;
        OutputView.printScore(statusCommand.getWhiteScore(), statusCommand.getBlackScore());
    }

}
