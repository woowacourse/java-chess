package chess;

import chess.piece.EmptyPiece;
import chess.piece.Piece;
import chess.position.Position;
import java.util.List;

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

    public void movePiece(Space targetSpace, List<Space> spaces) {
        validateClearRoute(targetSpace, spaces);
        if (piece.isCatchable(position, targetSpace.position)) {
            targetSpace.piece = piece;
            piece = new EmptyPiece();
            return;
        }
        if (!piece.isMovable(position, targetSpace.position)) {
            throw new IllegalArgumentException("이동 규칙을 위반한 움직임입니다.");
        }
        if (piece.isSameColor(targetSpace.piece)) {
            throw new IllegalArgumentException("해당 위치에 피스가 이미 있습니다.");
        }
        if (targetSpace.doesNotHavePiece()) {
            targetSpace.piece = piece;
            piece = new EmptyPiece();
            return;
        }
        throw new IllegalArgumentException("해당 위치의 상대 말을 잡을 수 없습니다.");
    }

    private void validateClearRoute(Space targetSpace, List<Space> spaces) {
        //TODO: 나이트 바로 반환 구현
        List<Position> routes = targetSpace.position.findRoute(position);
        for(Position position : routes){
            for(Space space: spaces){
                if(space.position.equals(position)){
                    if(space.hasPiece()){
                        throw new IllegalArgumentException("루트에 피스가 있습니다.");
                    }
                }
            }
        }
    }

    private boolean hasPiece() {
        return !piece.isSameColor(new EmptyPiece());
    }

    public boolean doesNotHavePiece() {
        return piece.isSameColor(new EmptyPiece());
    }
}
