package chess.domain.piece;

import chess.domain.move.MoveStrategy;
import chess.domain.move.MoveStrategyFactory;
import chess.domain.position.Position;

public class Piece {
    private final PieceType pieceType;
    private final Team team;
//    private final MoveStrategy moveStrategy;

    public Piece(final PieceType pieceType, final Team team) {
        this.pieceType = pieceType;
        this.team = team;
//        this.moveStrategy = MoveStrategyFactory.create(pieceType, team);
    }

    public String toSymbol() {
        return this.team.convert(this.pieceType.getSymbol());
    }

    public boolean move(Position source, Position target) {
        return this.pieceType.move(source, target);
    }
}
