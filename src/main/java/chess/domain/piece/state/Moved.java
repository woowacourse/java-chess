package chess.domain.piece.state;

/**
 *    움직인 상태를 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Moved extends Initialized {
	@Override
	public boolean isInitial() {
		return false;
	}

	@Override
	public String toString() {
		return "Moved";
	}
}
