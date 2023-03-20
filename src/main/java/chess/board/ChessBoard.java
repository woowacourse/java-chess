package chess.board;

import chess.piece.Bishop;
import chess.piece.EmptyPiece;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        initPosition(piecePosition);
        for (final Team team : Team.values()) {
            createPawn(piecePosition, team);
            createRook(piecePosition, team);
            createKnight(piecePosition, team);
            createBishop(piecePosition, team);
            createQueen(piecePosition, team);
            createKing(piecePosition, team);
        }
        return new ChessBoard(piecePosition);
    }

    static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    private static void initPosition(final Map<Position, Piece> piecePosition) {
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                piecePosition.put(new Position(file, rank), new EmptyPiece());
            }
        }
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

        final List<Rank> cutRanks = IntStream.range(1, ranks.size() - 1)
                .mapToObj(ranks::get)
                .collect(Collectors.toList());

        final List<File> cutFiles = IntStream.range(1, files.size() - 1)
                .mapToObj(files::get)
                .collect(Collectors.toList());

        if (from.getFile().getIndex() > to.getFile().getIndex()) {
            Collections.reverse(cutFiles);
        }

        if (from.getRank().getIndex() > to.getRank().getIndex()) {
            Collections.reverse(cutRanks);
        }

        final List<Position> collect = IntStream.range(0, cutFiles.size())
                .mapToObj(index -> new Position(cutFiles.get(index), cutRanks.get(index)))
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
            final Piece validationPiece = piecePosition.get(new Position(from.getFile(), Rank.of(i)));

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
            final Piece validationPiece = piecePosition.get(new Position(File.of(i), from.getRank()));
            validateBlockedRoute(validationPiece);
        }
    }

    private void validateBlockedRoute(final Piece validationPiece) {
        if (!validationPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
        }
    }

    private static void createPawn(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            Arrays.stream(File.values())
                    .forEach(file -> piecePosition.put(new Position(file, Rank.TWO), new Pawn(team)));
        }
        if (team == Team.BLACK) {
            Arrays.stream(File.values())
                    .forEach(file -> piecePosition.put(new Position(file, Rank.SEVEN), new Pawn(team)));
        }
    }

    private static void createRook(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.A, Rank.ONE), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.ONE), new Rook(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.A, Rank.EIGHT), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.EIGHT), new Rook(team));
        }

    }

    private static void createKnight(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.B, Rank.ONE), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.ONE), new Knight(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.B, Rank.EIGHT), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.EIGHT), new Knight(team));
        }

    }

    private static void createBishop(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.C, Rank.ONE), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.ONE), new Bishop(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.C, Rank.EIGHT), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.EIGHT), new Bishop(team));
        }
    }

    private static void createQueen(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.D, Rank.ONE), new Queen(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.D, Rank.EIGHT), new Queen(team));
        }
    }

    private static void createKing(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.E, Rank.ONE), new King(team));
        }
        if (team == Team.BLACK) {
            piecePosition.put(new Position(File.E, Rank.EIGHT), new King(team));
        }
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
