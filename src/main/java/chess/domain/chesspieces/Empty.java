package chess.domain.chesspieces;

public class Empty extends ChessPiece {
    public Empty() {
        super(".");
    }

    @Override
    public boolean validateMovableTileSize(int rowDiff, int columnDiff) {
        return false;
    }
}
