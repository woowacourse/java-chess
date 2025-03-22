package chess.view;

import chess.Board;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;

public class ResultView {

    private static final String BLANK = " ";

    public void showBoard(final Board board) {
        Map<Position, Piece> pieces = board.getPieces();
        Column col = Column.A;
        Row row = Row.EIGHT;

        StringBuilder sb = new StringBuilder();

        while (true) {
            while (true) {
                Position position = new Position(col, row);
                if (!pieces.containsKey(position)) {
                    sb.append(".");
                    if (col.isFarRight()){
                        break;
                    }
                    col = col.moveRight();
                    continue;
                }
                Piece piece = pieces.get(position);
                sb.append(piece.getMoveType().getValue());
                if (col.isFarRight()){
                    break;
                }
                col = col.moveRight();
            }
            sb.append("\n");
            if (row.isBottom()){
                break;
            }
            row = row.moveDown();
            col = Column.A;
        }
        System.out.println(sb.toString());
    }
}
