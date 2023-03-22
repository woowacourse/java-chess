package chess.controller;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.boardStrategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.view.Command;

import static chess.view.InputView.readStateCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printExceptionMessage;
import static chess.view.OutputView.printStartGuideMessage;

public class Controller {

    private static final String FIRST_COMMAND_ERROR_MESSAGE = "첫 번째로는 start, end만 입력 가능합니다";

    public void playChessGame() {
        printStartGuideMessage();
        ChessGame chessGame;

        Command command = getfirstCommand();
        while (isCommandStart(command)) {
            chessGame = new ChessGame(new InitialBoardStrategy());
            printChessGame(chessGame);
            command = inputMoveCommand(chessGame);
        }
    }

    private Command getfirstCommand() {
        try {
            CommandDto commandDto = readStateCommand();
            Command command = commandDto.getCommand();
            checkFirstCommand(command);
            return command;
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return getfirstCommand();
        }
    }

    private void checkFirstCommand(Command command) {
        if (command == Command.MOVE) {
            throw new IllegalArgumentException(FIRST_COMMAND_ERROR_MESSAGE);
        }
    }

    private boolean isCommandStart(Command command) {
        return command == Command.START;
    }

    private Command inputMoveCommand(ChessGame chessGame) {
        try {
            CommandDto commandDto = readStateCommand();
            Command command = commandDto.getCommand();

            while (command == Command.MOVE) {
                Position start = new Position(
                        Column.findColumnByValue(commandDto.getColumnOfStartSource())
                        , Rank.findRankByValue(commandDto.getRankOfStartSource()));

                Position end = new Position(
                        Column.findColumnByValue(commandDto.getColumnOfEndSource()),
                        Rank.findRankByValue(commandDto.getRankOfEndSource()));

                chessGame.move(start, end);
                printChessGame(chessGame);

                commandDto = readStateCommand();
                command = commandDto.getCommand();
            }
            return command;

        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return inputMoveCommand(chessGame);
        }
    }

    private void printChessGame(ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getChessBoard());
        printBoard(boardDto);
    }
}
