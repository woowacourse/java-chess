package controller;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.Score;
import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.PositionFactory;
import domain.piece.Color;
import jdbc.ChessGameDao;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.List;

public class ChessGameController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int SELECT_ID_COMMAND_INDEX = 1;
    private static final String newGame = "new";

    private final ChessGameDao chessGameDao;
    private String id;
    private boolean isKeepGaming;

    public ChessGameController() {
        chessGameDao = new ChessGameDao();
    }

    public void run() {
        OutputView.printStartMessage(chessGameDao.findAllId());

        isKeepGaming = true;

        while (isKeepGaming) {
            progress();
        }

        chessGameDao.delete(id);
    }

    public void progress() {
        try {
            playTurn();
            OutputView.printChessBoard(chessGameDao.select(id).getChessBoard());
        } catch (IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

    private void playTurn() {
        List<String> inputs = InputView.readline();
        Command command = Command.from(inputs.get(COMMAND_INDEX));
        isStart(inputs, command);
        isMove(inputs, command);
        isEnd(command);
        isStatus(command);
    }

    private void isStart(List<String> inputs, final Command command) {
        if (command == Command.START) {
            String select = inputs.get(SELECT_ID_COMMAND_INDEX);
            setChessGameId(select);
        }
    }

    private void setChessGameId(String select) {
        if (select.equals(newGame)) {
            id = chessGameDao.save(new ChessGame(ChessBoard.generate()));
            return;
        }

        id = select;
    }

    private void isMove(final List<String> inputs, final Command command) {
        if (command == Command.MOVE) {
            Position source = PositionFactory.createPosition(inputs.get(SOURCE_INDEX));
            Position target = PositionFactory.createPosition(inputs.get(TARGET_INDEX));
            ChessGame chessGame = chessGameDao.select(id);
            chessGame.move(MovePosition.of(source, target));
            chessGameDao.update(id, chessGame);
            exitIfKingDead(chessGame);
        }
    }

    private void exitIfKingDead(ChessGame chessGame) {
        if (chessGame.isCheckMate()) {
            Result result = Result.createByKingDead(
                    chessGame.getExistKingThisColor(Color.BLACK),
                    chessGame.getExistKingThisColor(Color.WHITE)
            );
            OutputView.printResult(result);
            isKeepGaming = false;
        }
    }

    private void isStatus(final Command command) {
        if (command == Command.STATUS) {
            ChessGame chessGame = chessGameDao.select(id);
            Score score = Score.of(
                    chessGame.getScoreThisColor(Color.BLACK),
                    chessGame.getScoreThisColor(Color.WHITE)
            );
            StatusResult statusResult = StatusResult.of(score);
            OutputView.printStatusResult(statusResult);
            isKeepGaming = false;
        }
    }

    private void isEnd(final Command command) {
        if (command == Command.END) {
            isKeepGaming = false;
        }
    }

}
