package chess.controller.mapper.request;

import chess.domain.game.ChessCommandType;
import chess.domain.game.command.ChessGameCommand;
import chess.domain.game.command.EndCommand;
import chess.domain.game.command.MoveCommand;
import chess.domain.game.command.StartCommand;
import chess.domain.position.Position;
import java.util.List;

public class ChessGameCommandMapper {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    public static ChessGameCommand convertToChessGameCommand(List<String> commandInputs) {
        String commandInput = commandInputs.get(COMMAND_INDEX);
        ChessCommandType commandType = ChessCommandTypeMapper.toChessCommandType(commandInput);

        if (commandType == ChessCommandType.START) {
            return new StartCommand();
        }

        if (commandType == ChessCommandType.END) {
            return new EndCommand();
        }

        return getMoveCommand(commandInputs);
    }

    private static MoveCommand getMoveCommand(List<String> commandInputs) {
        if (commandInputs.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("이동 명령은 명령어를 포함하여 시작점과 도착점이 존재해야 합니다. 예. move b2 b3.");
        }

        String fromInput = commandInputs.get(FROM_POSITION_INDEX);
        String toInput = commandInputs.get(TO_POSITION_INDEX);

        return new MoveCommand(toPosition(fromInput), toPosition(toInput));
    }

    private static Position toPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(FileMapper.toFile(fileInput), RankMapper.toRank(rankInput));
    }
}
