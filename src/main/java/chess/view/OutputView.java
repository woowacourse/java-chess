package chess.view;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printGameRule() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.get(Position.of(rank, file));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }
}
