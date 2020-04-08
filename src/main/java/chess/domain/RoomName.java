package chess.domain;

public class RoomName {
	private static final int MAX_LENGTH = 10;

	private String name;

	public RoomName(String name) {
		validateLength(name);
		this.name = name;
	}

	private void validateLength(String roomName) {
		if (roomName.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("방은 최대 10글자까지 가능합니다.");
		}
	}

	public String getName() {
		return name;
	}
}
