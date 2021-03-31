package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.game.ImpossibleMoveException;
import chess.domain.game.PieceNotFoundException;
import chess.controller.dto.PieceDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces() {
        pieces = new ArrayList<>();
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public void move(Position currentPosition, Position targetPosition, TeamColor currentColor) {
        Piece currentPiece = pieceByPosition(currentPosition);
        if (currentPiece.notSameColor(currentColor)) {
            throw new ImpossibleMoveException(currentColor + "팀 차례 입니다.");
        }
        if (isPieceExist(targetPosition)) {
            Piece targetPiece = pieceByPosition(targetPosition);
            pieces.remove(targetPiece);
        }
        currentPiece.move(targetPosition);
        currentPiece.updateMovablePositions(
                existPiecePositions(),
                positionsByTeamColor(currentPiece.enemyColor())
        );
    }

    public void updatePiecesMovablePositions() {
        for (Piece piece : pieces) {
            piece.updateMovablePositions(
                    existPiecePositions(),
                    positionsByTeamColor(piece.enemyColor())
            );
        }
    }

    private boolean isPieceExist(Position targetPosition) {
        try {
            pieceByPosition(targetPosition);
        } catch (PieceNotFoundException e) {
            return false;
        }
        return true;
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
                .reduce(Score.from(0), Score::add)
                .minus(sameColumnPawnScore(teamColor));
    }

    private Score sameColumnPawnScore(final TeamColor teamColor) {
        int pawnCount = sameTeamCountByPiece(teamColor, Piece::isPawn);
        long sameColumnPawnCount = pawnCount - piecesByTeamColor(teamColor).stream()
                .filter(Piece::isPawn)
                .map(Piece::row)
                .distinct()
                .count();
        if(sameColumnPawnCount != 0){
            sameColumnPawnCount += 1;
        }

        return pieces.stream()
                .filter(Piece::isPawn)
                .limit(sameColumnPawnCount)
                .map(piece -> Pawn.halfScore())
                .reduce(Score.from(0), Score::add);
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

    public Piece pieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(PieceNotFoundException::new);
    }

    public Piece kingByColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
                .stream()
                .filter(Piece::isKing)
                .findAny()
                .orElseThrow(PieceNotFoundException::new);
    }

    public List<PieceDto> getPieces() {
        return pieces.stream()
                .map(PieceDto::new)
                .collect(toList());
    }
}
