package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.PositionMapper;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class MoveCommand extends Command {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    protected MoveCommand(ChessGame chessGame) {
        super(chessGame, CommandType.MOVE);
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

    private MoveCommand executeMove(List<String> input) {
        chessGame.movePiece(PositionMapper.from(input.get(SOURCE_POSITION_INDEX)),
                PositionMapper.from(input.get(TARGET_POSITION_INDEX)));
        return new MoveCommand(new ChessGame(new Board(getChessGameBoards())));
    }

    private EndCommand executeEnd() {
        return new EndCommand(new ChessGame(new Board(getChessGameBoards())));
    }
}
