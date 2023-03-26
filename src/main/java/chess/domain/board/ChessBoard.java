package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.board.position.RouteFinder;
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
    private static final int DEFAULT_KING_COUNT = 2;

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

    private void validateMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        if (!fromPiece.isMovable(from, to)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
        if (fromPiece.getType() == PieceType.PAWN) {
            validatePawnMovable(from, to);
        }
    }

    private void validatePawnMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        final Piece toPiece = piecePosition.get(to);

        Team fromPieceTeam = fromPiece.getTeam();

        if (isDiagonal(from, to) && !fromPieceTeam.isEnemy(toPiece.getTeam())) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }

        if (!isDiagonal(from, to) && !toPiece.isEmpty()) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
    }

    private boolean isDiagonal(final Position from, final Position to) {

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());

        return fileInterval == rankInterval;
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

    private void move(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, fromPiece);
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
        BigDecimal result = BigDecimal.ZERO;

        List<File> files = pawnPositions.stream()
                .map(Position::getFile)
                .collect(Collectors.toList());

        for (final File file : File.values()) {
            int pawnCount = Collections.frequency(files, file);
            if (pawnCount > 1) {
                result = result.add(PAWN_REDUCE_NUMBER.multiply(BigDecimal.valueOf(pawnCount)));
            }
        }

        return result;
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

    public boolean isEnd() {
        return piecePosition.values().stream()
                .filter(piece -> piece.getType() == PieceType.KING)
                .count() < DEFAULT_KING_COUNT;
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }

    public Piece get(final Position from) {
        return piecePosition.get(from);
    }
}
