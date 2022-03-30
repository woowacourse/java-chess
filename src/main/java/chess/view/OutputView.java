package chess.view;

import chess.model.board.GameScore;
import chess.model.piece.Piece;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Map;

public class OutputView {

    private static final String WHITE_SCORE_MESSAGE_FORMAT = "화이트 점수: %.0f%n";
    private static final String BLACK_SCORE_MESSAGE_FORMAT = "블랙 점수: %.0f%n";
    private static final String NOW_WINNER_MESSAGE_FORMAT = "현재 승자: %s%n";

    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            printFilesIn(board, rank);
            System.out.println();
        }
    }

    private void printFilesIn(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            Piece piece = board.get(Position.of(rank, file));
            PieceView.printSymbolOf(piece);
        }
    }

    public void printScores(final GameScore scores) {
        System.out.printf(WHITE_SCORE_MESSAGE_FORMAT, scores.getWhiteScore());
        System.out.printf(BLACK_SCORE_MESSAGE_FORMAT, scores.getBlackScore());
        System.out.printf(NOW_WINNER_MESSAGE_FORMAT, scores.pickWinner());
    }
}
