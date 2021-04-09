package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.dto.ScoreDto;
import chess.domain.gamestate.finished.End;
import chess.domain.gamestate.running.Move;
import chess.domain.gamestate.running.Ready;
import chess.domain.gamestate.running.Start;
import chess.domain.gamestate.running.Status;
import chess.domain.team.Team;

public interface State {

    boolean isFinished();

    boolean isMove();

    State changeCommand(CommandType command);

    void processMove(String input, Team currentTeam);

    Board getBoard();

    String getValue();

    ScoreDto judgeResult();

    boolean isAnyKingDead();

    static State generateState(String stateSignature, Board board) {
        if (stateSignature.equals("ready")) {
            return new Ready(board);
        }
        if (stateSignature.equals("start")) {
            return new Start(board);
        }
        if (stateSignature.equals("move")) {
            return new Move(board);
        }
        if (stateSignature.equals("end")) {
            return new End(board);
        }
        if (stateSignature.equals("status")) {
            return new Status(board);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 명령입니다.");
    }
}
