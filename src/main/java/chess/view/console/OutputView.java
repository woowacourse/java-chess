package chess.view.console;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import chess.dto.response.BoardDto;
import chess.dto.response.PieceDto;
import chess.dto.response.PositionDto;
import chess.dto.response.ScoreResultDto;
import chess.dto.response.TurnDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        Map<PositionDto, PieceDto> value = boardDto.getValue();
        List<YAxis> yAxes = Arrays.asList(YAxis.values());
        Collections.reverse(yAxes);

        for (YAxis yAxis : yAxes) {
            printRowOfBoard(value, yAxis);
            System.out.println();
        }
    }

    private static void printRowOfBoard(Map<PositionDto, PieceDto> value, YAxis yAxis) {
        for (XAxis xAxis : XAxis.values()) {
            PositionDto positionDto = PositionDto.from(Position.of(xAxis, yAxis));
            PieceDto pieceDto = value.get(positionDto);
            System.out.print(getPieceNotation(pieceDto));
        }
    }

    private static String getPieceNotation(PieceDto pieceDto) {
        if (!Objects.isNull(pieceDto)) {
            return pieceDto.getConsoleText();
        }
        return EMPTY_PIECE_NOTATION;
    }

    public static void printCurrentTurn(TurnDto turnDto) {
        System.out.println(turnDto.getDisplayName() + "의 차례입니다.");
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
