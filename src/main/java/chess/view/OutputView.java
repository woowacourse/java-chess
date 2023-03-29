package chess.view;

import chess.domain.board.Position;
import chess.domain.game.Score;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Team;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다." + LINE_SEPARATOR
            + "> 게임 시작 : start" + LINE_SEPARATOR
            + "> 게임 종료 : end" + LINE_SEPARATOR
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + LINE_SEPARATOR
            + "> 점수 확인 : status");
    }

    public void printBoard(final Map<Position, Piece> board) {
        System.out.println();
        for (char row = '8'; row >= '1'; row--) {
            printLine(board, row);
            System.out.println();
        }
    }

    private void printLine(final Map<Position, Piece> board, final char row) {
        for (char col = 'a'; col <= 'h'; col++) {
            String position = String.valueOf(col) + String.valueOf(row);
            Piece piece = board.get(Position.from(position));
            printPiece(piece);
        }
    }

    private void printPiece(final Piece piece) {
        if (piece.isWhiteTeam() || piece.isEmpty()) {
            System.out.print(ViewPiece.getName(piece));
        }
        if (piece.isBlackTeam()) {
            System.out.print(ViewPiece.getName(piece).toUpperCase());
        }
    }

    public void printWin(final Score score) {
        double whiteTeamScore = score.getScoreTable().get(Team.WHITE);
        double blackTeamScore = score.getScoreTable().get(Team.BLACK);
        System.out.println("최종결과 white팀 : " + whiteTeamScore + " black팀 : " + blackTeamScore);

        if (whiteTeamScore > blackTeamScore) {
            System.out.println("White팀이 승리했습니다.");
            return;
        }
        if (blackTeamScore > whiteTeamScore) {
            System.out.println("black팀이 승리했습니다.");
            return;
        }
        System.out.println("무승부 입니다.");
    }
}
