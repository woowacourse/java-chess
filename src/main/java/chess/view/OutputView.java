package chess.view;

import chess.domain.game.score.ScoreResult;
import chess.domain.piece.PieceColor;
import chess.view.dto.BoardDto;
import chess.view.dto.PieceDto;
import java.util.List;
import java.util.Optional;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String COMMAND_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.%n"
            + "> 게임 시작 : start%n"
            + "> 게임 종료 : end%n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";
    private static final String EMPTY_PIECE_NOTATION = ".";

    public static void printCommandGuide() {
        System.out.printf(COMMAND_GUIDE_MESSAGE);
    }

    public static void printBoard(BoardDto boardDto) {
        for (List<Optional<PieceDto>> row : boardDto.getValue()) {
            printRowOfBoard(row);
        }
    }

    private static void printRowOfBoard(List<Optional<PieceDto>> row) {
        for (Optional<PieceDto> pieceDto : row) {
            String notation = pieceDto.map(PieceDto::getConsoleNotation)
                    .orElse(EMPTY_PIECE_NOTATION);
            System.out.print(notation);
        }

        System.out.println();
    }

    public static void printScore(ScoreResult scoreResult) {
        // TODO: 상수로 분리
        System.out.println("흰색팀 점수 : " + scoreResult.getScoreByPieceColor(PieceColor.WHITE).getValue());
        System.out.println("검정색팀 점수 : " + scoreResult.getScoreByPieceColor(PieceColor.BLACK).getValue());
    }

    public static void printException(Exception exception) {
        System.out.println(ERROR_MESSAGE_PREFIX + exception.getMessage());
    }
}
