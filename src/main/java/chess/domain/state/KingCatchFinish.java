package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public class KingCatchFinish extends Finish {
	public KingCatchFinish(Board board, Team turn) {
		super(board, turn);
		validCountOfKing(board);
	}

	private void validCountOfKing(Board board) {
		if (board.containsNotSingleKingWith(turn)) {
			throw new IllegalArgumentException("체스 보드 위에 킹은 하나만 있어야 합니다.");
		}
	}

	//움직이고 왕이 잡혔는지 확인 한 뒤, 잡혔으면 턴 교체 없이 바로 끝 !
	@Override
	public Team getWinner() {
		return turn;
	}
}
