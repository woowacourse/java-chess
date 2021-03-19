package chess.view;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;


public class OutputView {

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

    public static void printScore(Score whiteTeamScore, Score blackTeamScore) {
        System.out.println("흰색 팀 : " + whiteTeamScore.value());
        System.out.println("검은색 팀 : " + blackTeamScore.value());

    }

    public static void printWinner(TeamColor teamColor) {
        System.out.println(teamColor + " 승리!!");
    }
}
