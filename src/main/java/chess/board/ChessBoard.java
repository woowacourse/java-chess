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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO : 킹, 룩 이동 못하는거 검증 구현 완료 했으니..
// TODO : 나머지 말들이 이동 못하는거 검증 구현하고 테스트 만들기.
// TODO : 말들이 Piece를 인자로 받아서 동일한 색깔이면 false 반환하는건?

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    // TODO : EMPTY CHESSBOARD 반환하는 팩토리 메소드 만들기

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        initPosition(piecePosition);
        for (Team team : Team.values()) {
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

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    private static void initPosition(final Map<Position, Piece> piecePosition) {
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                piecePosition.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    public void movePiece(Position from, Position to) {
        Piece fromPiece = piecePosition.get(from);
        Piece toPiece = piecePosition.get(to);

        if (fromPiece.isRook() && fromPiece.isMovable(from, to, toPiece)) {
            validateRook(from, to, fromPiece);
            move(from, to);
        }

        if (fromPiece.isBishop() && fromPiece.isMovable(from, to, toPiece)) {
            validateBishop(from, to);
            move(from, to);
        }

        if (fromPiece.isQueen() && fromPiece.isMovable(from, to, toPiece)) {
            validateRook(from, to, fromPiece);
            validateBishop(from, to);
            move(from, to);
        }

        if (fromPiece.isPawn() && fromPiece.isMovable(from, to, toPiece)) {
            validateSameRank(from, to, fromPiece);
            move(from, to);
        }

        if (fromPiece.isKing() && fromPiece.isMovable(from, to, toPiece)) {
            move(from, to);
        }
        if (fromPiece.isKnight() && fromPiece.isMovable(from, to, toPiece)) {
            move(from, to);
        }
    }

    private void validateRook(final Position from, final Position to, final Piece fromPiece) {
        validateSameRank(from, to, fromPiece);
        validateSameFile(from, to, fromPiece);
    }

    private void validateSameFile(final Position from, final Position to, final Piece fromPiece) {
        if (from.getFile() == to.getFile()) {
            validateRookByRank(from, to, fromPiece);
        }
    }

    private void validateSameRank(final Position from, final Position to, final Piece fromPiece) {
        if (from.getRank() == to.getRank()) {
            validateRookByFile(from, to, fromPiece);
        }
    }

    private void validateBishop(Position from, Position to) {
        List<File> files = File.getBetween(from.getFile(), to.getFile());
        List<Rank> ranks = Rank.getBetween(from.getRank(), to.getRank());

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

        List<Position> collect = IntStream.range(0, cutFiles.size())
                .mapToObj(index -> new Position(cutFiles.get(index), cutRanks.get(index)))
                .collect(Collectors.toList());

        for (final Position position : collect) {
            if (!piecePosition.get(position).isEmpty()) {
                throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
            }
        }
    }

    private void move(final Position from, final Position to) {
        Piece piece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, piece);
    }

    private void validateRookByFile(final Position from, final Position to, final Piece fromPiece) {
        File fromFile = from.getFile();
        File toFile = to.getFile();
        int min = Math.min(fromFile.getIndex(), toFile.getIndex()) + 1;
        int max = Math.max(fromFile.getIndex(), toFile.getIndex()) - 1;

        for (int i = min; i < max; i++) {
            Piece validationPiece = piecePosition.get(new Position(File.of(i), from.getRank()));
            if (!validationPiece.isEmpty()) {
                throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
            }
        }
    }

    private void validateRookByRank(final Position from, final Position to, final Piece piece) {
        Rank fromRank = from.getRank();
        Rank toRank = to.getRank();
        int min = Math.min(fromRank.getIndex(), toRank.getIndex()) + 1;
        int max = Math.max(fromRank.getIndex(), toRank.getIndex()) - 1;

        for (int i = min; i < max; i++) {
            Piece validationPiece = piecePosition.get(new Position(from.getFile(), Rank.of(i)));

            if (!validationPiece.isEmpty()) {
                throw new IllegalArgumentException("말이 이동경로에 존재하여 이동할 수 없습니다.");
            }
        }
    }

    // === createBoard ===
    private static void createPawn(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            for (File file : File.values()) {
                piecePosition.put(new Position(file, Rank.TWO), new Pawn(team));
            }
        } else {
            for (File file : File.values()) {
                piecePosition.put(new Position(file, Rank.SEVEN), new Pawn(team));
            }
        }
    }

    private static void createRook(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.A, Rank.ONE), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.ONE), new Rook(team));
        } else {
            piecePosition.put(new Position(File.A, Rank.EIGHT), new Rook(team));
            piecePosition.put(new Position(File.H, Rank.EIGHT), new Rook(team));
        }

    }

    private static void createKnight(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.B, Rank.ONE), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.ONE), new Knight(team));
        } else {
            piecePosition.put(new Position(File.B, Rank.EIGHT), new Knight(team));
            piecePosition.put(new Position(File.G, Rank.EIGHT), new Knight(team));
        }

    }

    private static void createBishop(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.C, Rank.ONE), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.ONE), new Bishop(team));
        } else {
            piecePosition.put(new Position(File.C, Rank.EIGHT), new Bishop(team));
            piecePosition.put(new Position(File.F, Rank.EIGHT), new Bishop(team));
        }
    }

    private static void createQueen(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.D, Rank.ONE), new Queen(team));
        } else {
            piecePosition.put(new Position(File.D, Rank.EIGHT), new Queen(team));
        }
    }

    private static void createKing(final Map<Position, Piece> piecePosition, final Team team) {
        if (team == Team.WHITE) {
            piecePosition.put(new Position(File.E, Rank.ONE), new King(team));
        } else {
            piecePosition.put(new Position(File.E, Rank.EIGHT), new King(team));
        }
    }

    private boolean isSameTeam(final Piece originPiece, final Piece validatePiece) {
        return (originPiece.isBlack() && validatePiece.isBlack()) ||
                (originPiece.isWhite() && validatePiece.isWhite());
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }
}
