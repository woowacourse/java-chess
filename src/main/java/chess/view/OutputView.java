package chess.view;

import chess.domain.game.GameResult;
import chess.domain.pieces.piece.Color;
import chess.dto.PieceResponse;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_INFO_MESSAGE = "> 게임 시작 : start";
    private static final String END_INFO_MESSAGE = "> 게임 종료 : end";
    private static final String STATUS_INFO_MESSAGE = "> 점수 보기 : status";
    private static final String MOVE_INFO_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String SIGNUP_MESSAGE = "> 회원 가입: signup 이름 - 예. signup 초코칩";
    private static final String LOGIN_MESSAGE = "> 로그인: login 이름 - 예. login 초코칩";
    private static final String USER_STATUS = "> 로그인 가능한 사용: %s";
    private static final char EMPTY_SQUARE = '.';
    private static final String SCORE_STATUS_FORMAT = "%s: %.1f";
    private static final int BOARD_SIZE = 8;
    private static final String NAME_DELIMITER = ", ";

    public void printGameStartMessage() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(GAME_START_MESSAGE);
        stringJoiner.add(START_INFO_MESSAGE);
        stringJoiner.add(END_INFO_MESSAGE);
        stringJoiner.add(STATUS_INFO_MESSAGE);
        stringJoiner.add(MOVE_INFO_MESSAGE);
        System.out.println(stringJoiner);
    }

    public void printBoard(final List<PieceResponse> pieces) {
        char[][] board = setUpBoard();
        addPieceToBoard(pieces, board);
        printBoardStatus(board);
        System.out.println();
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
        IntStream.range(0, board.length)
                .mapToObj(lineCount -> board[board.length - 1 - lineCount])
                .map(String::new)
                .forEach(System.out::println);
    }

    public void printStatus(final GameResult gameResult) {
        printScoreStatus(gameResult, Color.WHITE);
        printScoreStatus(gameResult, Color.BLACK);
    }

    private void printScoreStatus(final GameResult gameResult, final Color color) {
        System.out.printf(SCORE_STATUS_FORMAT + System.lineSeparator(),
                color.name(), gameResult.calculateScore(color).getValue());
    }

    public void printUserEntranceMessage() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(SIGNUP_MESSAGE);
        stringJoiner.add(LOGIN_MESSAGE);
        System.out.println(stringJoiner);
    }

    public void printUserStatus(final List<String> names) {
        StringJoiner stringJoiner = new StringJoiner(NAME_DELIMITER);
        names.forEach(stringJoiner::add);
        if(stringJoiner.length() == 0){
            System.out.printf(USER_STATUS + System.lineSeparator(), "없음");
            return;
        }
        System.out.printf(USER_STATUS + System.lineSeparator(), stringJoiner);
    }
}
