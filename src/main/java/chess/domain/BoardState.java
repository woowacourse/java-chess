package chess.domain;

import chess.domain.piece.PieceDto;
import chess.domain.player.Player;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class BoardState {

    private Map<Position, PieceDto> boardState;

    private BoardState(Map<Position, PieceDto> boardState) {
        this.boardState = boardState;
    }

    public static BoardState of(Map<Position, PieceDto> boardState) {
        return new BoardState(boardState);
    }

    public boolean isSameTeam(Position target, Player player) {
        PieceDto pieceDto = boardState.get(target);
        return !Objects.isNull(pieceDto) && player.isSamePlayer(pieceDto.getPlayer());
    }

    public boolean isNotEmpty(Position target) {
        return !Objects.isNull(boardState.get(target));
    }

    public boolean isEmpty(Position target) {
        return Objects.isNull(boardState.get(target));
    }
}
