package domain.chessgame;

import domain.Player;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import utils.PieceScore;

public class ChessBoard {

    private static final String NULL_PIECE_SYMBOL = ".";
    private static final int DEFAULT_KING_COUNT = 2;
    private static final int PAWN_COUNT_SAME_FILE = 2;
    private static final int NOT_FOUND_DUPLICATE_PAWN = 0;

    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    private boolean isPawnAttackDirection(final Direction moveDirection) {
        return moveDirection == Direction.NORTHWEST
            || moveDirection == Direction.NORTHEAST
            || moveDirection == Direction.SOUTHEAST
            || moveDirection == Direction.SOUTHWEST;
    }

    public void move(final Position source, final Position target) {
        validateTargetPiece(source, target);
        validatePawnAttack(source, target);
        validateRoutePositions(source, target);
        movePiece(source, target);
    }

    private void validateTargetPiece(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (!targetPiece.isBlank() && sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 같은 색 기물이 위치하여 이동할 수 없습니다.");
        }
    }

    private void validatePawnAttack(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (sourcePiece.isPawn() && sourcePiece.move(source, target).size() == 0
            && targetPiece.isBlank()) {
            throw new IllegalArgumentException("[ERROR] Pawn은 상대편 말이 있을 경우에만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void validateRoutePositions(final Position source, final Position target) {
        List<Position> positions = board.get(source).move(source, target);
        positions.forEach(this::validateNullPosition);
    }

    private void validateNullPosition(final Position position) {
        if (!board.get(position).isBlank()) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 다른 기물에 의해 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.put(source, new Blank());
    }

    public double calculateScoreByPlayer(final Player player) {
        double sum = 0;
        for (File file : File.values()) {
            List<PieceScore> pieceScores = generatePieceScoreList(player, file);
            sum += calculateFileScore(pieceScores);
        }
        return sum;
    }

    private List<PieceScore> generatePieceScoreList(final Player player, final File file) {
        return Arrays.stream(Rank.values())
            .map(rank -> board.get(Position.of(file, rank)))
            .filter(piece -> piece.isSamePlayer(player))
            .map(piece -> PieceScore.of(piece.symbol()))
            .collect(Collectors.toList());
    }

    private double calculateFileScore(final List<PieceScore> pieceScores) {
        double sum = pieceScores.stream()
            .map(PieceScore::score)
            .mapToDouble(Double::doubleValue)
            .sum();
        return sum - calculatePawnsInFile(pieceScores);
    }

    private double calculatePawnsInFile(final List<PieceScore> pieceScores) {
        long count = pieceScores.stream()
            .filter(pieceScore -> pieceScore.score() == PieceScore.PAWN.score())
            .count();
        if (count >= PAWN_COUNT_SAME_FILE) {
            return count * PieceScore.DUPLICATE_PAWN;
        }
        return NOT_FOUND_DUPLICATE_PAWN;
    }

    public boolean isKingOnlyOne() {
        long kingCount = board.values().stream()
            .filter(Piece::isKing)
            .count();
        return kingCount != DEFAULT_KING_COUNT;
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
