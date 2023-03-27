package chess.controller;

import chess.dao.DbChessGameDao;
import chess.domain.Position;
import chess.domain.board.strategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.view.Command;

import java.util.Map;

import static chess.view.Command.isEnd;
import static chess.view.Command.isMove;
import static chess.view.Command.isStart;
import static chess.view.Command.isStatus;
import static chess.view.ErrorMessage.FIRST_COMMAND_ERROR_MESSAGE;
import static chess.view.InputView.readStateCommand;
import static chess.view.OutputView.printBoard;
import static chess.view.OutputView.printCheckmateGuideMessage;
import static chess.view.OutputView.printExceptionMessage;
import static chess.view.OutputView.printScores;
import static chess.view.OutputView.printStartGuideMessage;

public class Controller {

    public void playChessGame() {
        printStartGuideMessage();
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        DbChessGameDao dao = new DbChessGameDao();
        dao.save(chessGame);
        dao.select();

        Command command = getFirstCommand();
        play(chessGame, command);
    }

    private void play(ChessGame chessGame, Command command) {
        while (gameCondition(chessGame, command)) {
            if (isEnd(command)) {
                break;
            }

            if (isStatus(command)) {
                printScores(chessGame.getChessBoard());
            }

            if (isStart(command)) {
                chessGame = new ChessGame(new InitialBoardStrategy());
                printChessGame(chessGame);
            }

            command = inputCommand(chessGame);
        }
    }

    private boolean gameCondition(ChessGame chessGame, Command command) {
        return !isKingDead(chessGame) && (isStart(command) || isStatus(command));
    }

    private Command getFirstCommand() {
        try {
            CommandDto commandDto = readStateCommand();
            Command command = commandDto.getCommand();
            checkFirstCommand(command);
            return command;
        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return getFirstCommand();
        }
    }

    private void checkFirstCommand(Command command) {
        if (command == Command.MOVE) {
            throw new IllegalArgumentException(FIRST_COMMAND_ERROR_MESSAGE.getErrorMessage());
        }
    }

    private Command inputCommand(ChessGame chessGame) {
        try {
            CommandDto commandDto = readStateCommand();
            Command command = commandDto.getCommand();

            while (isMove(command) || isStatus(command)) {

                if (isStatus(command)) {
                    printScores(chessGame.getChessBoard());
                }

                if (isMove(command)) {
                    move(chessGame, commandDto);

                    if (isKingDead(chessGame)) {
                        printCheckmateGuideMessage();
                        return Command.END;
                    }
                }

                commandDto = readStateCommand();
                command = commandDto.getCommand();
            }
            return command;

        } catch (IllegalArgumentException exception) {
            printExceptionMessage(exception);
            return inputCommand(chessGame);
        }
    }

    private void move(ChessGame chessGame, CommandDto commandDto) {
        Position start = new Position(commandDto.getColumnOfStartSource(), commandDto.getRankOfStartSource());
        Position end = new Position(commandDto.getColumnOfEndSource(), commandDto.getRankOfEndSource());


        chessGame.move(start, end);
        printChessGame(chessGame);
    }

    private boolean isKingDead(ChessGame chessGame) {
        Map<Position, Piece> chessBoard = chessGame.getChessBoardMap();
        int count = 0;
        for (Piece piece : chessBoard.values()) {
            count = piece.calculateKing(count);
        }

        return count < 2;
    }

    private void printChessGame(ChessGame chessGame) {
        BoardDto boardDto = new BoardDto(chessGame.getChessBoardMap());
        printBoard(boardDto);
    }
}
