package chess.domain.state;

import chess.domain.Team;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public abstract class Playing implements GameState {
    private final Map<Position, Piece> board;

    public Playing(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece getPiece(Position position) {
        return board.get(position);
    }

    abstract Playing turn();

    public GameState move(String source, String destination) {
        Position sourcePosition = Position.from(source);
        Position destinationPosition = Position.from(destination);
        validateMovePiece(sourcePosition, destinationPosition);

        Piece destinationPiece = board.get(destinationPosition);
        movePiece(sourcePosition, destinationPosition);

        if (destinationPiece.isKing()) {
            return finished();
        }
        return turn();
    }

    private void validateMovePiece(Position source, Position destination) {
        Piece piece = getPiece(source);
        validateTeam(piece);
        List<Position> positions = piece.findPath(source, destination);
        validateMovingPath(source, destination, positions);
    }

    private void validateMovingPath(Position source, Position destination, List<Position> positions) {
        Piece sourcePiece = board.get(source);
        Piece destinationPiece = board.get(destination);
        if (sourcePiece.isSameTeam(destinationPiece)) {
            throw new IllegalArgumentException("목적지에 같은 팀 말이 있습니다.");
        }
        if (isPawnAttemptKill(source, destination)) {
            validatePawnAttemptKill(destinationPiece);
            return;
        }
        validateExistOtherPiece(positions);
    }

    private boolean isPawnAttemptKill(Position source, Position destination) {
        Piece sourcePiece = board.get(source);
        return sourcePiece.isPawn() && isDiagonal(source, destination);
    }

    private boolean isDiagonal(Position source, Position destination) {
        return Math.abs(source.getRow().getDifference(destination.getRow())) == 1
                && Math.abs(source.getCol().getDifference(destination.getCol())) == 1;
    }

    private void validatePawnAttemptKill(Piece destination) {
        if (destination.isBlank()) {
            throw new IllegalArgumentException("잡을 수 있는 말이 없습니다.");
        }
    }

    private void validateExistOtherPiece(List<Position> positions) {
        for (Position position : positions) {
            validateBlackPiece(position);
        }
    }

    private void validateBlackPiece(Position position) {
        if (!getPiece(position).isBlank()) {
            throw new IllegalArgumentException("해당 위치로 말이 이동할 수 없습니다.");
        }
    }

    private void movePiece(Position source, Position destination) {
        Piece sourcePiece = board.get(source);
        board.put(destination, sourcePiece);
        board.put(source, new Blank(Team.NONE));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    abstract void validateTeam(Piece piece);

    abstract GameState finished();
}
