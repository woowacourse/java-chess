package chess.domain.moverule;

public class Empty extends AbstractMoveRule {
	private static Empty INSTANCE = new Empty();
	private static final double SCORE = 0;
	public static final String NAME = "EMPTY";

	private Empty() {
		super(SCORE, NAME);
	}

	public static Empty getInstance() {
		return INSTANCE;
	}
}

