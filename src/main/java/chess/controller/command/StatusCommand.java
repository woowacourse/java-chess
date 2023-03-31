package chess.controller.command;

import chess.controller.mapper.PositionMapper;
import chess.domain.ChessGame;
import chess.domain.Result;
import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;


public class StatusCommand extends Command {

    private static final String INVALID_EXECUTE_MESSAGE = "게임이 종료되어 상태를 확인하시려면 status를, 게임을 종료하려면 end를 입력해주세요.";
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final Result result;

    protected StatusCommand(BoardDao boardDao, OutputView outputView) {
        super(boardDao, CommandType.STATUS, outputView);
        result = boardDao.selectChessGame().calculateResult();
        outputView.printResult(result);
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.STATUS) {
            return executeStatus();
        }
        if (inputCommandType == CommandType.END) {
            return executeEnd();
        }
        if (inputCommandType == CommandType.MOVE) {
            return excecuteMove(input);
        }
        throw new IllegalArgumentException(INVALID_EXECUTE_MESSAGE);
    }

    private Command excecuteMove(List<String> input) {
        ChessGame chessGame = boardDao.selectChessGame();
        if (chessGame.isFinished()) {
            outputView.printError(INVALID_COMMAND_MESSAGE);
            return new StatusCommand(boardDao, outputView);
        }
        moveChessPiece(input, chessGame);
        if (chessGame.isFinished()) {
            outputView.printBoard(getChessGameBoards());
            outputView.printFinish();
            return new StatusCommand(boardDao, outputView);
        }
        return new MoveCommand(boardDao, outputView);
    }

    private void moveChessPiece(List<String> input, ChessGame chessGame) {
        chessGame.movePiece(PositionMapper.from(input.get(SOURCE_POSITION_INDEX)),
                PositionMapper.from(input.get(TARGET_POSITION_INDEX)));
        deleteAndSave(chessGame);
    }

    private synchronized void deleteAndSave(ChessGame chessGame) {
        boardDao.deleteAll();
        boardDao.saveChessGame(chessGame);
    }

    private Command executeStatus() {
        return new StatusCommand(boardDao, outputView);
    }

    private Command executeEnd() {
        boardDao.deleteAll();
        return new EndCommand(boardDao, outputView);
    }
}
