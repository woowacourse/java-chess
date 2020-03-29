package chess.domain.piece.state;

/**
 *    초기화 상태 추상 클래스입니다.
 *
 *    @author AnHyungJu
 */
public abstract class Initialized extends Started {
	@Override
	public boolean isCaptured() {
		return false;
	}

	@Override
	public boolean isAlive() {
		return true;
	}
}
