package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.direction.Direction;
import chess.domain.direction.KnightDirection;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Objects;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "KNIGHT";

    private static PieceInfo pieceInfo = PieceInfo.valueOf(KNIGHT_NAME);

    public Knight(Player player) {
        super(player, pieceInfo);
        directions.addAll(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean movable(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return super.movable(from, to) && validateMovePosition(from, to);
    }

    public boolean validateMovePosition(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        return Arrays.stream(KnightDirection.values())
                .anyMatch(KnightDirection -> KnightDirection.contains(from, to));
    }

    @Override
    public boolean validateTileSize(Position from, Position to) {
        return false;
    }
}
