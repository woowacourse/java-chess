package chess.domain.gamestate;

import chess.domain.dto.ResponseDto;
import chess.domain.team.Team;

public interface State {

    boolean isFinished();

    boolean isMove();

    void processCommand(Team currentTurn);

    State changeCommand(CommandType command, String commandInput);

    ResponseDto getProcessResult();
}
