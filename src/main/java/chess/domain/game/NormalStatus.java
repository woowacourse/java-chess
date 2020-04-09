package chess.domain.game;

public enum NormalStatus {
	YES(true),
	NO(false);

	private boolean normalStatus;

	NormalStatus(boolean normalStatus) {
		this.normalStatus = normalStatus;
	}

	public boolean isNormalStatus() {
		return normalStatus;
	}
}
