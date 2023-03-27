package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class StartCommand extends Command {

    public StartCommand(BoardDao boardDao) {
        super(boardDao, CommandType.START, new OutputView());
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType != CommandType.START) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
        return new MoveCommand(boardDao, outputView);
    }
}
