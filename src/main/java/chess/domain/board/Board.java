package chess.domain.board;


import chess.domain.piece.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int MIN_BORDER = 1;
    public static final int MAX_BORDER = 8;
    private final Map<Position, Piece> board;

    public Board() {
        this.board = createBoard();
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = initialize();

        board.put(Position.of(Horizontal.A, Vertical.EIGHT), new Rook(Team.BLACK));
        board.put(Position.of(Horizontal.B, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Horizontal.C, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Horizontal.D, Vertical.EIGHT), new Queen(Team.BLACK));
        board.put(Position.of(Horizontal.E, Vertical.EIGHT), new King(Team.BLACK));
        board.put(Position.of(Horizontal.F, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Horizontal.G, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Horizontal.H, Vertical.EIGHT), new Rook(Team.BLACK));

        board.put(Position.of(Horizontal.A, Vertical.ONE), new Rook(Team.WHITE));
        board.put(Position.of(Horizontal.B, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Horizontal.C, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Horizontal.D, Vertical.ONE), new Queen(Team.WHITE));
        board.put(Position.of(Horizontal.E, Vertical.ONE), new King(Team.WHITE));
        board.put(Position.of(Horizontal.F, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Horizontal.G, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Horizontal.H, Vertical.ONE), new Rook(Team.WHITE));
        setPawn(board);

        return board;
    }

    public Piece findPieceFromPosition(Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> initialize() {
        Map<Position, Piece> board = new LinkedHashMap<>();

        for (Position position : Position.getPositions()) {
            board.put(position, null);
        }

        return board;
    }

    public void movePiece(Position target, Position destination) {
        Piece targetPiece = findPieceFromPosition(target);
        List<Position> targetMovablePositions = targetPiece.searchMovablePositions(target);
        
        checkMovable(targetMovablePositions, destination);
        board.put(destination, targetPiece);
        board.put(target, null);
    }

    private void checkMovable(List<Position> targetMovablePositions, Position destination) {
        if (targetMovablePositions.contains(destination)) {
            return;
        }
        throw new UnsupportedOperationException("이동 불가능한 좌표입니다.");
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    private void setPawn(Map<Position, Piece> board) {
        for (Horizontal horizontal : Horizontal.values()) {
            board.put(Position.of(horizontal, Vertical.SEVEN), new Pawn(Team.BLACK));
            board.put(Position.of(horizontal, Vertical.TWO), new Pawn(Team.WHITE));
        }
    }
}
