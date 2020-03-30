package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.exception.MovingException;

import java.util.List;

public abstract class Piece implements PieceState {

    protected final PieceType pieceType;
    protected final Position position;
    protected final Team team;

    protected Piece(PieceType pieceType, Position position, Team team) {
        this.pieceType = pieceType;
        this.position = position;
        this.team = team;
    }

    @Override
    public PieceState move(Position target, BoardSituation boardSituation) {
        List<Position> positions = getMovablePositions(boardSituation);
        if (!positions.contains(target)) {
            throw new MovingException();
        }
        return movedPieceState(target);
    }

    protected abstract PieceState movedPieceState(Position target);

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public double getPoint(BoardSituation boardSituation) {
        return pieceType.getPoint();
    }
}
