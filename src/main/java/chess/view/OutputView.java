package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.GamePiece;

import java.util.List;

public class OutputView {

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력해세요.");
    }

    public static void printBoard(Board board) {
        for (List<GamePiece> gamePiece : board.gamePieces()) {
            for (GamePiece piece : gamePiece) {
                System.out.print(piece.getName());
            }
            System.out.println();
        }
    }
}
