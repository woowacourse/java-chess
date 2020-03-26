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
        validateMove(target, boardDto);
        this.position = target;
        return makePieceState();
    }

    private void validateMove(Position target, Map<Position, PieceDto> boardDto) {
        validateAlly(target, boardDto);
        validateMovingPolicy(target, boardDto);
    }

    private void validateAlly(Position target, Map<Position, PieceDto> boardDto) {
        PieceDto pieceDto = boardDto.get(target);
        if (!Objects.isNull(pieceDto) && pieceDto.getPlayer() == player) {
            throw new IllegalArgumentException("아군의 말 위치로는 이동할 수 없습니다.");
        }
    }

    protected abstract void validateMovingPolicy(Position target, Map<Position, PieceDto> boardDto);

    protected abstract PieceState makePieceState();

    public Position getPosition() {
        return position;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
