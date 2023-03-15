package chess.view;

import static chess.view.PieceRender.render;
import static java.util.stream.Collectors.toList;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Rank;
import chess.domain.board.Square;
import java.util.List;

public class OutputView {

    public static void printBoard(Board board) {
        List<Rank> ranks = board.getBoard();
        List<List<Square>> squares = ranks.stream()
                .map(Rank::getRank)
                .collect(toList());

        for (int row = 0; row < squares.size(); row++) {
            for (int col = 0; col < squares.get(0).size(); col++) {
                Square square = squares.get(row).get(col);
                Piece piece = square.getPiece();
                System.out.print(render(piece));
            }
            System.out.println();
        }
    }

    public static void printFinishMessage() {
        System.out.println("end");
    }
}
