package chess.board;

import chess.piece.EmptyPiece;
import chess.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        PieceFactory.createPiece(piecePosition);
        return new ChessBoard(piecePosition);
    }

    static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public void movePiece(final Position from, final Position to) {
        Piece fromPiece = piecePosition.get(from);
        Piece toPiece = piecePosition.get(to);

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
    }

    private void move(final Position from, final Position to) {
        final Piece piece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, piece);
    }

    private void validateDiagonal(final Position from, final Position to) {
        List<File> files = File.sliceBetween(from.getFile(), to.getFile());
        List<Rank> ranks = Rank.sliceBetween(from.getRank(), to.getRank());

        if (files.size() != ranks.size()) {
            return;
        }

        List<Rank> cutRanks = IntStream.range(1, ranks.size() - 1)
                .mapToObj(ranks::get)
                .collect(Collectors.toList());

        List<File> cutFiles = IntStream.range(1, files.size() - 1)
                .mapToObj(files::get)
                .collect(Collectors.toList());

        if (from.getFile().getIndex() > to.getFile().getIndex()) {
            Collections.reverse(cutFiles);
        }

        if (from.getRank().getIndex() > to.getRank().getIndex()) {
            Collections.reverse(cutRanks);
        }

        final List<Position> collect = IntStream.range(0, cutFiles.size())
                .mapToObj(index -> Position.of(cutFiles.get(index), cutRanks.get(index)))
                .collect(Collectors.toList());

        for (final Position position : collect) {
            validateBlockedRoute(piecePosition.get(position));
        }
    }

    private void validateFileAndRank(final Position from, final Position to) {
        validateSameRank(from, to);
        validateSameFile(from, to);
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
            Piece validationPiece = piecePosition.get(Position.of(from.getFile(), Rank.of(i)));

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
            Piece validationPiece = piecePosition.get(Position.of(File.from(i), from.getRank()));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateBlockedRoute(final Piece validationPiece) {
        if (!validationPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
