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
        Piece sourcePiece = getPiece(Position.from(source));
        Piece destinationPiece = getPiece(Position.from(destination));
        validateMovePiece(sourcePiece, destinationPiece);
        movePiece(sourcePiece, destinationPiece);
        if (destinationPiece.isKing()) {
            return finished();
        }
        return turn();
    }

    private void validateMovePiece(Piece source, Piece destination) {
        validateTeam(source);
        List<Position> positions = source.findPath(destination.getPosition());
        validateMovingPath(source, destination, positions);
    }

    private void validateMovingPath(Piece source, Piece destination, List<Position> positions) {
        if (source.isSameTeam(destination)) {
            throw new IllegalArgumentException("목적지에 같은 팀 말이 있습니다.");
        }
        if (isPawnAttemptKill(source, destination)){
            validatePawnAttemptKill(destination);
            return;
        }
        validateExistOtherPiece(positions);
    }

    private boolean isPawnAttemptKill(Piece source, Piece destination) {
        return source.isPawn() && isDiagonal(source.getPosition(), destination.getPosition());
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

    private void movePiece(Piece sourcePiece, Piece destinationPiece) {
        changePiece(sourcePiece, destinationPiece);
        sourcePiece.move(destinationPiece);
    }

    private void changePiece(Piece sourcePiece, Piece destinationPiece) {
        board.put(destinationPiece.getPosition(), sourcePiece);
        board.put(sourcePiece.getPosition(), new Blank(Team.NONE, sourcePiece.getPosition()));
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
