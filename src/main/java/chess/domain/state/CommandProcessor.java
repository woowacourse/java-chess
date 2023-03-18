package chess.domain.state;

import chess.dto.ChessInputDto;
import chess.view.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandProcessor {
    private Map<Command, Function<ChessInputDto, State>> commands;

    private CommandProcessor(final Map<Command, Function<ChessInputDto, State>> commands) {
        this.commands = commands;
    }

    public static CommandProcessor create() {
        return new CommandProcessor(new HashMap<>());
    }

    public void register(Command command, Function<ChessInputDto, State> consumer) {
        commands.put(command, consumer);
    }

    public void execute(StateProcessor stateProcessor, ChessInputDto chessInputDto) {
        stateProcessor.changeState(commands.get(chessInputDto.getGameState())
                .apply(chessInputDto));
    }
}
