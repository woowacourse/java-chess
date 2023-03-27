package chess.model.board;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.piece.score.PieceRuleScore;
import chess.model.piece.score.PieceScore;
import chess.model.piece.PieceType;
import chess.model.position.Distance;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.RankPosition;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final long MULTIPLE_PAWN_COUNT = 2L;

    private final Map<Position, Piece> board;

    ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position source, final Position target, final Camp camp) {
        final Piece sourcePiece = board.get(source);

        validateSource(sourcePiece, camp);
        validateTotalWayPoint(source, target);

        final Piece targetPiece = board.get(target);
        final Distance distance = target.differ(source);

        validateMovable(sourcePiece, targetPiece, distance);
        updateBoard(source, sourcePiece, target);
    }

    private void validateSource(final Piece sourcePiece, final Camp camp) {
        if (!sourcePiece.isSameTeam(camp)) {
            throw new IllegalArgumentException("게임을 진행하는 플레이어의 기물이 아닙니다.");
        }
    }

    private void validateTotalWayPoint(final Position source, final Position target) {
        final Distance distance = target.differ(source);
        final Direction direction = distance.findDirection();

        findWayPoint(source, target, direction);
    }

    private void findWayPoint(final Position source, final Position target, final Direction direction) {
        Position wayPoint = source.findNextPosition(direction);
        source.findNextPosition(direction);

        while (!wayPoint.equals(target)) {
            validateTargetWayPoint(wayPoint);
            wayPoint = wayPoint.findNextPosition(direction);
        }
    }

    private void validateTargetWayPoint(final Position wayPoint) {
        final Piece wayPointPiece = board.get(wayPoint);

        if (wayPointPiece.isNotPassable()) {
            throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
        }
    }

    private void validateMovable(final Piece sourcePiece, final Piece targetPiece, final Distance distance) {
        if (!sourcePiece.movable(distance, targetPiece)) {
            throw new IllegalArgumentException("해당 기물은 지정한 목적지로 움직일 수 없습니다.");
        }
    }

    private void updateBoard(final Position source, final Piece sourcePiece, final Position target) {
        board.put(target, sourcePiece.pick());
        board.put(source, Piece.EMPTY);
    }

    public boolean canPlayGame(final Camp camp) {
        return board.keySet().stream()
                .anyMatch(position -> isAliveKing(position, camp));
    }

    private boolean isAliveKing(final Position position, final Camp camp) {
        final Piece piece = board.get(position);

        return piece.isSameTeam(camp) && piece.isSameType(PieceType.KING);
    }

    public PieceScore calculateScoreByCamp(final Camp camp) {
        final List<PieceScore> campPieceScore = convertToPieceScoreByCamp(camp);
        final PieceScore totalPieceScore = calculateTotalPieceScore(campPieceScore);
        final PieceScore pawnOffsetScore = calculateTotalPawnOffsetScore(camp);

        return totalPieceScore.minus(pawnOffsetScore);
    }

    private List<PieceScore> convertToPieceScoreByCamp(final Camp camp) {
        return board.keySet().stream()
                .map(board::get)
                .filter(piece -> piece.isSameTeam(camp))
                .map(Piece::score)
                .collect(Collectors.toList());
    }

    private PieceScore calculateTotalPieceScore(final List<PieceScore> campPieceScore) {
        return campPieceScore.stream()
                .reduce(PieceRuleScore.ZERO.score(), PieceScore::plus);
    }

    private PieceScore calculateTotalPawnOffsetScore(final Camp camp) {
        return Arrays.stream(File.values())
                .map(file -> calculatePawnOffsetScoreByFile(file, camp))
                .reduce(PieceRuleScore.ZERO.score(), PieceScore::plus);
    }

    private PieceScore calculatePawnOffsetScoreByFile(final File file, final Camp camp) {
        final RankPosition rankPosition = Position.findRankPositionByFile(file);
        final long count = countPawn(camp, rankPosition);

        return calculatePawnOffsetScoreByFilePawnCount(count);
    }

    private long countPawn(final Camp camp, final RankPosition rankPosition) {
        return rankPosition.getPositions().stream()
                .map(board::get)
                .filter(piece -> piece.isSameType(PieceType.PAWN) || piece.isSameType(PieceType.INITIAL_PAWN))
                .filter(piece -> piece.isSameTeam(camp))
                .count();
    }

    private PieceScore calculatePawnOffsetScoreByFilePawnCount(final long count) {
        if (count < MULTIPLE_PAWN_COUNT) {
            return PieceRuleScore.ZERO.score();
        }

        PieceScore pawnOffsetScore = PieceRuleScore.ZERO.score();

        for (int i = 0; i < count; i++) {
            pawnOffsetScore = pawnOffsetScore.plus(PieceRuleScore.PAWN_OFFSET.score());
        }
        return pawnOffsetScore;
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
