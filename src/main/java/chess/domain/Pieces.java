package chess.domain;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import chess.domain.piece.Piece;
import chess.exception.PieceNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Pieces {

    private final List<Piece> pieces;

    private Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Pieces emptyPieces() {
        return new Pieces(new ArrayList<>());
    }

    public static Pieces from(List<Piece> pieces) {
        return new Pieces(pieces);
    }

    public static Pieces from(Piece... pieces) {
        return new Pieces(Arrays.asList(pieces));
    }

    public void updateMovablePositions() {
        pieces.forEach(piece ->
            piece.updateMovablePositions(positionInformationList())
        );
    }

    private List<PositionInformation> positionInformationList() {
        return pieces.stream()
            .map(Piece::positionInformation)
            .collect(toList());
    }

    public Optional<Piece> pieceByPosition(Position currentPosition) {
        return pieces.stream()
            .filter(piece -> piece.samePosition(currentPosition))
            .findAny();
    }

    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    public List<Piece> piecesByTeamColor(TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(teamColor))
            .collect(toList());
    }

    public Set<Position> attackPositionsByColor(TeamColor teamColor) {
        return piecesByTeamColor(teamColor)
            .stream()
            .flatMap(piece -> piece.movablePositions().stream())
            .collect(toSet());
    }

    public Piece kingByColor(TeamColor teamColor) {
        return pieces.stream()
            .filter(Piece::isKing)
            .filter(piece -> piece.isSameColor(teamColor))
            .findAny()
            .orElseThrow(PieceNotFoundException::new);
    }

    public boolean isKingEmpty(TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(teamColor))
            .noneMatch(Piece::isKing);
    }

    public List<Piece> asList() {
        return pieces;
    }
}
