package chess.domain.game;

import chess.machine.Command;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public ResponseDto move(final Game game, final String command) {
        if (Command.isMove(command)) {
            return getResponseDto(game, command);
        }
        return new ResponseDto(400, "잘못된 명령어 입니다.");
    }

    private ResponseDto getResponseDto(Game game, String command) {
        try {
            movePiece(game, Arrays.asList(command.split(MOVE_DELIMITER)));
        } catch (IllegalArgumentException e) {
            return new ResponseDto(400, e.getMessage());
        }
        return new ResponseDto(200, "");
    }

    private void movePiece(Game game, final List<String> commands) {
        if (commands.size() == MOVE_COMMAND_SIZE) {
            movePieceOnBoard(game, commands);
        }
    }

    private void movePieceOnBoard(Game game, final List<String> commands) {
        game.move(commands.get(SOURCE_INDEX), commands.get(TARGET_INDEX));
    }

}
