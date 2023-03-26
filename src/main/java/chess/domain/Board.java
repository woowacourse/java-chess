package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.NoneEmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.domain.position.RelativePosition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.PAWN;

public final class Board implements BoardProvider {

    private static final int LINE_SIZE = 8;

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePiece(final Position from, final Position to) {
        validateSourceEmpty(from);
        validateObstacleInPath(from, to);

        NoneEmptyPiece source = (NoneEmptyPiece) board.get(from);
        Piece target = board.get(to);
        if (source.isMobile(RelativePosition.of(from, to), target)) {
            board.replace(to, source);
            board.replace(from, new EmptyPiece());
            checkPromotion(to, source);
        }
    }

    private void checkPromotion(final Position position, final Piece piece) {
        if (!piece.isPieceType(PAWN)) {
            return;
        }
        Piece promotionResult = ((Pawn) piece).getPromotionResult(position);
        if (!promotionResult.isPieceType(PAWN)) {
            board.replace(position, promotionResult);
        }
    }

    private void validateSourceEmpty(final Position from) {
        if (board.get(from).isEmpty()) {
            throw new IllegalArgumentException("조작할 수 있는 말이 없습니다.");
        }
    }

    private void validateObstacleInPath(final Position from, final Position to) {
        NoneEmptyPiece source = (NoneEmptyPiece) board.get(from);
        List<Position> obstaclePositionsInPath = source.getObstacleCheckingPositions(from, to);

        if (hasObstacle(obstaclePositionsInPath)) {
            throw new IllegalArgumentException("해당 경로의 다른 말을 건너뛸 수 없습니다.");
        }
    }

    private boolean hasObstacle(final List<Position> obstaclePositionsInPath) {
        return obstaclePositionsInPath.stream()
                .anyMatch(position -> !board.get(position).isEmpty());
    }

    public boolean isKingDead(final Team team) {
        return board.values()
                .stream()
                .noneMatch(piece -> piece.isPieceType(KING) && piece.isTeam(team));
    }

    public boolean isTeamInPositionMatched(final Position position, final Team expected) {
        return board.get(position).isTeam(expected);
    }

    private long countPawnsInSameColumn(final int column, final Team team) {
        long pawnCount = board.keySet()
                .stream()
                .filter(position -> position.isColumn(column))
                .map(board::get)
                .filter(piece -> piece.isPieceType(PAWN) && piece.isTeam(team))
                .count();

        if (pawnCount > 1) {
            return pawnCount;
        }
        return 0;
    }

    public List<Double> getScores(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isTeam(team))
                .map(Piece::getScore)
                .collect(Collectors.toList());
    }

    public double getMinusScore(final Team team) {
        long pawnCount = 0;
        for (int i = 0; i < LINE_SIZE; i++) {
            pawnCount += countPawnsInSameColumn(i, team);
        }
        return pawnCount * 0.5;
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    @Override
    public List<Piece> getPieces() {
        return List.copyOf(board.values());
    }

    @Override
    public int getLineSize() {
        return LINE_SIZE;
    }
}
