package chess.command;

import chess.progress.Progress;

@FunctionalInterface
public interface CommandConductable {
    Progress couduct(String command);
}
