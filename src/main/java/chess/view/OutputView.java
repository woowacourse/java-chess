package chess.view;

import chess.piece.Piece;

import java.util.List;

public class OutputView {

    //    1. rank..는 내림차순
//    2. fil..은 오름차순
    public static void printBoard(List<Piece> board) {


        board.stream()
                .sorted()
                .forEach(piece -> System.out.println(piece.getName()));

    }
}
