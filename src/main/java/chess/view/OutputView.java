package chess.view;

import chess.domain.GameResult;
import chess.domain.Position;
import chess.domain.TeamColor;
import chess.view.dto.BoardDto;

public final class OutputView {

    private static final String EMPTY_SPACE = ".";
    private static final String CHECKMATE_FORM = "체크메이트입니다. 승자는 %s팀 입니다.";
    private static final String KING_DEAD_FORM = "왕이 죽었습니다. 승자는 %s팀 입니다.";
    private static final String CHECK_MESSAGE = "체크입니다.";
    private static final String GAME_END_MESSAGE = "게임을 종료합니다.";
    private static final String SCORE_FORM = "%s팀 : %.1f" + System.lineSeparator();
    private static final String WINNER_FORM = "승자는 %s팀 입니다.";

    private OutputView() {}

    public static void printBoard(BoardDto boardDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = boardDto.boardSize() - 1; y >= 0; y--) {
            appendPieceNames(boardDto, stringBuilder, y);
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static void appendPieceNames(BoardDto boardDto, StringBuilder stringBuilder,
        int column) {
        for (int x = 0; x < boardDto.boardSize(); x++) {
            Position currentPosition = Position.of(x, column);
            stringBuilder.append(boardDto.board().getOrDefault(currentPosition, EMPTY_SPACE));
        }
    }

    public static void printCheckmate(TeamColor winner) {
        System.out.printf(CHECKMATE_FORM, winner);
    }

    public static void printKingDead(TeamColor winner) {
        System.out.printf(KING_DEAD_FORM, winner);
    }

    public static void printCheck() {
        System.out.println(CHECK_MESSAGE);
    }

    public static void printStartGame() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printEndGame() {
        System.out.println(GAME_END_MESSAGE);
    }

    public static void printResult(GameResult gameResult) {
        System.out.printf(SCORE_FORM, TeamColor.WHITE, gameResult.whiteTeamScore().value());
        System.out.printf(SCORE_FORM, TeamColor.BLACK, gameResult.blackTeamScore().value());
        System.out.printf(WINNER_FORM, gameResult.winner());
    }
}
