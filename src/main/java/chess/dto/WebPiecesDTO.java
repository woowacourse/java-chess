package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public class WebPiecesDTO {

    private Map<Position, Piece> pieces;
    private Position source;
    private Position target;

    public WebPiecesDTO(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public WebPiecesDTO(Map<Position, Piece> pieces, String source, String target) {
        this(pieces, Position.of(source), Position.of(target));
    }

    public WebPiecesDTO(Map<Position, Piece> pieces, Position source, Position target) {
        this.pieces = pieces;
        this.source = source;
        this.target = target;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
