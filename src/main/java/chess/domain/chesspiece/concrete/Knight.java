package chess.domain.chesspiece.concrete;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.PieceInfo;
import chess.domain.direction.Direction;
import chess.domain.game.Player;
import chess.domain.position.Position;

import java.util.Objects;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "KNIGHT";

    public Knight(Player player) {
        super(player, PieceInfo.valueOf(KNIGHT_NAME));
        directions.addAll(Direction.knightDirection());
    }

    @Override
    public boolean needValidateObstacle() {
        return false;
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
        return directions.stream()
                .anyMatch(direction -> direction.getJudge(from, to));
    }
}
