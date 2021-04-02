package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.dto.ScoreDto;
import chess.domain.team.Team;

public interface State {

    boolean isFinished();

    boolean isMove();

    State changeCommand(CommandType command);

    void processMove(String input, Team currentTeam);

    Board getBoard();

    String getValue();

    ScoreDto judgeResult();
}
