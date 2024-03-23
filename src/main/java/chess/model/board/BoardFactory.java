package chess.model.board;

import chess.dto.PieceMapper;
import chess.model.Position;
import chess.model.piece.Piece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardFactory {

    Board generate();

    default Map<Position, Piece> generatePieces(List<String> snapShot) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (int i = 0; i < snapShot.size(); i++) {
            String row = snapShot.get(i);
            putPiecesInRow(pieces, row, i);
        }
        return pieces;
    }

    private void putPiecesInRow(Map<Position, Piece> pieces, String row, int rowIndex) {
        for (int j = 0; j < row.length(); j++) {
            Position position = new Position(rowIndex, j);
            String pieceName = String.valueOf(row.charAt(j));
            Piece piece = PieceMapper.deserialize(pieceName);
            pieces.put(position, piece);
        }
    }
}
