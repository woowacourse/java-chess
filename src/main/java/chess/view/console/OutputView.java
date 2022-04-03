package chess.view.console;

import chess.dto.BoardDto;
import chess.dto.CurrentTurnDto;
import chess.dto.PieceDto;
import chess.dto.ScoreResultDto;
import java.util.List;
import java.util.Optional;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String COMMAND_GUIDE_MESSAGE = "> 체스 게임을 시작합니다." + NEW_LINE
            + "> 게임 시작 : start" + NEW_LINE
            + "> 게임 종료 : end" + NEW_LINE
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String EMPTY_PIECE_NOTATION = ".";

    public static void printCommandGuide() {
        System.out.println(COMMAND_GUIDE_MESSAGE);
    }

    public static void printBoard(BoardDto boardDto) {
        for (List<Optional<PieceDto>> row : boardDto.getValue()) {
            printRowOfBoard(row);
        }
    }

    private static void printRowOfBoard(List<Optional<PieceDto>> row) {
        for (Optional<PieceDto> pieceDto : row) {
            String notation = pieceDto.map(PieceDto::getConsoleText)
                    .orElse(EMPTY_PIECE_NOTATION);
            System.out.print(notation);
        }

        System.out.println();
    }

    public static void printCurrentTurn(CurrentTurnDto currentTurnDto) {
        System.out.println(currentTurnDto.getDisplayName() + "의 차례입니다.");
    }

    public static void printScore(ScoreResultDto scoreResultDto) {
        // TODO: 상수로 분리
        System.out.println("흰색팀 점수 : " + scoreResultDto.getWhiteScore());
        System.out.println("검정색팀 점수 : " + scoreResultDto.getBlackScore());
    }

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }
}
