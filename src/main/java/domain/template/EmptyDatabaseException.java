package domain.template;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class EmptyDatabaseException extends IllegalArgumentException {
	public static final String EMPTY_BOARD = "데이터 베이스에 저장된 Board가 없습니다.";
	public static final String EMPTY_TURN = "데이터 베이스에 저장된 Turn이 없습니다.";

	public EmptyDatabaseException(String message) {
		super(message);
	}
}
