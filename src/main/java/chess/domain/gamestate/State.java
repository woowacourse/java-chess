package chess.domain.gamestate;

import chess.domain.dto.ResponseDto;
import chess.domain.team.Team;

public interface State {

    boolean isFinished();

    boolean isMove();

    State changeCommand(CommandType command);

    ResponseDto getProcessResult();
}
