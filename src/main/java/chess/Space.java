package chess;

import chess.piece.Piece;
import chess.position.Position;

public class Space {

    private Piece piece;
    private final Position position;

    public Space(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public String pieceCharacter() {
        return PieceSign.findSign(piece);
    }

    public void movePiece(Space targetSpace) {
        if (!piece.isMovable(position, targetSpace.position)) {
            throw new IllegalArgumentException("이동 규칙을 위반한 움직임 입니다.");
        }
        if (targetSpace.isBlankSpace()) {
            targetSpace.piece = piece;
            piece = null;
            return;
        }
        throw new IllegalArgumentException("해당위치에 피스가 이미 있습니다");
    }

    public boolean isBlankSpace() {
        return piece == null;
    }
}
