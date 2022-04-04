package chess.view;

import chess.chessboard.position.File;
import chess.game.Player;
import chess.chessboard.position.Position;
import chess.chessboard.position.Rank;
import chess.piece.Piece;

import java.util.Map;

public final class OutputView {

    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final Map<Position, Piece> board) {
        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                final Piece piece = board.get(Position.of(rank, file));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }

    public void printStatusInstruction() {
        System.out.println("결과 출력을 원하면 status 명령을 입력하세요. 종료하려면 아무키나 입력하세요.");
    }

    public void printScores(final Map<Player, Double> calculateScore) {
        for (final Player player : calculateScore.keySet()) {
            if (player == Player.BLACK) {
                System.out.printf("검은색: %.1f\n", calculateScore.get(player));
            }
            if (player == Player.WHITE) {
                System.out.printf("흰색: %.1f\n", calculateScore.get(player));
            }
        }
        printWinner(calculateScore);
    }

    private void printWinner(final Map<Player, Double> calculateScore) {
        if (calculateScore.get(Player.BLACK) >= calculateScore.get(Player.WHITE)) {
            System.out.println("블랙 승");
            return;
        }
        System.out.println("흰색 승");
    }
}
