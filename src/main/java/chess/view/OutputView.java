package chess.view;

import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.view.display.PieceDisplay;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printBoard(Map<Position, PieceType> board) {
        List<String> lines = PieceDisplay.makeBoardDisplay(board);
        lines.forEach(System.out::println);
    }
}
