package chess.domain;

import java.util.*;
import java.util.stream.Collectors;

import chess.domain.piece.EmptyPiece;
import chess.exception.SamePositionException;
import chess.domain.piece.Piece;
import chess.exception.UnmovableException;

public class ChessBoard {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 8;

    private final List<Piece> pieces;

    public ChessBoard() {
        pieces = new ArrayList<>();
    }

    public ChessBoard(List<Piece> pieces) {
        this();
        for (Piece piece : pieces) {
            addPiece(piece);
        }
    }

    public void addPiece(Piece newPiece) {
        if (hasSamePosition(newPiece)) {
            throw new SamePositionException();
        }
        pieces.add(newPiece);
    }

    private boolean hasSamePosition(Piece newPiece) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(newPiece));
    }

    public Piece findStartPiece(Player player, Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position) && piece.isMine(player))
                .findFirst()
                .orElseThrow(UnmovableException::new);
    }

    public Piece findEndPiece(Position position) {
        for (Piece piece : pieces) {
            if (piece.isSamePosition(position)) {
                return piece;
            }
        }
        return EmptyPiece.valueOf(Player.EMPTY, position);
    }

    public boolean isMovable(Path path) {
        return path.getPath().stream()
                .noneMatch(this::isExist);
    }

    private boolean isExist(Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    public Score getPlayerScore(Player player) {
        Score totalScore = new Score(0);
        List<Score> scores = pieces.stream()
                .filter(piece -> piece.isMine(player) && !piece.isPawn())
                .map(Piece::getScore)
                .collect(Collectors.toList());

        for (Score score : scores) {
            totalScore = totalScore.add(score);
        }

        totalScore = totalScore.add(getPawnScore(player));
        return totalScore;
    }

    private Score getPawnScore(Player player) {
        Score score = new Score(0);
        List<Piece> pawns = getPawn(player);

        for (int i = 1; i <= 8; i++) {
            score = score.add(getXScore(pawns, i));
        }
        return score;
    }

    private List<Piece> getPawn(Player player) {
        return pieces.stream()
                .filter(piece -> piece.isMine(player) && piece.isPawn())
                .collect(Collectors.toList());
    }

    private Score getXScore(List<Piece> pieces, int x) {
        long count = pieces.stream()
                .filter(piece -> piece.isSameCoordinateX(x))
                .count();

        if (count == 1) {
            return new Score(1);
        }
        return new Score(count * 0.5);
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public List<String> getPieceImages() {
        Map<Position, String> positionPieceImages = makeEmptyImages();
        for (Piece piece : pieces) {
            positionPieceImages.put(piece.getPosition(), piece.getPieceImage());
        }
        return new ArrayList<>(positionPieceImages.values());
    }

    private Map<Position, String> makeEmptyImages() {
        Map<Position, String> positionPieceImages = new LinkedHashMap<>();
        for (int y = MAX_BOUND; y >= MIN_BOUND; y--) {
            positionPieceImages.putAll(makeRowWithEmptyImages(y));
        }

        return positionPieceImages;
    }

    private Map<Position, String> makeRowWithEmptyImages(int y) {
        Map<Position, String> positionPieceImages = new LinkedHashMap<>();
        for (int x = MIN_BOUND; x <= MAX_BOUND; x++) {
            positionPieceImages.put(Position.getPosition(x, y), ChessPieceInfo.EMPTY.getImage());
        }
        return positionPieceImages;
    }
}
