package chess.view;

import chess.domain.ChessBoard;
import chess.domain.chessPiece.ChessPiece;

import java.util.Optional;

public class OutputView {

    private static final char RANK_START = 'a';
    private static final char RANK_END = 'h';
    private static final int FILE_END = 8;
    private static final int FILE_START = 1;
    private static final String START_MESSAGE = "체스 게임을 시작합니다.\n" +
            "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private static final String EMPTY = ".";

    private OutputView() {
    }

    public static void printStartMessage(){
        System.out.println(START_MESSAGE);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        for (int j = FILE_END; j >= FILE_START; j--) {
            printRank(chessBoard, j);
        }
    }

    private static void printRank(ChessBoard chessBoard, int j) {
        for (int i = RANK_START; i <= RANK_END; i++) {
            String position = (char) i + String.valueOf(j);

            Optional<ChessPiece> possiblePiece = chessBoard.findPiece(position);
            possiblePiece.ifPresentOrElse(
                    (piece) -> System.out.print(piece.getName()),
                    () -> System.out.print(EMPTY));
        }
        System.out.println();
    }
}
