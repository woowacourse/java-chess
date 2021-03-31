package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandMenu;
import chess.controller.dto.BoardResponseDto;
import chess.controller.dto.GameResultDto;
import chess.controller.dto.ShowPathResponseDto;
import chess.controller.dto.StatusResponseDto;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.manager.ChessManager;
import chess.manager.Status;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        OutputView.printGuideStartGame();
        Command firstCommand = initFirstCommand();
        if (firstCommand.isEnd()) {
            return;
        }
        initCommand();
        OutputView.printGameResult(GameResultDto.toStatus(chessManager.getStatus()));
    }

    private Command initFirstCommand() {
        try {
            List<String> userInput = InputView.getUserCommand();
            Command command = CommandMenu.findCommandByInputCommand(this, userInput);
            validFirstCommand(command);
            command.execute();
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private void validFirstCommand(Command command) {
        if (command.isStart() || command.isEnd()) {
            return;
        }
        throw new IllegalArgumentException("첫 입력은 start(게임시작) 또는 end(게임종료)만 가능합니다.");
    }

    private void initCommand() {
        try {
            final Command command =
                    CommandMenu.findCommandByInputCommand(this, InputView.getUserCommand());
            command.execute();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            initCommand();
        }
    }

    public void startGame() {
        Board board = chessManager.getBoard();
        OutputView.printBoard(BoardResponseDto.toBoard(board));
    }

    public void restart() {
        chessManager.resetBoard();
        Board board = chessManager.getBoard();
        OutputView.printRestartGame(BoardResponseDto.toBoard(board));
        initCommand();
    }

    public void end() {
        OutputView.printEndGame();
    }

    public void move(final Position target, final Position source) {
        chessManager.move(target, source);
        chessManager.changeTurn();
        OutputView.printBoard(BoardResponseDto.toBoard(chessManager.getBoard()));
        if (chessManager.isDiedKing()) {
            end();
            return;
        }
        initCommand();
    }

    public void status() {
        Status status = chessManager.getStatus();
        StatusResponseDto statusResponseDto = StatusResponseDto.toStatus(status);
        OutputView.printStatus(statusResponseDto);
        initCommand();
    }

    public void show(final Position source) {
        BoardResponseDto boardResponseDto = BoardResponseDto.toBoard(chessManager.getBoard());
        ShowPathResponseDto showPathResponseDto =
                ShowPathResponseDto.toPath(chessManager.movablePath(source));
        OutputView.printMovablePath(boardResponseDto, showPathResponseDto);
        initCommand();
    }
}
