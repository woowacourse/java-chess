package chess.Controller.command;

import chess.Controller.dto.PiecesDto;
import chess.domain.board.Board;

public abstract class PieceCommand {

    private static final String CANNOT_IMPLEMENT_COMMAND = "현재 실행할 수 없는 명령입니다.";

    public PiecesDto doCommandAction(final ParsedCommand parsedCommand, final Board board) {
        if (canDoAction(parsedCommand.getCommand(), board)) {
            return PiecesDto.fromEntity(doAction(parsedCommand, board));
        }
        throw new IllegalArgumentException(CANNOT_IMPLEMENT_COMMAND);
    }

    protected abstract boolean canDoAction(final Command command, final Board board);

    protected abstract Board doAction(final ParsedCommand parsedCommand, final Board board);

}
