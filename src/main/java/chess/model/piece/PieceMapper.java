package chess.model.piece;

@FunctionalInterface
public interface PieceMapper {

    Piece mapToPiece(Integer id, Color color, Integer squareId);
}
