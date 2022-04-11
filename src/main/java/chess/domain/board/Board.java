package chess.domain.board;

import static chess.domain.piece.Team.WHITE;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> pieces;
    private final Team currentTurnTeam;

    public Board(final Map<Position, Piece> pieces, final Team currentTurnTeam) {
        this.pieces = pieces;
        this.currentTurnTeam = currentTurnTeam;
    }

    public Board() {
        this(new PieceFactory()
                .generateInitialPieces(), WHITE);
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = pieces.get(sourcePosition);

        validateTurn(sourcePiece);
        validateSameTeamTargetPositionPiece(sourcePiece, targetPosition);
        validateMovement(sourcePosition, targetPosition);

        pieces.remove(sourcePosition);
        pieces.put(targetPosition, sourcePiece);
        return new Board(pieces, currentTurnTeam.turnToNext());
    }

    private void validateMovement(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = pieces.get(sourcePosition);
        if (!sourcePiece.canMove(sourcePosition, targetPosition, getOtherPositions(sourcePosition))) {
            throw new IllegalArgumentException("기물을 이동시킬 수 없습니다.");
        }
    }

    private void validateSameTeamTargetPositionPiece(final Piece sourcePiece, final Position targetPosition) {
        final Piece pieceInTargetPosition = pieces.get(targetPosition);
        if (pieceInTargetPosition == null) {
            return;
        }
        if (sourcePiece.isSameTeam(pieceInTargetPosition)) {
            throw new IllegalArgumentException("이동하려는 위치에 같은 팀 기물이 있습니다.");
        }
    }

    private void validateTurn(final Piece sourcePiece) {
        if (!sourcePiece.isTeamOf(currentTurnTeam)) {
            throw new IllegalArgumentException("다른 팀 기물은 이동시킬 수 없습니다.");
        }
    }

    private List<Position> getOtherPositions(final Position sourcePosition) {
        return pieces.keySet()
                .stream()
                .filter(position -> position != sourcePosition)
                .collect(Collectors.toList());
    }

    public boolean hasOneKing() {
        return pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count() == 1;
    }

    public double getTotalPoint(Team team) {
        final Map<Position, Piece> teamPieces = pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue()
                        .isTeamOf(team))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return TotalScore.getTotalPoint(teamPieces);
    }

    public Board promotePawn(final Position sourcePosition, final String promotionType) {
        final Piece piece = pieces.get(sourcePosition);
        validatePromoteCondition(sourcePosition, piece);
        pieces.put(sourcePosition, piece.promote(promotionType));
        return new Board(pieces, currentTurnTeam);
    }

    private void validatePromoteCondition(final Position position, final Piece piece) {
        if (!piece.canPromote(position)) {
            throw new IllegalArgumentException("해당 기물은 프로모션 할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    public boolean hasPromotionPawnIn(final Position sourcePosition) {
        return pieces.values()
                .stream()
                .anyMatch(piece -> piece.canPromote(sourcePosition));
    }

    public Team getTurn() {
        return currentTurnTeam;
    }
}
