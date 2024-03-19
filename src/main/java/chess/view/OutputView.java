package chess.view;

import chess.ChessBoard;
import chess.Piece;

import java.util.List;

public class OutputView {

    public void printChessBoard(final ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();

        List<Piece> values = chessBoard.findAllPieces(); //이놈은 게터가 있다!! (똥)

        int idx = 1;
        for (Piece value : values) {
            stringBuilder.append(PieceExpression.mapToExpression(value));
            if (idx % 8 == 0) {
                stringBuilder.append(System.lineSeparator());
            }
            idx++;
        }

        System.out.println(stringBuilder);

        // TODO: 고민
//        for (char rank = 'a'; rank <= 'h'; rank++) { // 범위를 공개! 이건 캡슐화 노노(똥)
//            for (int file = 1; file <= 8; file++) {
//                Piece piece = chessBoard.findBySquare(Square.of(rank, file));
//                System.out.print("이넘.of(value)");
//            }
//            System.out.println();
//        }
    }
}
