package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.TWO;
import static chess.domain.piece.vo.TeamColor.WHITE;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.vo.TeamColor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static final List<Rank> INITIAL_PIECES_RANKS = Arrays.asList(ONE, TWO, SEVEN, EIGHT);

    private final List<Piece> pieces;
    private TeamColor currentTurnTeamColor;

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
        validateTurn(sourcePiece);
        final List<Piece> otherPieces = getOtherPieces(sourcePiece);
        final Piece movedPiece = sourcePiece.move(otherPieces, targetPosition);

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

    public boolean isCheckMate() {
        return pieces.stream()
                .filter(piece -> piece.isTypeOf(King.class))
                .count() != 2;
    }

    public double getTotalPoint(TeamColor teamColor) {
        double totalPoint = pieces.stream()
                .filter(piece -> piece.isTeamOf(teamColor))
                .mapToDouble(Piece::getScore)
                .sum();
        final List<Pawn> pawns = findPawns(teamColor);

        for (File file : File.values()) {
            totalPoint -= countSameFilePawn(pawns, file) * 0.5;
        }
        return totalPoint;
    }

    private int countSameFilePawn(final List<Pawn> pawns, final File file) {
        final int sameFilePawnCount = (int) pawns.stream()
                .map(Pawn.class::cast)
                .filter(pawn -> pawn.isInFile(file))
                .count();
        if (sameFilePawnCount > 1) {
            return sameFilePawnCount;
        }
        return 0;
    }

    private List<Pawn> findPawns(final TeamColor teamColor) {
        return pieces.stream()
                .filter(piece -> piece.isTeamOf(teamColor))
                .filter(piece -> piece.isTypeOf(Pawn.class))
                .map(Pawn.class::cast)
                .collect(Collectors.toList());
    }
}
