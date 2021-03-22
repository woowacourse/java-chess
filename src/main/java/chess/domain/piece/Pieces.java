package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces() {
        pieces = new ArrayList<>();
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    public void updateMovablePositions() {
        pieces.forEach(piece -> piece.updateMovablePositions(
                existPiecePositions(),
                positionsByTeamColor(piece.enemyColor())
                )
        );
    }

    private List<Position> existPiecePositions() {
        return pieces.stream()
                .map(Piece::currentPosition)
                .collect(Collectors.toList());
    }

    public List<Piece> piecesByTeamColor(final TeamColor teamColor) {
        return pieces.stream()
                .filter(piece -> piece.sameColor(teamColor))
                .collect(Collectors.toList());
    }

    private List<Position> positionsByTeamColor(final TeamColor teamColor) {
        return piecesByTeamColor(teamColor).stream()
                .map(Piece::currentPosition)
                .collect(Collectors.toList());
    }

    public Score totalScoreByTeamColor(final TeamColor teamColor) {
        return piecesByTeamColor(teamColor).stream()
                .map(Piece::score)
                .reduce(Score.from(0), Score::addedScore)
                .minus(sameColumnPawnScore(teamColor));
    }

    private Score sameColumnPawnScore(final TeamColor teamColor) {
        int pawnCount = sameTeamCountByPiece(teamColor, Piece::isPawn);
        long sameColumnPawnCount = pawnCount - pieces.stream()
                .filter(Piece::isPawn)
                .map(Piece::row)
                .distinct()
                .count();

        return pieces.stream()
                .filter(Piece::isPawn)
                .limit(sameColumnPawnCount)
                .map(piece -> Pawn.halfScore())
                .reduce(Score.from(0), Score::addedScore);
    }

    private int sameTeamCountByPiece(final TeamColor teamColor, final Predicate<Piece> piecePredicate) {
        return (int) piecesByTeamColor(teamColor).stream()
                .filter(piecePredicate)
                .count();
    }

    public boolean isKingDead(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
                .stream()
                .noneMatch(Piece::isKing);
    }

    public Set<Position> attackPositions(TeamColor teamColor) {
        return piecesByTeamColor(teamColor).stream()
                .flatMap(piece -> piece.movablePositions().stream())
                .collect(Collectors.toSet());
    }

    public Optional<Piece> kingByColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
                .stream()
                .filter(Piece::isKing)
                .findAny();
    }

    public Map<Position, String> nameGroupingByPosition() {
        return pieces.stream()
                .collect(toMap(
                        Piece::currentPosition,
                        Piece::name
                ));
    }

    public Optional<Piece> pieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny();
    }
}
