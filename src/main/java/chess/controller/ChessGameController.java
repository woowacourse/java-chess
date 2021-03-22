package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.LineDto;
import chess.domain.command.Command;
import chess.domain.command.Commands;
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
            commandExecution(commands);
            printBoardStatus(chessGame);
        }
    }

    private void afterFinishChess(ChessGame chessGame, Commands commands) {
        OutputView.printFinishWithReason(chessGame.finishReason());
        while (chessGame.isFinished()) {
            commandExecution(commands);
        }
    }

    private void printBoardStatus(ChessGame chessGame) {
        List<LineDto> boardDto = DtoAssembler.assemble(chessGame.board());
        OutputView.printBoard(boardDto);
    }

    private void commandExecution(Commands commands) {
        try {
            String input = InputView.command();
            Command command = commands.matchedCommand(input);
            command.execution(input);
        } catch (IllegalStateException | IllegalArgumentException e) {
            OutputView.printErrorException(e.getMessage());
        }
    }
}
