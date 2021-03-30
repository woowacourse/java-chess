package chess.domain.gamestate;

import chess.domain.team.Team;

public interface State {

    boolean isFinished();

    boolean isMove();

    State changeCommand(CommandType command);

    void processMove(String input, Team currentTeam);

    String getValue();
}
