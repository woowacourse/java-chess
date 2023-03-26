package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class StartCommand extends Command{

    private static final String INVALID_ACCESS_CHESS_BOARD_MESSAGE = "게임을 시작해야 체스판을 확인할 수 있습니다.";

    public StartCommand() {
        super(null, CommandType.START);
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType != CommandType.START) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
        return new MoveCommand(new ChessGame(BoardFactory.createBoard()));
    }

    @Override
    public Map<Position, Piece> getChessGameBoards() {
        throw new IllegalArgumentException(INVALID_ACCESS_CHESS_BOARD_MESSAGE);
    }
}
