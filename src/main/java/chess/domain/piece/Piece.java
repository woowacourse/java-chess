package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.player.Player;
import chess.domain.position.Position;

public abstract class Piece implements PieceState {

    protected PieceType pieceType;
    protected Position position;
    protected Player player;

    protected Piece(PieceType pieceType, Position position, Player player) {
        this.pieceType = pieceType;
        this.position = position;
        this.player = player;
    }

    @Override
    public PieceState move(Position target, BoardState boardState) {
        validateMove(target, boardState);
        this.position = target;
        return makePieceState();
    }

    private void validateMove(Position target, BoardState boardState) {
        validateMovingPolicy(target, boardState);
        validateAlly(target, boardState);
    }

    private void validateAlly(Position target, BoardState boardState) {
        if (boardState.isSameTeam(target, player)) {
            throw new IllegalArgumentException("아군의 말 위치로는 이동할 수 없습니다.");
        }
    }

    protected abstract void validateMovingPolicy(Position target, BoardState boardState);

    protected abstract PieceState makePieceState();

    public Position getPosition() {
        return position;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public double getPoint() {
        return pieceType.getPoint();
    }
}
