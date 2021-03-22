package chess.view;

import chess.domain.ChessResult;
import chess.domain.Position;
import chess.domain.TeamColor;


public class OutputView {

    private static final String SCORE_FORM = "%s팀 : %.1f" + System.lineSeparator();
    private static final String WINNER_FORM = "%s팀 승리!";

    private OutputView(){}

    public static void printByFormat(String format, Object... message) {
        System.out.printf(format, message);
    }

    public static void printBoard(BoardDto boardDto) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = boardDto.boardSize() - 1; y >= 0; y--) {
            appendPieceNames(boardDto, stringBuilder, y);
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder);
    }

    private static void appendPieceNames(BoardDto boardDto, StringBuilder stringBuilder, int column) {
        for (int x = 0; x < boardDto.boardSize(); x++) {
            Position currentPosition = Position.of(x, column);
            stringBuilder.append(boardDto.board().getOrDefault(currentPosition, "."));
        }
    }

    public static void printStartGame() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printCheck() {
        System.out.println("체크!");
    }

    public static void printResult(ChessResult chessResult) {
        printByFormat(SCORE_FORM, TeamColor.WHITE, chessResult.whiteTeamScore().value());
        printByFormat(SCORE_FORM, TeamColor.BLACK, chessResult.blackTeamScore().value());
        printWinner(chessResult.winner());
    }

    public static void printWinner(TeamColor teamColor) {
        printByFormat(WINNER_FORM, teamColor);
    }
}
