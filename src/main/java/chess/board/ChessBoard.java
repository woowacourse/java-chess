package chess.board;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    public ChessBoard() {
        this.piecePosition = PieceFactory.createPiece();
    }

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public void movePiece(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        final Piece toPiece = piecePosition.get(to);

        if (fromPiece.isRook() && fromPiece.isMovable(from, to, toPiece)) {
            validateFileAndRank(from, to);
            move(from, to);
        }

        if (fromPiece.isBishop() && fromPiece.isMovable(from, to, toPiece)) {
            validateDiagonal(from, to);
            move(from, to);
        }

        if (fromPiece.isQueen() && fromPiece.isMovable(from, to, toPiece)) {
            validateFileAndRank(from, to);
            validateDiagonal(from, to);
            move(from, to);
        }

        if (fromPiece.isPawn() && fromPiece.isMovable(from, to, toPiece)) {
            validateRanks(from, to);
            move(from, to);
        }

        if (fromPiece.isKing() && fromPiece.isMovable(from, to, toPiece)) {
            move(from, to);
        }

        if (fromPiece.isKnight() && fromPiece.isMovable(from, to, toPiece)) {
            move(from, to);
        }

        if (fromPiece.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에는 움직일 수 있는 기물이 없습니다.");
        }
    }

    private void move(final Position from, final Position to) {
        final Piece piece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, piece);
    }

    private void validateDiagonal(final Position from, final Position to) {
        final List<File> files = File.sliceBetween(from.getFile(), to.getFile());
        final List<Rank> ranks = Rank.sliceBetween(from.getRank(), to.getRank());

        if (files.size() != ranks.size()) {
            return;
        }

        for (final Position position : makeDiagonal(sliceFile(from, to, files), sliceRank(from, to, ranks))) {
            validateBlockedRoute(piecePosition.get(position));
        }
    }

    private static List<Position> makeDiagonal(final List<File> slicedFiles, final List<Rank> slicedRanks) {
        return IntStream.range(0, slicedFiles.size())
                .mapToObj(index -> new Position(slicedFiles.get(index), slicedRanks.get(index)))
                .collect(Collectors.toList());
    }

    private List<File> sliceFile(final Position from, final Position to, final List<File> files) {
        final List<File> slicedFiles = IntStream.range(1, files.size() - 1)
                .mapToObj(files::get)
                .collect(Collectors.toList());
        if (from.getFile().getIndex() > to.getFile().getIndex()) {
            Collections.reverse(slicedFiles);
        }
        return slicedFiles;
    }

    private List<Rank> sliceRank(final Position from, final Position to, final List<Rank> ranks) {
        final List<Rank> slicedRanks = IntStream.range(1, ranks.size() - 1)
                .mapToObj(ranks::get)
                .collect(Collectors.toList());
        if (from.getRank().getIndex() > to.getRank().getIndex()) {
            Collections.reverse(slicedRanks);
        }
        return slicedRanks;
    }

    private void validateFileAndRank(final Position from, final Position to) {
        validateSameFile(from, to);
        validateSameRank(from, to);
    }

    private void validateSameFile(final Position from, final Position to) {
        if (from.getFile() == to.getFile()) {
            validateRanks(from, to);
        }
    }

    private void validateRanks(final Position from, final Position to) {
        final Rank fromRank = from.getRank();
        final Rank toRank = to.getRank();
        final int min = Math.min(fromRank.getIndex(), toRank.getIndex()) + 1;
        final int max = Math.max(fromRank.getIndex(), toRank.getIndex()) - 1;

        for (int i = min; i <= max; i++) {
            final Piece validationPiece = piecePosition.get(new Position(from.getFile(), Rank.from(i)));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateSameRank(final Position from, final Position to) {
        if (from.getRank() == to.getRank()) {
            validateFiles(from, to);
        }
    }

    private void validateFiles(final Position from, final Position to) {
        final File fromFile = from.getFile();
        final File toFile = to.getFile();
        final int min = Math.min(fromFile.getIndex(), toFile.getIndex()) + 1;
        final int max = Math.max(fromFile.getIndex(), toFile.getIndex()) - 1;

        for (int i = min; i <= max; i++) {
            final Piece validationPiece = piecePosition.get(new Position(File.from(i), from.getRank()));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateBlockedRoute(final Piece validationPiece) {
        if (!validationPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }
    }

    public boolean isGameOver() {
        final long numberOfKing = piecePosition.values()
                .stream()
                .filter(Piece::isKing)
                .count();
        return numberOfKing != 2;
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
