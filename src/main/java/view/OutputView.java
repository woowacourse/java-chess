package view;

import domain.game.PieceType;
import domain.game.TeamColor;
import domain.position.Position;
import dto.BoardDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final String FILE_DESCRIPTION = "A  B  C  D  E  F  G  H";
    private static final int FILE_DESCRIPTION_PAD_SIZE = 4;
    private static final String FILE_DESCRIPTION_SEPARATOR = "-".repeat(FILE_DESCRIPTION.length());
    private static final String RANK_DESCRIPTION_SEPARATOR = "|";
    private static final String PAD_CHAR = " ";
    private static final int PIECE_CHAR_PAD_SIZE = 1;
    private static final String EMPTY_PIECE = ".";
    private static final Map<TeamColor, String> TEAM_NAME= Map.of(
            TeamColor.WHITE, "흰색 팀",
            TeamColor.BLACK, "검은색 팀"
    );
    private static final String NEW_LINE = System.lineSeparator();

    public void printWelcomeMessage() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작: start
                > 게임 종료: end
                > 게임 이동: move source위치 target위치 - 예. move b2 b3"""
        );
    }

    public void printBoard(final BoardDto boardDto) {
        List<String> boardStatus = convertBoardStatus(boardDto.piecePositions());
        StringBuilder sb = new StringBuilder();

        sb.append(pad(FILE_DESCRIPTION, FILE_DESCRIPTION_PAD_SIZE)).append(NEW_LINE);
        sb.append(pad(FILE_DESCRIPTION_SEPARATOR, FILE_DESCRIPTION_PAD_SIZE)).append(NEW_LINE);
        for (int rank = boardStatus.size(); rank > 0; rank--) {
            sb.append(rank).append(PAD_CHAR).append(RANK_DESCRIPTION_SEPARATOR)
                    .append(boardStatus.get(boardStatus.size() - rank))
                    .append(RANK_DESCRIPTION_SEPARATOR).append(PAD_CHAR).append(rank)
                    .append(NEW_LINE);
        }
        sb.append(pad(FILE_DESCRIPTION_SEPARATOR, FILE_DESCRIPTION_PAD_SIZE)).append(NEW_LINE);
        sb.append(pad(FILE_DESCRIPTION, FILE_DESCRIPTION_PAD_SIZE));
        System.out.println(sb);
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

    private String[][] initStringsArray() {
        String[][] strings = new String[BOARD_SIZE][BOARD_SIZE];
        for (String[] row : strings) {
            Arrays.fill(row, pad(EMPTY_PIECE, PIECE_CHAR_PAD_SIZE));
        }
        return strings;
    }

    private void buildStrings(final String[][] strings, final Position position, final PieceType pieceType) {
        int row = position.rowIndex();
        int col = position.columnIndex();
        strings[row][col] = pad(OutputConvertor.convertPieceTypeToString(pieceType), PIECE_CHAR_PAD_SIZE);
    }

    private String pad(String origin, int padSize) {
        return PAD_CHAR.repeat(padSize) + origin + PAD_CHAR.repeat(padSize);
    }

    public void printWinner(TeamColor winner) {
        System.out.printf("우승자는 %s 입니다.%n", getTeamName(winner));
    }

    private String getTeamName(TeamColor teamColor) {
        if (!TEAM_NAME.containsKey(teamColor)) {
            throw new IllegalArgumentException("팀 정보가 존재하지 않습니다.");
        }
        return TEAM_NAME.get(teamColor);
    }
}
