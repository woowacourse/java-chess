package chess.domain.piece.state;

/**
 *    잡힌 상태를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Captured extends Started {
	@Override
	public boolean isInitial() {
		return false;
	}

	@Override
	public boolean isCaptured() {
		return true;
	}

	@Override
	public boolean isAlive() {
		return false;
	}
}
