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
                > 게임 저장: save
                > 게임 로드: load
                > 게임 이동: move source위치 target위치 - 예. move b2 b3"""
        );
    }

    public void printBoard(final BoardDto boardDto) {
        List<String> boardStatus = convertBoardStatus(boardDto.piecePositions());
        StringBuilder sb = new StringBuilder();

        sb.append(NEW_LINE).append(pad(FILE_DESCRIPTION, FILE_DESCRIPTION_PAD_SIZE)).append(NEW_LINE);
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

    public void printCurrentTurn(TeamColor teamColor) {
        System.out.printf("(%s) 차례 >> ", getTeamName(teamColor));
    }

    public void printWinner(TeamColor winner) {
        System.out.printf("우승자는 %s 입니다.%n", getTeamName(winner));
    }

    public void printScore(TeamColor teamColor, double score) {
        System.out.printf("%s 의 점수: %.1f%n", getTeamName(teamColor), score);
    }

    private String getTeamName(TeamColor teamColor) {
        if (!TEAM_NAME.containsKey(teamColor)) {
            throw new IllegalArgumentException("팀 정보가 존재하지 않습니다.");
        }
        return TEAM_NAME.get(teamColor);
    }

    public void printRestartMessage() {
        System.out.println("다시 시작하려면 start 를, 다른 게임을 불러오려면 load 를 입력하세요.");
    }

    public void printErrorMessage(String message) {
        System.out.println("[오류] " + message);
    }

    public void printStatusInputMessage() {
        System.out.println("결과를 확인하려면 status 를 입력하세요.");
    }

    public void printGameEndMessage() {
        System.out.println("게임이 종료되었습니다.");
    }

    public void printSaveResult(int gameId) {
        System.out.println("게임 진행 상황이 저장되었습니다. ID: " + gameId);
    }
}
