package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.result.ResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NOTICE_CHESS_GAME = "> 체스 게임을 시작합니다.";
    private static final String NOTICE_CHESS_GAME_START = "> 게임 시작 : start";
    private static final String NOTICE_CHESS_GAME_END = "> 게임 종료 : end";
    private static final String NOTICE_CHESS_GAME_HOW_MOVE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String SKELETON_RESULT_WHITE_SCORE = "WHITE POINT: %s";
    private static final String SKELETON_RESULT_BLACK_SCORE = "BLACK POINT: %s";
    private static final String WINNER = "승자: %s";

    private OutputView() {
    }

    public static void printBoard(Map<Position, Piece> chessBoard) {
        List<String> chessBoardNames = chessBoard.values().stream()
            .map(Piece::getPieceName)
            .collect(Collectors.toList());

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoardNames.get(i * 8 + j));
            }
            System.out.println();
        }
    }

    public static void printInitMessage() {
        System.out.println(NOTICE_CHESS_GAME);
        System.out.println(NOTICE_CHESS_GAME_START);
        System.out.println(NOTICE_CHESS_GAME_END);
        System.out.println(NOTICE_CHESS_GAME_HOW_MOVE);
    }

    public static void printScore(ResultDto resultDto) {
        System.out.println(String.format(SKELETON_RESULT_WHITE_SCORE, resultDto.whiteScore()));
        System.out.println(String.format(SKELETON_RESULT_BLACK_SCORE + resultDto.blackScore()));
        System.out.println(String.format(WINNER + resultDto.getWinner()));
    }
}
