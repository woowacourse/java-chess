package domain.chessgame;

import domain.Player;
import domain.direction.Direction;
import domain.piece.Blank;
import domain.piece.Piece;
import domain.piece.PieceSymbol;
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
        return moveDirection.equals(Direction.NORTHWEST)
            || moveDirection.equals(Direction.NORTHEAST)
            || moveDirection.equals(Direction.SOUTHEAST)
            || moveDirection.equals(Direction.SOUTHWEST);
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        sourcePiece.generateAvailablePosition(source);
        validateTargetPiece(source, target);
        validatePawnAttack(sourcePiece, target);
        validateRoutePositions(source, target);
        movePiece(source, target);
    }

    private void validatePawnAttack(Piece sourcePiece, Position target) {
        Piece targetPiece = board.get(target);
        Direction moveDirection = sourcePiece.findDirection(target);
        if (sourcePiece.isPawn() && isPawnAttackDirection(moveDirection) && targetPiece.isBlank()) {
            throw new IllegalArgumentException("[ERROR] Pawn은 상대편 말이 있을 경우에만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void validateRoutePositions(Position source, Position target) {
        List<Position> positions = board.get(source).getAvailablePositions(source, target);
        positions.forEach(this::validateNullPosition);
    }

    private void validateTargetPiece(final Position source, final Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        if (!targetPiece.isBlank() && sourcePiece.isSamePlayer(targetPiece)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 같은 색 기물이 위치하여 이동할 수 없습니다.");
        }
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

    public double calculateScoreByPlayer(Player player) {
        double sum = 0;
        for (File file : File.values()) {
            List<PieceScore> pieceScores = generatePieceScoreList(player, file);
            sum += calculateFileScore(pieceScores);
        }
        return sum;
    }

    private List<PieceScore> generatePieceScoreList(Player player, File file) {
        return Arrays.stream(Rank.values())
            .map(rank -> board.get(Position.of(file, rank)))
            .filter(piece -> piece.isSamePlayer(player))
            .map(piece -> PieceScore.of(piece.symbol()))
            .collect(Collectors.toList());
    }

    private double calculateFileScore(List<PieceScore> pieceScores) {
        double sum = pieceScores.stream()
            .map(PieceScore::score)
            .mapToDouble(Double::doubleValue)
            .sum();
        return sum - calculatePawnsInFile(pieceScores);
    }

    private double calculatePawnsInFile(List<PieceScore> pieceScores) {
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
            .filter(piece -> piece.symbol().equals(PieceSymbol.KING.symbol()))
            .count();
        return kingCount != DEFAULT_KING_COUNT;
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
