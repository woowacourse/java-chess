package chess.domain.state;

import chess.domain.*;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public abstract class Playing implements GameState {

    private Map<Row, Rank> board;

    public Playing(Map<Row, Rank> board) {
        this.board = board;
    }

    public Piece getPiece(Position position) {
        return board.get(position.getRow()).getPiece(position.getCol());
    }

    abstract Playing turn();

    public GameState move(String source, String destination) {
        Piece sourcePiece = getPiece(Position.from(source));
        Piece destinationPiece = getPiece(Position.from(destination));
        validateMovePiece(sourcePiece, destinationPiece);
        movePiece(source, destination, sourcePiece);
        if (destinationPiece.isKing()) {
            return finished();
        }
        return turn();
    }

    private void movePiece(String source, String destination, Piece sourcePiece) {
        sourcePiece.move(Position.from(destination));
        changePiece(Position.from(source), Position.from(destination), sourcePiece);
    }

    private void validateMovePiece(Piece source, Piece destination) {
        validateTeam(source);
        List<Position> positions = source.findPath(destination.getPosition());
        validateMovingPath(source, destination, positions);
    }

    private void validateMovingPath(Piece source, Piece destination, List<Position> positions) {
        if (source.isSameTeam(destination)) {
            throw new IllegalArgumentException("같은 팀은 kill 할 수 없습니다.");
        }
        if (source.isPawn() && isDiagonal(source.getPosition(), destination.getPosition())){
            validatePawnAttemptKill(destination);
            return;
        }
        validateExistOtherPiece(positions);
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
            if (!getPiece(position).isBlank()) {
                throw new IllegalArgumentException("해당 위치로 말이 이동할 수 없습니다.");
            }
        }
    }

    abstract void validateTeam(Piece piece);

    private void changePiece(Position source, Position destination, Piece piece) {
        board.get(source.getRow()).changePiece(source.getCol(), new Blank(Team.NONE, source));
        board.get(destination.getRow()).changePiece(destination.getCol(), piece);
    }

    public Map<Row, Rank> getBoard() {
        return board;
    }

    abstract GameState finished();

    @Override
    public boolean isFinished() {
        return false;
    }
}
