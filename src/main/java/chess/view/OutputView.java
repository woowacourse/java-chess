package chess.view;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.piece.Piece;

import java.util.Map;

public class OutputView {

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printCommandInstruction() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.get(new Position(rank, file));
                System.out.print(piece.getSymbol());
            }
            System.out.println();
        }
    }
}
