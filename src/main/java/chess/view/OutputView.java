package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class OutputView {

    public void displayBoard(ChessBoard board) {
        StringBuilder sb = new StringBuilder();
        sb.append("   a b c d e f g h \n");
        for(Row row: Row.values()) {
            sb.append(row.intValue() + "| ");
            for(Column column : Column.values()) {
                Piece piece = board.findPieceBy(new Position(row, column));
                PieceType pieceType = piece.getPieceType();
                PieceTypeView pieceTypeView = PieceTypeView.from(pieceType);
                sb.append(pieceTypeView.getPieceNameBy(pieceType) + " ");
            }
            sb.append(System.lineSeparator());
        }

        sb.append("   a b c d e f g h \n");
        System.out.println(sb);
    }
}
