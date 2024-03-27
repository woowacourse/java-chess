package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;
    private final RemovedPieces removedPieces;

    public Board() {
        Map<Position, Piece> board = new HashMap<>();
        initialize(board);

        this.board = board;
        this.removedPieces = new RemovedPieces();
    }

    private void initialize(final Map<Position, Piece> board) {
        List<Piece> pieces = BoardInitializer.initialize();
        pieces.forEach(piece -> board.put(piece.getPosition(), piece));
    }

    public boolean movePieceAndRenewBoard(final Position source, final Position target) {
        Piece piece = board.get(source);
        Piece movedPiece = movePiece(source, target, piece);
        Position newPosition = movedPiece.getPosition();

        if (target == newPosition) {
            removedPieces.addPiece(board.get(newPosition));
        }
        board.put(source, new EmptyPiece(new PieceInfo(source, Team.NONE)));
        board.put(newPosition, movedPiece);

        return target == newPosition;
    }

    public boolean isSameTeamFromPosition(final Position position, final Team team) {
        Piece piece = board.get(position);

        return piece.isSameTeam(team);
    }

    private Piece movePiece(final Position source, final Position target, final Piece piece) {
        return piece.move(target,
                checkObstacleInRange(source, target),
                checkPieceExist(target),
                checkSameTeamPieceExist(piece.getTeam(), target));
    }

    public boolean isKingRemoved() {
        return removedPieces.isRecentlyRemovedPieceType(PieceType.KING);
    }

    public List<Piece> getPiecesInVertical(final List<Position> positions) {
        return positions.stream()
                .map(board::get)
                .toList();
    }

    boolean checkObstacleInRange(final Position currentPosition, final Position newPosition) {
        List<Position> internalPositions = currentPosition.getInternalPositions(newPosition);

        return internalPositions.stream()
                .map(board::get)
                .anyMatch(piece -> piece.getType() != PieceType.EMPTY);
    }

    boolean checkPieceExist(final Position position) {
        Piece piece = board.get(position);

        return piece.getType() != PieceType.EMPTY;
    }

    boolean checkSameTeamPieceExist(final Team currentTeam, final Position otherPosition) {
        Piece otherPiece = board.get(otherPosition);

        return otherPiece.isSameTeam(currentTeam);
    }

    void placePiece(final Position currentPosition, final Piece piece) {
        board.put(currentPosition, piece);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
