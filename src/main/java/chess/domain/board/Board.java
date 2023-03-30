package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;
    private Color turn;

    public Board(final Map<Position, Piece> board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public static Board from(final Map<Position, Piece> board) {
        return new Board(board, Color.WHITE);
    }

    public static Board from(final Map<Position, Piece> board, final Color turn) {
        return new Board(board, turn);
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        final Piece piece = board.get(sourcePosition);

        validate(sourcePosition, targetPosition, piece);
        movePiece(sourcePosition, targetPosition, piece);
        turn = turn.nextTurn();
    }

    private void validate(final Position sourcePosition, final Position targetPosition, final Piece piece) {
        if (turn.isOpponent(piece.color())) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
        if (piece.isUnmovable(sourcePosition, targetPosition, board.get(targetPosition))) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령어입니다.");
        }
        if (isPieceExistsBetweenPositions(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    private boolean isPieceExistsBetweenPositions(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.between(targetPosition).stream()
                .anyMatch(this::isPieceExists);
    }

    private boolean isPieceExists(final Position position) {
        return !isNotPieceExists(position);
    }

    private boolean isNotPieceExists(final Position position) {
        return board.get(position).equals(Empty.getInstance());
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        board.put(target, piece);
        board.put(source, Empty.getInstance());
    }

    public double whiteScore() {
        return calculateScore(Color.WHITE);
    }

    public double blackScore() {
        return calculateScore(Color.BLACK);
    }

    private double calculateScore(final Color color) {
        final BoardResult boardResult = BoardResult.create(getBoard());
        return boardResult.calculatePoints(color);
    }

    public boolean isKingDead() {
        return isKingDead(Color.WHITE) || isKingDead(Color.BLACK);
    }

    public boolean isKingDead(final Color color) {
        return !isKingAlive(color);
    }

    private boolean isKingAlive(final Color color){
        return board.containsValue(King.from(color));
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }
}
