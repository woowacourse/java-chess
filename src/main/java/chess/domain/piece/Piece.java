package chess.domain.piece;

import java.util.List;

import chess.domain.*;
import chess.exception.NotFoundPathException;

public abstract class Piece {
    private final Player player;
    private final Type type;

    protected Position position;

    public Piece(Player player, Type type, Position position) {
        this.player = player;
        this.type = type;
        this.position = position;
    }

    public void changePosition(Position position) {
        this.position = position;
    }

    public Player getPlayer() {
        return player;
    }

    public Position getPosition() {
        return position;
    }

    public ChessPieceInfo getChessPiece() {
        return ChessPieceInfo.getChessPiece(player, type);
    }

    public String getPieceImage() {
        return ChessPieceInfo.getPieceImage(this.player, this.type);
    }

    Path getValidPath(Position end, List<MovementInfo> movementInfos) {
        Path validPath = movementInfos.stream()
                .map(movementInfo -> getPath(movementInfo, end))
                .filter(path -> path.contains(end))
                .findFirst()
                .orElseThrow(NotFoundPathException::new);
        validPath.removeEndPosition();
        return validPath;
    }

    private Path getPath(MovementInfo movementInfo, Position end) {
        Path path = new Path();
        Direction direction = movementInfo.getDirection();
        int maxDistance = movementInfo.getMaxDistance();
        Position currentPosition = position;

        while (currentPosition.canMove(direction) && path.getDistance() < maxDistance) {
            currentPosition = currentPosition.move(direction);
            path.add(currentPosition);
            if (currentPosition.equals(end)) {
                break;
            }
        }
        return path;
    }

    public int getCoordinateX() {
        return position.getCoordinateX();
    }

    public int getCoordinateY() {
        return position.getCoordinateY();
    }

    public Score getScore() {
        return type.getScore();
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSamePosition(Piece piece) {
        return this.position.equals(piece.position);
    }

    public boolean isMine(Player player) {
        return this.player.equals(player);
    }

    public boolean isSameCoordinateX(int x) {
        return position.isSameCoordinateX(x);
    }

    public boolean isKing() {
        return type.equals(Type.KING);
    }

    public boolean isPawn() {
        return type.equals(Type.PAWN);
    }

    public boolean isEmpty() {
        return type.equals(Type.EMPTY);
    }

    public abstract Path getMovablePath(Position end);

    public abstract Path getAttackablePath(Position end);
}
