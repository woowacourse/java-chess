package chess.piece;

import chess.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> mapView;

    public static Board emptyBoard() {
        return new Board(new HashMap<>());
    }

    public void gameSetUp() {
        final List<Piece> pawns = Pawn.initialize();
        final List<Piece> knights = Knight.initialize();
        final List<Piece> rooks = Rook.initialize();
        final List<Piece> bishops = Bishop.initialize();
        final List<Piece> kings = King.initialize();
        final List<Piece> queens = Queen.initialize();
        locatePiece(pawns, knights, rooks, bishops, kings, queens);
    }

    private void locatePiece(final List<Piece> ...piecesGroup) {
        for (List<Piece> pieces : piecesGroup) {
            for (Piece piece : pieces) {
                mapView.put(piece.getPosition(), piece);
            }
        }
    }

    public Board(final Map<Position, Piece> mapView) {
        this.mapView = mapView;
    }

    public boolean isSameTeam(final Position position, final Piece piece) {
        return mapView.get(position).getColor() == piece.getColor();
    }

    public boolean isEmptyPosition(final Position position) {
        return mapView.get(position) == null;
    }

    public boolean isEnemy(final Position position, final Piece piece) {
        return !isEmptyPosition(position) && (mapView.get(position).getColor() != piece.getColor());
    }

    public Map<Position, Piece> getMapView() {
        return mapView;
    }

    public Piece move(final Position startPosition, final Position endPosition) {
        validateStartPosition(startPosition);
        validateEndPosition(startPosition, endPosition);
        final Piece startPiece = mapView.remove(startPosition);
        final Piece endPiece = mapView.get(endPosition);
        mapView.put(endPosition, startPiece.copyOf(endPosition));
        return endPiece;
    }

    private void validateStartPosition(final Position position) {
        if (mapView.get(position) == null) {
            throw new IllegalArgumentException("해당 위치에는 기물이 없습니다.");
        }
    }

    private void validateEndPosition(final Position startPosition, final Position endPosition) {
        final Piece startPiece = mapView.get(startPosition);
        if (!startPiece.calculateAvailablePositions(this).contains(endPosition)) {
            throw new IllegalArgumentException("해당 위치로는 이용할 수 없습니다.");
        }
    }
}
