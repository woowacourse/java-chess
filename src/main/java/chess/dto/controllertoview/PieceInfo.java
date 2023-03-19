package chess.dto.controllertoview;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.piece.Piece;

public class PieceInfo {

    private final Class<? extends Piece> pieceType;
    private final Color color;
    private final File positionFile;
    private final Rank positionRank;

    public PieceInfo(Piece piece) {
        this.pieceType = piece.getClass();
        this.color = piece.getColor();
        this.positionFile = piece.getPosition().getFile();
        this.positionRank = piece.getPosition().getRank();
    }

    public Class<? extends Piece> getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    public File getPositionFile() {
        return positionFile;
    }

    public Rank getPositionRank() {
        return positionRank;
    }
}
