package chess.domain.piece;

import java.util.List;

import chess.domain.*;

public class NormalPiece extends Piece {
    private final List<MovementInfo> movementInfos;

    NormalPiece(Player player, Type type, List<MovementInfo> movementInfos, Position position) {
        super(player, type, position);
        this.movementInfos = movementInfos;
    }

    @Override
    public Path getMovablePath(Position end) {
        return getValidPath(end, movementInfos);
    }

    @Override
    public Path getAttackablePath(Position end) {
        return getMovablePath(end);
    }
}
