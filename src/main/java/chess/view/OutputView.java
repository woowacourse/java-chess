package chess.view;

import chess.domain.game.GameResult;
import chess.domain.game.Turn;
import chess.domain.pieces.piece.Color;
import chess.dto.PieceResponse;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class OutputView {
    private static final String WELCOME_MESSAGE = "> 우아한 체스 게임에 오신 것을 환영합니다.";
    private static final String SIGNUP_MESSAGE = "> 회원 가입: signup 이름 - 예. signup 초코칩";
    private static final String LOGIN_MESSAGE = "> 로그인: login 이름 - 예. login 초코칩";
    private static final String USER_STATUS = "> 로그인 가능한 사용자 : %s";
    private static final String ROOM_CREATE_MESSAGE = "> 방 만들기: create 이름 - 예. create chess";
    private static final String ROOM_ENTER_MESSAGE = "> 방 만들기: enter 이름 - 예. enter chess";
    private static final String ROOM_STATUS = "> 입장 가능한 방 : %s";
    private static final String NO_NAME = "없음";
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_INFO_MESSAGE = "> 게임 시작 : start";
    private static final String END_INFO_MESSAGE = "> 게임 종료 : end";
    private static final String STATUS_INFO_MESSAGE = "> 점수 보기 : status";
    private static final String MOVE_INFO_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String TURN_INFO_MESSAGE = "> 현재 %s 턴입니다.";
    private static final String GAME_FINISH_MESSAGE = "> 게임이 종료되었습니다.";
    private static final String GAME_ALREADY_OVER = "> 이미 게임이 종료되었습니다.";
    private static final char EMPTY_SQUARE = '.';
    private static final String SCORE_STATUS_FORMAT = "%s: %.1f";
    private static final int BOARD_SIZE = 8;
    private static final String NAME_DELIMITER = ", ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printGameStartMessage() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(GAME_START_MESSAGE);
        stringJoiner.add(START_INFO_MESSAGE);
        stringJoiner.add(END_INFO_MESSAGE);
        stringJoiner.add(STATUS_INFO_MESSAGE);
        stringJoiner.add(MOVE_INFO_MESSAGE);
        System.out.println(System.lineSeparator() + stringJoiner);
    }

    public void printBoard(final List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoardStatus(board);
    }

    private char[][] setUpBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : board) {
            Arrays.fill(line, EMPTY_SQUARE);
        }
        return board;
    }

    private void addPieceToBoard(final List<PieceResponse> pieces, final char[][] board) {
        for (PieceResponse piece : pieces) {
            int y = piece.rankIndex();
            int x = piece.fileIndex();
            board[y][x] = getPieceDisplay(piece.type(), piece.color());
        }
    }

    private char getPieceDisplay(final String type, final String color) {
        return PieceView.valueOf(type).changeToView(color);
    }

    private void printBoardStatus(final char[][] board) {
        IntStream.range(0, board.length).mapToObj(lineCount -> board[board.length - 1 - lineCount]).map(String::new)
                .forEach(System.out::println);
    }

    public void printStatus(final GameResult gameResult) {
        printScoreStatus(gameResult, Color.WHITE);
        printScoreStatus(gameResult, Color.BLACK);
    }

    private void printScoreStatus(final GameResult gameResult, final Color color) {
        System.out.printf(SCORE_STATUS_FORMAT + System.lineSeparator(), color.name(),
                gameResult.calculateScore(color).getValue());
    }

    public void printUserEntranceMessage() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(WELCOME_MESSAGE);
        stringJoiner.add(SIGNUP_MESSAGE);
        stringJoiner.add(LOGIN_MESSAGE);
        System.out.println(System.lineSeparator() + stringJoiner);
    }

    public void printUserStatus(final List<String> names) {
        StringJoiner stringJoiner = new StringJoiner(NAME_DELIMITER);
        names.forEach(stringJoiner::add);
        if (stringJoiner.length() == 0) {
            System.out.printf(USER_STATUS + System.lineSeparator(), NO_NAME);
            return;
        }
        System.out.printf(USER_STATUS + System.lineSeparator(), stringJoiner);
    }

    public void printRoomEntranceMessage() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(ROOM_CREATE_MESSAGE);
        stringJoiner.add(ROOM_ENTER_MESSAGE);
        System.out.println(System.lineSeparator() + stringJoiner);
    }

    public void printRoomNames(final List<String> names) {
        StringJoiner stringJoiner = new StringJoiner(NAME_DELIMITER);
        names.forEach(stringJoiner::add);
        if (stringJoiner.length() == 0) {
            System.out.printf(ROOM_STATUS + System.lineSeparator(), NO_NAME);
            return;
        }
        System.out.printf(ROOM_STATUS + System.lineSeparator(), stringJoiner);
    }

    public void printGameFinish() {
        System.out.println(GAME_FINISH_MESSAGE);
    }

    public void printAlreadyOver() {
        System.out.println(GAME_ALREADY_OVER);
    }

    public void printTurn(final Turn turn) {
        System.out.printf(TURN_INFO_MESSAGE + System.lineSeparator(), turn.getColor());
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
