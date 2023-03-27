package chess.controller;

import java.util.List;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.dao.ChessGameDao;
import chess.dto.ChessBoardDto;
import chess.game.ChessGame;
import chess.piece.Team;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;

public class ChessController {

    private final ChessGameDao chessGameDao;

    public ChessController(final ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        OutputView.printInitGame();
        playGame();
    }

    private void playGame() {
        final StartCommand startCommand = readStartCommand();
        if (startCommand.getStartCommandType() == StartCommandType.END) {
            return;
        }
        if (startCommand.getStartCommandType() == StartCommandType.START) {
            startGame();
        }
    }

    private static StartCommand readStartCommand() {
        try {
            final List<String> commands = InputView.readGameCommand();
            return StartCommand.parse(commands);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return readStartCommand();
        }
    }

    private void startGame() {
        final ChessGame chessGame = makeGame();

        while (chessGame.isProcessing()) {
            processGame(chessGame);
        }
        OutputView.printWinner(chessGame.findWinner());
    }

    private ChessGame makeGame() {
        ChessGame chessGame = chessGameDao.select();
        if (chessGame == null) {
            chessGame = new ChessGame(new ChessBoard());
            chessGameDao.save(chessGame);
        }
        return chessGame;
    }

    private void processGame(final ChessGame chessGame) {
        try {
            renderChessBoard();
            final RunningCommand runningCommand = readMoveCommand();
            if (runningCommand.getRunningCommandType() == RunningCommandType.STATUS) {
                OutputView.printScore(chessGame.calculateScore(Team.WHITE), chessGame.calculateScore(Team.BLACK));
                OutputView.printWinner(chessGame.findWinner());
            }
            if (runningCommand.getRunningCommandType() == RunningCommandType.MOVE) {
                move(runningCommand.getParameters());
            }
            if (runningCommand.getRunningCommandType() == RunningCommandType.END) {
                chessGame.end();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void renderChessBoard() {
        final ChessGame chessGame = chessGameDao.select();
        final ChessBoard chessBoard = chessGame.getChessBoard();
        final ChessBoardDto chessBoardDto = ChessBoardDto.toView(chessBoard);
        OutputView.printChessBoard(chessBoardDto);
    }

    private RunningCommand readMoveCommand() {
        final List<String> commands = InputView.readGameCommand();
        return RunningCommand.parse(commands);
    }

    private void move(final List<String> movePositions) {
        final ChessGame chessGame = chessGameDao.select();
        final Position from = PositionConvertor.convert(movePositions.get(0));
        final Position to = PositionConvertor.convert(movePositions.get(1));
        chessGame.movePiece(from, to);
        chessGameDao.update(chessGame);
    }
}
