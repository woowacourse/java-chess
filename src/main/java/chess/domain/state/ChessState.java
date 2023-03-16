package chess.domain.state;

import java.util.List;

public interface ChessState {

    ChessState command(final Command command);

    boolean runnable();

    class Command {
        private final List<String> commands;

        public Command(final List<String> commands) {
            this.commands = commands;
        }

        public String getCommand() {
            return commands.get(0);
        }

        public List<String> getParameters() {
            return commands.subList(1, commands.size());
        }
    }
}
