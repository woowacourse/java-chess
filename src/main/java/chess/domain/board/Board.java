package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Board {

    private static final Piece EMPTY_PIECE = new Empty();

    private final Map<Coordinate, Piece> pieces = new HashMap<>();
    private Team turn;

    public Board(Map<Coordinate, Piece> pieces) {
        this.pieces.putAll(pieces);
        this.turn = Team.WHITE;
    }

    public Board() {
        this(BoardFactory.createInitialPieces());
    }

    public Piece findByCoordinate(Coordinate coordinate) {
        return pieces.getOrDefault(coordinate, EMPTY_PIECE);
    }

    public boolean isPiecePresent(Coordinate coordinate) {
        return pieces.containsKey(coordinate);
    }

    public void move(Coordinate source, Coordinate target) {
        validateSourceExist(source);
        validateTurn(source);
        validateMovable(source, target);
        updateBoard(source, target);
    }

    private void validateSourceExist(Coordinate source) {
        if (!pieces.containsKey(source)) {
            throw new NoSuchElementException("보드에 움직일 대상이 없습니다.");
        }
    }

    private void validateTurn(Coordinate source) {
        Piece sourcePiece = pieces.get(source);
        if (!sourcePiece.isSameTeam(turn)) {
            throw new IllegalStateException("상대방이 기물을 둘 차례입니다.");
        }
    }

    private void validateMovable(Coordinate source, Coordinate target) {
        Piece sourcePiece = pieces.get(source);
        sourcePiece.validateMovable(source, target, this);
    }

    private void updateBoard(Coordinate source, Coordinate target) {
        Piece sourcePiece = pieces.get(source);
        pieces.remove(source);
        pieces.put(target, sourcePiece);
        turn = turn.opposite();
    }
}
