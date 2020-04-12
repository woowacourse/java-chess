package domain.template;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class EmptyDatabaseException extends IllegalArgumentException {
	public static final String EMPTY_DATA = "데이터 베이스에 저장된 정보가 없습니다.";

	public EmptyDatabaseException(String message) {
		super(message);
	}
}
