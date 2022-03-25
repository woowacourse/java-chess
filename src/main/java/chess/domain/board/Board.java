package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final List<Rank> INITIAL_PIECES_RANKS = Arrays.asList(ONE, TWO, SEVEN, EIGHT);

    private final List<Piece> pieces;

    private Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Board() {
        this.pieces = INITIAL_PIECES_RANKS.stream()
                .map(this::generateOf)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<Piece> generateOf(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Piece.create(file, rank))
                .collect(Collectors.toList());
    }

    public boolean hasPieceInPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.hasPosition(position));
    }

    public Piece findPieceInPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.hasPosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 없습니다."));
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Piece sourcePiece = findPieceInPosition(sourcePosition);
        final List<Piece> otherPieces = getOtherPieces(sourcePiece);
        final Piece movedPiece = sourcePiece.move(otherPieces, targetPosition);

        if (hasPieceInPosition(targetPosition)) {
            removeTargetPositionPiece(findPieceInPosition(targetPosition), movedPiece);
        }

        pieces.set(pieces.indexOf(sourcePiece), movedPiece);
        return new Board(pieces);
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
}
