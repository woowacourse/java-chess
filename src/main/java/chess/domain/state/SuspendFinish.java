package chess.domain.state;

import static chess.domain.piece.Team.*;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public class SuspendFinish extends Finish {
	public SuspendFinish(Board board, Team turn) {
		super(board, turn);
	}

	@Override
	public Team getWinner() {
		// TODO: 2020/04/03  status() 메서드에서 점수 더 큰 친구 찾자. 이건 객체 포장해서 리팩토링  하자
		Map<Team, Double> status = status();
		if (status.size() == 0) {
			return NONE;
		}
		return status.get(BLACK) < status.get(WHITE) ? WHITE :
			!status.get(BLACK).equals(status.get(WHITE)) ? BLACK : NONE;
	}
}
