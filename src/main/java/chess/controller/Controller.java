package chess.controller;

import chess.dao.DbChessGameDao;
import chess.domain.Position;
import chess.domain.board.strategy.InitialBoardStrategy;
import chess.domain.game.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.GameStatus;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import chess.dto.CommandDto;
import chess.view.Command;

import java.util.Map;

import static chess.domain.game.GameStatus.GAME_OVER;
import static chess.domain.game.GameStatus.IDLE;
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

    private DbChessGameDao dbChessGameDao = new DbChessGameDao();
    private Command command;

    public void playChessGame() {
        printStartGuideMessage();
        ChessGame chessGame = loadChessGame();

        //command = getFirstCommand();
        initCommend(chessGame);
        play(chessGame);
    }

    private void initCommend(ChessGame chessGame) {
        if (chessGame.getGameStatus().equals(GameStatus.PLAYING)) {
            return;
        }
        CommandDto commandDto = readStateCommand();
        Command command = commandDto.getCommand();
        chessGame.receiveCommand(command);
        dbChessGameDao.update(chessGame);
    }

    private ChessGame loadChessGame() {
        ChessGame chessGame = dbChessGameDao.select();

        if (chessGame == null) {
            chessGame = new ChessGame(new InitialBoardStrategy(), IDLE);
            dbChessGameDao.save(chessGame);
        }

        return chessGame;
    }

    private void play(ChessGame chessGame) {
        while (!isEnd(chessGame)) {
           ChessGame chessGame1 = dbChessGameDao.select();
            ChessBoard chessBoard = chessGame1.getChessBoard();
            printBoard(new BoardDto(chessBoard.getChessBoard()));
            chessGame.receiveCommand(inputCommand(chessGame));
            dbChessGameDao.update(chessGame);
        }
    }

    private boolean isEnd(ChessGame chessGame) {
        GameStatus gameStatus = chessGame.getGameStatus();
        return isKingDead(chessGame)
                || gameStatus == IDLE || gameStatus == GAME_OVER;
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
                    dbChessGameDao.update(chessGame);
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

    public Command getCommand() {
        return command;
    }
}
