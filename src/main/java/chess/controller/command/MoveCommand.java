package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.OutputView;
import chess.controller.mapper.PositionMapper;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class MoveCommand extends Command {

    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    protected MoveCommand(ChessGame chessGame, OutputView outputView) {
        super(chessGame, CommandType.MOVE, outputView);
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
        chessGame.movePiece(PositionMapper.from(input.get(SOURCE_POSITION_INDEX)),
                PositionMapper.from(input.get(TARGET_POSITION_INDEX)));

        if (chessGame.isFinished()) {
            outputView.printBoard(getChessGameBoards());
            return new StatusCommand(new ChessGame(new Board(getChessGameBoards()), chessGame.getNowPlayingTeam()), outputView);
        }
        return new MoveCommand(new ChessGame(new Board(getChessGameBoards()), chessGame.getNowPlayingTeam()), outputView);
    }

    private Command executeEnd() {
        return new EndCommand(new ChessGame(new Board(getChessGameBoards()), chessGame.getNowPlayingTeam()), outputView);
    }
}
