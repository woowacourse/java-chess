package chess.view;

import chess.constant.ExceptionCode;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.dto.controllertoview.PieceInfo;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class OutputView {

    private static final String EXCEPTION_MESSAGE_SIGN = "[ERROR] ";
    private static final String EMPTY_SQUARE = ".";
    private static final String NEW_LINE = System.lineSeparator();
    private static final Map<String, String> UPPER_SIGN_BY_PIECE;
    private static final Map<File, Integer> COLUMN_BY_FILE;
    private static final Map<Rank, Integer> ROW_BY_RANK;
    private static final Map<Color, String> TEAM_COLOR_NAME;
    private static final Map<ExceptionCode, String> EXCEPTION_MESSAGES;

    static {
        UPPER_SIGN_BY_PIECE = Map.of(
                "ROOK", "R",
                "KNIGHT", "N",
                "BISHOP", "B",
                "QUEEN", "Q",
                "KING", "K",
                "PAWN", "P"
        );
        COLUMN_BY_FILE = Map.of(
                File.A, 0,
                File.B, 1,
                File.C, 2,
                File.D, 3,
                File.E, 4,
                File.F, 5,
                File.G, 6,
                File.H, 7
        );
        ROW_BY_RANK = Map.of(
                Rank.ONE, 7,
                Rank.TWO, 6,
                Rank.THREE, 5,
                Rank.FOUR, 4,
                Rank.FIVE, 3,
                Rank.SIX, 2,
                Rank.SEVEN, 1,
                Rank.EIGHT, 0
        );
        TEAM_COLOR_NAME = Map.of(
                Color.BLACK, "검은색",
                Color.WHITE, "흰색"
        );

        EXCEPTION_MESSAGES = Map.ofEntries(
                entry(ExceptionCode.INVALID_COMMAND_PARAMETER, "커멘드에 맞는 파라미터를 입력해주세요."),
                entry(ExceptionCode.UNDEFINED_COMMAND_TYPE, "잘못된 명령어 입니다."),
                entry(ExceptionCode.PIECE_CAN_NOT_FOUND, "해당 위치에 말이 존재하지 않습니다."),
                entry(ExceptionCode.PIECE_MOVING_PATH_BLOCKED, "이동 경로에 다른 말이 있습니다."),
                entry(ExceptionCode.ACCESS_BLANK_PIECE, "유효하지 않은 체스말 사용입니다."),
                entry(ExceptionCode.INVALID_DESTINATION, "해당 위치로 이동할 수 없습니다."),
                entry(ExceptionCode.TARGET_IS_SAME_COLOR, "같은 색 말은 잡을 수 없습니다."),
                entry(ExceptionCode.GAME_END, "체스가 이미 종료되었습니다."),
                entry(ExceptionCode.GAME_NOT_INITIALIZED, "아직 게임이 생성되지 않았습니다."),
                entry(ExceptionCode.INVALID_COMMAND, "유효하지 않은 커멘드가 입력되었습니다."),
                entry(ExceptionCode.GAME_ALREADY_RUNNING, "게임이 이미 진행중입니다."),
                entry(ExceptionCode.INVALID_TURN, "해당 색의 말을 이동시킬 순서가 아닙니다."),
                entry(ExceptionCode.GAME_OVER_STATE, "승자가 나와 더이상 플레이할 수 없습니다. 게임 재시작 또는 종료를 해주세요.")
        );
    }

    public void printGameStartGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 상태 확인 : status (게임 진행 중 점수 확인, 게임 오버시 승자 확인)");
    }

    public void printBoard(final List<PieceInfo> pieceInfos) {
        final String boardMessage = generateBoardMessage(pieceInfos);

        System.out.println(boardMessage);
    }

    private String generateBoardMessage(final List<PieceInfo> pieceInfos) {
        final StringBuilder mapBuilder = generateEmptyBoardBuilder();

        for (PieceInfo pieceInfo : pieceInfos) {
            setUpPieceInBoard(mapBuilder, pieceInfo);
        }

        return mapBuilder.toString();
    }

    private static StringBuilder generateEmptyBoardBuilder() {
        final StringBuilder mapBuilder = new StringBuilder();
        for (int index = 0; index < ROW_BY_RANK.size(); index++) {
            mapBuilder.append(EMPTY_SQUARE.repeat(COLUMN_BY_FILE.size()))
                    .append(NEW_LINE);
        }
        return mapBuilder;
    }

    private void setUpPieceInBoard(final StringBuilder mapBuilder, final PieceInfo pieceInfo) {
        final int positionIndex = calculateIndex(pieceInfo.getPositionFile(), pieceInfo.getPositionRank());
        mapBuilder.replace(positionIndex, positionIndex + 1, getPieceAccordingToSign(pieceInfo));
    }

    private int calculateIndex(final File positionFile, final Rank positionRank) {
        final int oneLineLength = COLUMN_BY_FILE.size() + NEW_LINE.length();
        return oneLineLength * ROW_BY_RANK.get(positionRank) + COLUMN_BY_FILE.get(positionFile);
    }

    private static String getPieceAccordingToSign(final PieceInfo pieceInfo) {
        final String upperPieceMessage = UPPER_SIGN_BY_PIECE.get(pieceInfo.getPieceType());
        if (pieceInfo.getColor().equals(Color.BLACK)) {
            return upperPieceMessage;
        }
        return upperPieceMessage.toLowerCase();
    }

    public void printScores(final Map<Color, Double> scores) {
        final String SCORE_FORMAT = "%s : %.1f";
        System.out.println("팀색별 점수");
        for (Map.Entry entry : scores.entrySet()) {
            System.out.println(String.format(SCORE_FORMAT, TEAM_COLOR_NAME.get(entry.getKey()), entry.getValue()));
        }
    }

    public void printWinner(final Color winningTeamColor) {
        System.out.println(String.format("승리팀색 : %s", TEAM_COLOR_NAME.get(winningTeamColor)));
    }

    public void printErrorMessage(final RuntimeException exception) {
        try {
            final String exceptionCodeName = exception.getMessage();
            final ExceptionCode code = ExceptionCode.valueOf(exceptionCodeName);
            System.out.println(EXCEPTION_MESSAGE_SIGN + EXCEPTION_MESSAGES.getOrDefault(code, exceptionCodeName));
        } catch (RuntimeException e) {
            System.out.println(EXCEPTION_MESSAGE_SIGN + exception.getMessage());
        }
    }
}
