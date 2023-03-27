package chess.view;

import chess.controller.dto.ChessBoardDto;
import chess.controller.dto.ChessResultDto;
import chess.controller.dto.PieceDto;
import chess.controller.dto.PositionDto;

import java.util.Map;

import static chess.controller.CommandType.END;
import static chess.controller.CommandType.MOVE;
import static chess.controller.CommandType.START;

public final class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final char BLANK = '.';
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3";
    private static final String RESULT_MESSAGE = "> 체스 게임 결과입니다.\n" +
            "> 백진영 (WHITE) : %.1f\n" +
            "> 흑진영 (BLACK) : %.1f\n" +
            "> 승리한 팀 : %s\n";

    private static final String USER_NAME_INPUT_MESSAGE = "플레이어님의 이름을 입력해 주세요. (1~20자)";

    private static final String DRAW = "DRAW!";
    private static final String WHITE = "WHITE";
    private static final String BLACK = "BLACK";


    public static void print(final String message) {
        System.out.println(message);
    }

    public static void printUserNameInputMessage() {
        print(USER_NAME_INPUT_MESSAGE);
    }

    public static void printStartMessage() {
        print(String.format(START_MESSAGE, START.name().toLowerCase(),
                END.name().toLowerCase(), MOVE.name().toLowerCase()));
    }

    public static void printBoard(final ChessBoardDto chessBoardDto) {
        final StringBuilder boardMessage = new StringBuilder();
        for (int rank = BOARD_SIZE - 1; rank >= 0; rank--) {
            boardMessage.append(makeFileMessage(chessBoardDto, rank)).append(System.lineSeparator());
        }
        print(boardMessage.toString());
    }

    public static void printChessResult(final ChessResultDto chessResultDto) {
        final double whiteScore = chessResultDto.getWhiteScore();
        final double blackScore = chessResultDto.getBlackScore();
        final String winnerCamp = getWinnerCamp(whiteScore, blackScore);
        print(String.format(RESULT_MESSAGE, whiteScore, blackScore, winnerCamp));
    }

    private static String makeFileMessage(final ChessBoardDto chessBoardDto, final int rank) {
        final StringBuilder fileMessage = new StringBuilder();
        for (int file = 0; file < BOARD_SIZE; file++) {
            fileMessage.append(getPieceName(chessBoardDto, rank, file));
        }
        return fileMessage.toString();
    }

    private static char getPieceName(final ChessBoardDto chessBoardDto, final int rank, final int file) {
        final PositionDto positionDto = PositionDto.from(rank, file);
        final Map<PositionDto, PieceDto> board = chessBoardDto.getBoard();
        if (board.containsKey(positionDto)) {
            return PieceName.findMessage(board.get(positionDto));
        }
        return BLANK;
    }

    private static String getWinnerCamp(final double whiteScore, final double blackScore) {
        if (whiteScore == blackScore) {
            return DRAW;
        }
        if (whiteScore < blackScore) {
            return BLACK;
        }
        return WHITE;
    }
}
