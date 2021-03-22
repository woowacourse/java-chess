package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.LineDto;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.command.Status;
import chess.domain.game.ChessGame;
import chess.utils.DtoAssembler;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessGameController {
    public void run() {
        ChessGame chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
        OutputView.printCommandNotice();
        while (chessGame.isNotEnd()) {
            chessGame = new ChessGame(new Board(InitBoardGenerator.initLines()));
            Commands commands = Commands.initCommands(chessGame);
            runningChess(chessGame, commands);
            afterFinishChess(chessGame, commands);
        }
    }

    private void runningChess(ChessGame chessGame, Commands commands) {
        while (chessGame.isNotFinished()) {
            commandExecution(chessGame, commands);
            printBoardStatus(chessGame);
        }
    }

    private void afterFinishChess(ChessGame chessGame, Commands commands) {
        while (chessGame.isNotEnd()) {
            commandExecution(chessGame, commands);
            OutputView.printFinishWithReason(chessGame.finishReason());
        }
    }

    private void printBoardStatus(ChessGame chessGame) {
        List<LineDto> boardDto = DtoAssembler.assemble(chessGame.board());
        OutputView.printBoard(boardDto);
    }

    private void commandExecution(ChessGame chessGame, Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            printStatus(chessGame, command);
            command.execution(input);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }

    private void printStatus(ChessGame chessGame, Command command) {
        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printWinner(chessGame.winner());
            OutputView.printScoreStatus(status.totalWhiteScore(), status.totalBlackScore());
        }
    }
}
