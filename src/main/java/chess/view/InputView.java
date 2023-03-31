package chess.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import chess.controller.dto.CommandDto;

public class InputView {

	private static final String INPUT_INVALID_ERROR_MESSAGE = "불가능한 입력입니다.";
	private static final String BLANK_POSITION_INVALID_ERROR_MESSAGE = "위치를 추가로 입력해야 합니다.";
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer buffer;


	public static CommandDto readCommand() {
		try {
			buffer = new StringTokenizer(br.readLine());
			return CommandDto.from(buffer.nextToken());
		} catch (Exception e) {
			throw new IllegalArgumentException(INPUT_INVALID_ERROR_MESSAGE + "\n");
		}
	}

	public static String readPosition() {
		if (!buffer.hasMoreElements()) {
			throw new IllegalArgumentException(BLANK_POSITION_INVALID_ERROR_MESSAGE + "\n");
		}
		return buffer.nextToken();
	}

	public static List<String> readNames() {
		System.out.println("두 참가자의 이름을 쉼표로 구분해 입력해주세요");
		String line = readNameLine();
		return parseNames(line);
	}

	private static String readNameLine() {
		String line;
		try {
			line = br.readLine();
		} catch (IOException e) {
			throw new IllegalArgumentException("참가자의 이름이 정상적으로 입력되지 않았습니다.");
		}
		return line;
	}

	private static List<String> parseNames(final String line) {
		StringTokenizer st = new StringTokenizer(line, ",");
		List<String> names = new ArrayList<>();
		while (st.hasMoreTokens()) {
			names.add(st.nextToken().trim());
		}
		if (names.size() != 2) {
			throw new IllegalArgumentException("참가자의 이름이 정상적으로 입력되지 않았습니다.");
		}
		return names;
	}
}
