package chess.domain.board;

import static chess.domain.piece.TeamColor.WHITE;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private final List<Piece> pieces;
    private final TeamColor currentTurnTeamColor;

    private Board(final List<Piece> pieces, final TeamColor currentTurnTeamColor) {
        this.pieces = pieces;
        this.currentTurnTeamColor = currentTurnTeamColor;
    }

    public Board() {
        this(new BoardFactory()
                .generateInitialPieces(), WHITE);
    }

    public boolean hasPieceInPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.matchesPosition(position));
    }

    public Piece findPieceInPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.matchesPosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = findPieceInPosition(sourcePosition);
        validateTurn(sourcePiece);
        final Piece movedPiece = sourcePiece.move(getOtherPieces(sourcePiece), targetPosition);

        if (hasPieceInPosition(targetPosition)) {
            removeTargetPositionPiece(findPieceInPosition(targetPosition), movedPiece);
        }

        pieces.set(pieces.indexOf(sourcePiece), movedPiece);
        return new Board(pieces, currentTurnTeamColor.turnToNext());
    }

    private void validateTurn(final Piece sourcePiece) {
        if (!sourcePiece.isTeamOf(currentTurnTeamColor)) {
            throw new IllegalArgumentException("다른 팀 기물은 이동시킬 수 없습니다.");
        }
    }

    private List<Piece> getOtherPieces(final Piece sourcePiece) {
        return pieces.stream()
                .filter(piece -> !sourcePiece.equals(piece))
                .collect(Collectors.toUnmodifiableList());
    }

    private void removeTargetPositionPiece(final Piece targetPositionPiece, final Piece movedPiece) {
        validateSameTeamTargetPositionPiece(movedPiece, targetPositionPiece);
        pieces.remove(targetPositionPiece);
    }

    private void validateSameTeamTargetPositionPiece(final Piece movedPiece, final Piece targetPositionPiece) {
        if (movedPiece.isSameTeam(targetPositionPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 같은 팀 기물이 있습니다.");
        }
    }

    public boolean hasOneKing() {
        return pieces.stream()
                .filter(Piece::isKing)
                .count() == 1;
    }

    public double getTotalPoint(TeamColor teamColor) {
        final List<Piece> teamPieces = pieces.stream()
                .filter(piece -> piece.isTeamOf(teamColor))
                .collect(Collectors.toUnmodifiableList());
        return TotalScore.getTotalPoint(teamPieces);
    }

    public Board promotePawn(final Position position, final String promotionType) {
        final Piece piece = findPieceInPosition(position);
        validatePromoteCondition(piece);
        pieces.set(pieces.indexOf(piece), piece.promote(promotionType));
        return new Board(pieces, currentTurnTeamColor);
    }

    private void validatePromoteCondition(final Piece piece) {
        if (!piece.canPromote()) {
            throw new IllegalArgumentException("해당 기물은 프로모션 할 수 없습니다.");
        }
    }
}
