package chess.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputView {

	private static final String BLANK_INPUT_INVALID_ERROR_MESSAGE = "공백을 입력할 수 없습니다.";
	private static final String BLANK_POSITION_INVALID_ERROR_MESSAGE = "위치를 추가로 입력해야 합니다.";
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer buffer;


	public static String readCommand() {
		try {
			buffer = new StringTokenizer(br.readLine());
			return buffer.nextToken();
		} catch (Exception e) {
			throw new IllegalArgumentException(BLANK_INPUT_INVALID_ERROR_MESSAGE + "\n");
		}
	}

	public static String readPosition() {
		if (!buffer.hasMoreElements()) {
			throw new IllegalArgumentException(BLANK_POSITION_INVALID_ERROR_MESSAGE + "\n");
		}
		return buffer.nextToken();
	}
}
