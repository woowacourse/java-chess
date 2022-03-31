package chess.view;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.piece.PieceColor;
import chess.view.dto.board.BoardDto;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String COMMAND_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.%n"
            + "> 게임 시작 : start%n"
            + "> 게임 종료 : end%n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";

    public static void printCommandGuide() {
        System.out.printf(COMMAND_GUIDE_MESSAGE);
    }

    public static void printBoard(Board board) {
        BoardDto boardDto = new BoardDto(board);
        System.out.println(boardDto.getBoardText());
    }

    public static void printScore(ScoreResult scoreResult) {
        System.out.println("흰색팀 점수 : " + scoreResult.getScoreByPieceColor(PieceColor.WHITE).getValue());
        System.out.println("검정색팀 점수 : " + scoreResult.getScoreByPieceColor(PieceColor.BLACK).getValue());
    }

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }
}
