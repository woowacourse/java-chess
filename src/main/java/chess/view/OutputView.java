package chess.view;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.Piece;

import static chess.domain.piece.info.Position.POSITIONS;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요");
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        int index = 0;
        for (String key : POSITIONS.keySet()) {
            if (index % 8 == 0) {
                System.out.println();
            }
            index++;
            Piece piece = chessBoard.findByPosition(POSITIONS.get(key));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    public static void printStatus(ChessBoard chessBoard) {
        System.out.println("black: " + chessBoard.sumScoreByColor(Color.BLACK));
        System.out.println("white: " + chessBoard.sumScoreByColor(Color.WHITE));
    }
}
