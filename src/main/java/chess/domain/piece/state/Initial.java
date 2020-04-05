package chess.domain.piece.state;

/**
 *    초기 상태를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class Initial extends Initialized {
	@Override
	public boolean isInitial() {
		return true;
	}

	@Override
	public String toString() {
		return "Initial";
	}
}
