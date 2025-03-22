package chess.view;

import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Map;

public class OutputView {
    public void showPieces(Piece piece) {
        if(piece == null){
            System.out.print(" ");
            return;
        }
        System.out.print(piece.pieceType().getType());
    }

    public void printChessMap(Map<Position, Piece> positionPieceMap) {
        for(Row row : Row.values()){
            for(Column column : Column.values()){
                Position position = new Position(row, column);
                Piece piece = positionPieceMap.get(position);
                showPieces(piece);
            }
            System.out.println();
        }

    }
}
