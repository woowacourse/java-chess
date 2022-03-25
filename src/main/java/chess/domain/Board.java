package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.KingPiece;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new LinkedHashMap<>(64);

        initialPositions(board);
        initialPieces(board);

        return new Board(board);
    }

    private static void initialPositions(Map<Position, Piece> board) {
        List<Position> positions = Stream.of(Rank.values())
            .flatMap(rank ->
                Stream.of(File.values()).map(file -> new Position(file, rank)))
            .collect(Collectors.toList());

        for (Position position : positions) {
            board.put(position, new EmptyPiece());
        }
    }

    private static void initialPieces(Map<Position, Piece> board) {
        initialPiecesWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        initialPawns(board, Rank.SEVEN, Color.BLACK);

        initialPiecesWithoutPawn(board, Rank.ONE, Color.WHITE);
        initialPawns(board, Rank.TWO, Color.WHITE);
    }

    private static void initialPiecesWithoutPawn(Map<Position, Piece> board, final Rank rank,
        final Color color) {
        board.put(new Position(File.A, rank), new RookPiece(color));
        board.put(new Position(File.B, rank), new KnightPiece(color));
        board.put(new Position(File.C, rank), new BishopPiece(color));
        board.put(new Position(File.D, rank), new QueenPiece(color));
        board.put(new Position(File.E, rank), new KingPiece(color));
        board.put(new Position(File.F, rank), new BishopPiece(color));
        board.put(new Position(File.G, rank), new KnightPiece(color));
        board.put(new Position(File.H, rank), new RookPiece(color));
    }

    private static void initialPawns(Map<Position, Piece> board, final Rank rank,
        final Color color) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), new PawnPiece(color));
        }
    }

    public void move(final Position from, final Position to) {
        board.put(to, board.get(from));
        board.put(from, new EmptyPiece());
    }

    public void isMovable(Position from, Position to, Color color) {
        final Piece source = board.get(from);
        final Piece target = board.get(to);

        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        final Direction direction = Direction.of(fileDistance, rankDistance);

        checkMove(from, to, color, source, target, direction);
    }

    public boolean isCheckmate(final Position to) {
        return board.get(to).isKing();
    }

    private void checkMove(final Position from, final Position to, final Color color,
        final Piece source, final Piece target, final Direction direction) {
        checkSamePosition(from, to);
        checkEmptySource(source);
        checkTurn(color, source);
        checkMovement(from, to, source, target);
        checkTargetColor(color, target);
        checkBlocked(from, to, source, direction);
    }

    private void checkSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void checkEmptySource(final Piece source) {
        if (source.equals(new EmptyPiece())) {
            throw new IllegalStateException("[ERROR] source 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void checkTurn(final Color color, final Piece source) {
        if (!source.isSameColor(color)) {
            throw new IllegalStateException("[ERROR] 자신의 기물만 이동시킬 수 있습니다.");
        }
    }

    private void checkMovement(final Position from, final Position to, final Piece source,
        final Piece target) {
        if (!source.isMovable(from, to, target.equals(new EmptyPiece()))) {
            throw new IllegalStateException("[ERROR] 행마법에 맞지 않는 이동입니다.");
        }
    }

    private void checkTargetColor(final Color color, final Piece target) {
        if (target.isSameColor(color)) {
            throw new IllegalStateException("[ERROR] 자신의 기물이 있는 곳으로 이동시킬 수 없습니다.");
        }
    }

    private void checkBlocked(final Position from, final Position to, final Piece source,
        final Direction direction) {
        if (!source.isJumpable() && isBlocked(direction, from, to)) {
            throw new IllegalStateException("[ERROR] 이동 경로에 기물이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isBlocked(final Direction direction, final Position from, final Position to) {
        final Position next = direction.move(from);
        if (next.equals(to)) {
            return false;
        }
        if (!board.get(next).equals(new EmptyPiece())) {
            return true;
        }
        return isBlocked(direction, next, to);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
