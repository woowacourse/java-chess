package chess.domain.piece.state;

/**
 *    class description
 *
 *    @author AnHyungJu, LeeHoBin
 */
public interface State {
	boolean isInitial();

	boolean isAlive();

	boolean isCaptured();
}
