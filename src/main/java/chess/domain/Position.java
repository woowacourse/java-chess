package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
	private static final Map<String, Integer> alphabetToNumber;
	private static final String INVALID_POSITION_INPUT_EXCEPTION_MESSAGE = "옳지 않은 좌표 입력입니다.";
	private static final String POSITION_INPUT_MATCHER = "[a-h][1-8]";

	private final int x;
	private final int y;

	static{
		alphabetToNumber = new HashMap<>();
		alphabetToNumber.put("a",1);
		alphabetToNumber.put("b",2);
		alphabetToNumber.put("c",3);
		alphabetToNumber.put("d",4);
		alphabetToNumber.put("e",5);
		alphabetToNumber.put("f",6);
		alphabetToNumber.put("g",7);
		alphabetToNumber.put("h",8);
	}

	public Position(String position) {
		Objects.requireNonNull(position, INVALID_POSITION_INPUT_EXCEPTION_MESSAGE);
		if (!position.matches(POSITION_INPUT_MATCHER)) {
			throw new IllegalArgumentException(INVALID_POSITION_INPUT_EXCEPTION_MESSAGE);
		}
		this.x = alphabetToNumber.get(position.substring(0,1));
		this.y = Integer.parseInt(position.substring(1,2));
	}
}
