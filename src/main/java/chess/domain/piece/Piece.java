package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public abstract class Piece implements PieceState {

    protected Position position;
    protected Player player;

    protected Piece(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    @Override
    public PieceState move(Position target, Map<Position, PieceDto> boardDto) {
        if (!canMove(target, boardDto)) {
            throw new IllegalArgumentException("움직일 수 없는 positon입니다.");
        }
        this.position = target;
        return makePieceState();
    }

    private boolean canMove(Position target, Map<Position, PieceDto> boardDto) {
        PieceDto pieceDto = boardDto.get(target);
        if (!Objects.isNull(pieceDto) && pieceDto.getPlayer() == player) {
            throw new IllegalArgumentException("아군의 말 위치로는 이동할 수 없습니다.");
        }
        return checkMovingPolicy(target, boardDto);
    }

    protected abstract boolean checkMovingPolicy(Position target, Map<Position, PieceDto> boardDto);

    protected abstract PieceState makePieceState();

    public Position getPosition() {
        return position;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
