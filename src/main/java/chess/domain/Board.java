package chess.domain;

import static chess.domain.Rank.createBlank;
import static chess.domain.Rank.createPawn;
import static chess.domain.Rank.createPiecesExceptPawn;
import static chess.domain.Row.EIGHT;
import static chess.domain.Row.ONE;
import static chess.domain.Row.SEVEN;
import static chess.domain.Row.TWO;
import static chess.domain.Row.find;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Row, Rank> board;

    private Board(Map<Row, Rank> board) {
        this.board = board;
    }

    public static Board initBoard() {
        Map<Row, Rank> board = new EnumMap<>(Row.class);
        initPieces(board);
        return new Board(board);
    }

    private static void initPieces(Map<Row, Rank> board) {
        board.put(EIGHT, createPiecesExceptPawn(Team.BLACK, 8));
        board.put(SEVEN, createPawn(Team.BLACK, 7));
        for (int i = 3; i <= 6; i++) {
            board.put(find(i), createBlank(i));
        }
        board.put(TWO, createPawn(Team.WHITE, 2));
        board.put(ONE, createPiecesExceptPawn(Team.WHITE, 1));
    }

    public Piece getPiece(Position position) {
        return board.get(position.getRow()).getPiece(position.getCol());
    }

    public Piece movePiece(Position source, Position destination, Team team) {
        Piece srcPiece = getPiece(source);
        Piece dstPiece = getPiece(destination);

        validateMovingPiece(source, destination, srcPiece, team);
        srcPiece.move(destination);
        changePiece(source, destination, srcPiece);

        return dstPiece;
    }

    public void validateMovingPiece(Position source, Position destination, Piece piece, Team team) {
        validateTeam(team, piece);
        validateMovingPath(source, destination, piece);
    }

    private void validateTeam(Team team, Piece piece) {
        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("다른 팀 말을 옮길 수 없습니다.");
        }
    }

    private void validateMovingPath(Position source, Position destination, Piece piece) {
        if (piece.isSameTeam(getPiece(destination))) {
            throw new IllegalArgumentException("같은 팀은 kill 할 수 없습니다.");
        }
        if (piece.isPawn() && isDiagonal(source, destination)) {
            validatePawnAttemptKill(destination);
            return;
        }
        validateExistOtherPiece(piece.findPath(destination));
    }

    private boolean isDiagonal(Position source, Position destination) {
        return Math.abs(source.getRow().getDifference(destination.getRow())) == 1
                && Math.abs(source.getCol().getDifference(destination.getCol())) == 1;
    }

    private void validatePawnAttemptKill(Position destination) {
        if (getPiece(destination).isBlank()) {
            throw new IllegalArgumentException("잡을 수 있는 말이 없습니다.");
        }
    }

    private void validateExistOtherPiece(List<Position> positions) {
        for (Position position : positions) {
            checkIsBlank(position);
        }
    }

    private void checkIsBlank(Position position) {
        if (!getPiece(position).isBlank()) {
            throw new IllegalArgumentException("해당 위치로 말이 이동할 수 없습니다.");
        }
    }

    private void changePiece(Position source, Position destination, Piece piece) {
        board.get(source.getRow()).changePiece(source.getCol(), new Blank(Team.NONE, source));
        board.get(destination.getRow()).changePiece(destination.getCol(), piece);
    }

    public boolean isKingDead(Piece piece) {
        return piece.isKing();
    }

    public Map<Row, Rank> getBoard() {
        return new EnumMap<>(board);
    }
}
