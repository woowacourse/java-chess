package domain.chessgame;

import domain.Player;
import domain.piece.Blank;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int DEFAULT_KING_COUNT = 2;
    private static final int PAWN_COUNT_SAME_FILE = 2;

    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
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

    public double calculateScoreByPlayer(Player player) {
        return Arrays.stream(File.values())
            .map(file -> calculateTotalScoreInFile(player, file))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private double calculateTotalScoreInFile(Player player, File file) {
        List<Piece> pieces = createPiecesInFile(player, file);
        boolean isSeveralPawn = isSeveralPawnInList(pieces);
        return pieces.stream().map(piece -> piece.score(isSeveralPawn))
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    private List<Piece> createPiecesInFile(Player player, File file) {
        return Arrays.stream(Rank.values())
            .map(rank -> board.get(Position.of(file, rank)))
            .filter(piece -> piece.isSamePlayer(player))
            .collect(Collectors.toList());
    }

    private boolean isSeveralPawnInList(List<Piece> pieces) {
        return pieces.stream()
            .filter(Piece::isPawn)
            .count() >= PAWN_COUNT_SAME_FILE;
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
