package chess.model.board;

import chess.dto.PieceMapper;
import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BoardFactory {

    public abstract Board generate();

    protected final Map<Position, Piece> generatePieces(List<String> snapShot) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (int i = 0; i < snapShot.size(); i++) {
            String rank = snapShot.get(i);
            putPiecesInRow(pieces, rank, i);
        }
        return pieces;
    }

    private void putPiecesInRow(Map<Position, Piece> pieces, String rank, int rankIndex) {
        for (int i = 0; i < rank.length(); i++) {
            Row row = Row.findRow(rankIndex);
            Column column = Column.findColumn(i);
            Position position = new Position(column, row);
            String pieceName = String.valueOf(rank.charAt(i));
            Piece piece = PieceMapper.deserialize(pieceName);
            pieces.put(position, piece);
        }
    }
}
