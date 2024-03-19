package view;

import domain.PieceType;
import domain.Position;
import dto.BoardDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int BOARD_SIZE = 8;

    private final OutputConvertor outputConvertor = new OutputConvertor();

    public void printWelcomeMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(final BoardDto boardDto) {
        List<String> boardStatus = convertBoardStatus(boardDto.piecePositions());

        boardStatus.forEach(System.out::println);
    }

    private List<String> convertBoardStatus(final Map<Position, PieceType> boardStatus) {
        String[][] strings = initStringsArray();
        boardStatus.forEach(
                (position, pieceType) -> buildStrings(strings, position, pieceType)
        );
        return Arrays.stream(strings)
                .map(rowString -> String.join("", rowString))
                .toList();
    }

    private static String[][] initStringsArray() {
        String[][] strings = new String[BOARD_SIZE][BOARD_SIZE];
        for (String[] row : strings) {
            Arrays.fill(row, ".");
        }
        return strings;
    }

    private void buildStrings(final String[][] strings, final Position position, final PieceType pieceType) {
        int row = position.rowIndex();
        int col = position.columnIndex();
        strings[row][col] = outputConvertor.convertPieceTypeToString(pieceType);
    }
}
