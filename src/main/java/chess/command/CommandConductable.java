package chess.command;

import chess.progress.Progress;
import chess.team.Team;

@FunctionalInterface
public interface CommandConductable {
    Progress couduct(String command);
}
