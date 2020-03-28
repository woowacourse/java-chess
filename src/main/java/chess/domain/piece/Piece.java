package chess.domain.piece;

import chess.domain.board.BoardState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece implements PieceState {

    private final PieceType pieceType;
    protected final Position position;
    protected final Team team;

    protected Piece(PieceType pieceType, Position position, Team team) {
        this.pieceType = pieceType;
        this.position = position;
        this.team = team;
    }

    @Override
    public PieceState move(Position target, BoardState boardState) {
        List<Position> positions = getMovablePositions(boardState);
        if (!positions.contains(target)) {
            throw new IllegalArgumentException("이동 할 수 없는 position입니다.");
        }
        return movedPieceState(target);
    }

    protected abstract PieceState movedPieceState(Position target);

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public String getFigure() {
        return pieceType.getFigure(team);
    }

    @Override
    public double getPoint() {
        return pieceType.getPoint();
    }
}
