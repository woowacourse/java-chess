package chess.controller.command;

import chess.controller.mapper.PositionMapper;
import chess.domain.ChessGame;
import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class MoveCommand extends Command {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    protected MoveCommand(BoardDao boardDao, OutputView outputView) {
        super(boardDao, CommandType.MOVE, outputView);
        outputView.printBoard(getChessGameBoards());
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.MOVE) {
            return executeMove(input);
        }
        if (inputCommandType == CommandType.END) {
            return executeEnd();
        }
        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    private Command executeMove(List<String> input) {
        ChessGame chessGame = boardDao.selectChessGame();
        if (chessGame.isFinished()) {
            outputView.printError(INVALID_COMMAND_MESSAGE);
            return new StatusCommand(boardDao, outputView);
        }
        moveChessPiece(input, chessGame);
        if (chessGame.isFinished()) {
            outputView.printBoard(getChessGameBoards());
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

    private Command executeEnd() {
        return new EndCommand(boardDao, outputView);
    }
}
