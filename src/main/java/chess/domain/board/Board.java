package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.TeamColor.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import chess.game.TotalScore;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final List<Rank> INITIAL_PIECES_RANKS = Arrays.asList(ONE, TWO, SEVEN, EIGHT);

    private final List<Piece> pieces;
    private final TeamColor currentTurnTeamColor;

    private Board(final List<Piece> pieces, final TeamColor currentTurnTeamColor) {
        this.pieces = pieces;
        this.currentTurnTeamColor = currentTurnTeamColor;
    }

    public Board() {
        this.pieces = INITIAL_PIECES_RANKS.stream()
                .map(this::generateOf)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        currentTurnTeamColor = WHITE;
    }

    private List<Piece> generateOf(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Piece.createInitial(file, rank))
                .collect(Collectors.toList());
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
        return new Board(pieces, currentTurnTeamColor.nextTurn());
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

    public boolean hasPromotionPawn(final Position position) {
        final Piece piece = findPieceInPosition(position);
        if (!piece.isPawn()) {
            return false;
        }
        return piece.canPromote();
    }

    public Board promotePawn(final Position position, final String promotionType) {
        final Piece piece = findPieceInPosition(position);
        pieces.set(pieces.indexOf(piece), piece.promote(promotionType));
        return new Board(pieces, currentTurnTeamColor);
    }
}
