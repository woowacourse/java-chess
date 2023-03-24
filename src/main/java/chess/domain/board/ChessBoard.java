package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.exception.PieceCannotMoveException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final BigDecimal PAWN_REDUCE_NUMBER = BigDecimal.valueOf(0.5);

    private final Map<Position, Piece> piecePosition;

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = PieceFactory.createPiece();
        return new ChessBoard(piecePosition);
    }

    public static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public Piece movePiece(final Position from, final Position to) {
        Piece fromPiece = piecePosition.get(from);
        Piece toPiece = piecePosition.get(to);

        validateMovable(from, to);
        validateAlly(fromPiece, toPiece);
        validateRoute(from, to);

        move(from, to);
        return toPiece;
    }

    private void move(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, fromPiece);
    }

    private void validateMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        if (!fromPiece.isMovable(from, to)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
    }

    private void validateAlly(final Piece fromPiece, final Piece toPiece) {
        if (fromPiece.getTeam() == toPiece.getTeam()) {
            throw new IllegalArgumentException("도착지에 동일한 팀의 말이 존재합니다");
        }
    }

    private void validateRoute(final Position from, final Position to) {
        RouteFinder.findRoute(from, to).stream()
                .filter(position -> !piecePosition.get(position).isEmpty())
                .forEach(position -> {
                    throw new IllegalArgumentException("이동하려는 경로에 말이 존재합니다.");
                });
    }

    public BigDecimal calculateScore(final Team team) {
        BigDecimal ordinalScore = getOrdinalScore(team);
        BigDecimal pawnDifferenceScore = getPawnSubScore(team);
        return ordinalScore.subtract(pawnDifferenceScore);
    }

    private BigDecimal getPawnSubScore(final Team team) {

        List<Position> pawnPositions = toPawnPositions(team);
        return calculatePawnSubScore(pawnPositions);
    }

    private BigDecimal calculatePawnSubScore(final List<Position> pawnPositions) {
        BigDecimal sum = getReduceNumber(pawnPositions);

        if (sum.equals(PAWN_REDUCE_NUMBER)) {
            return BigDecimal.ZERO;
        }

        return sum;
    }

    private BigDecimal getReduceNumber(final List<Position> pawnPositions) {
        BigDecimal sum = PAWN_REDUCE_NUMBER;

        List<File> files = pawnPositions.stream()
                .map(Position::getFile)
                .collect(Collectors.toList());

        for (final File file : files) {
            if (Collections.frequency(files, file) != 1) {
                sum = sum.add(sum);
            }
        }
        return sum;
    }

    private List<Position> toPawnPositions(Team team) {
        return Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values())
                        .map(rank -> Position.of(file, rank))
                ).filter(position -> piecePosition.get(position).getType() == PieceType.PAWN)
                .filter(position -> piecePosition.get(position).getTeam() == team)
                .collect(Collectors.toList());
    }

    private BigDecimal getOrdinalScore(final Team team) {
        return piecePosition.values().stream()
                .filter(piece -> piece.getTeam() == team)
                .map(piece -> piece.getType().getScore())
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalStateException(team + "은 점수를 계산할 수 없습니다."));
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }

    public Piece get(final Position from) {
        return piecePosition.get(from);
    }
}
