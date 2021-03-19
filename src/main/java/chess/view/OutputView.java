package chess.view;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printBoard(Map<Position, Piece> chessBoard) {
        List<String> chessBoardNames = chessBoard.values().stream()
            .map(Piece::getPieceName)
            .collect(Collectors.toList());

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoardNames.get(i * 8 + j));
            }
            System.out.println();
        }
    }

}
