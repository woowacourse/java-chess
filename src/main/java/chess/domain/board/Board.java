package chess.domain.board;

import java.util.Map;
import java.util.NoSuchElementException;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Board {

    private final Pieces pieces;
    private Team turn;

    public Board(Map<Coordinate, Piece> pieces) {
        this.pieces = new Pieces(pieces);
        this.turn = Team.WHITE;
    }

    public Board() {
        this(BoardFactory.createInitialPieces());
    }

    public Piece findByCoordinate(Coordinate coordinate) {
        return pieces.findByCoordinate(coordinate);
    }

    public boolean isPiecePresent(Coordinate coordinate) {
        return pieces.isPiecePresent(coordinate);
    }

    public void move(Coordinate source, Coordinate target) {
        validateSourceExist(source);
        validateTurn(source);
        validateMovable(source, target);
        updateBoard(source, target);
    }

    private void validateSourceExist(Coordinate source) {
        if (!pieces.isPiecePresent(source)) {
            throw new NoSuchElementException("보드에 움직일 대상이 없습니다.");
        }
    }

    private void validateTurn(Coordinate source) {
        Piece sourcePiece = pieces.findByCoordinate(source);
        if (!sourcePiece.isSameTeam(turn)) {
            throw new IllegalStateException("상대방이 기물을 둘 차례입니다.");
        }
    }

    private void validateMovable(Coordinate source, Coordinate target) {
        Piece sourcePiece = pieces.findByCoordinate(source);
        sourcePiece.validateMovable(source, target, this);
    }

    private void updateBoard(Coordinate source, Coordinate target) {
        pieces.swap(source, target);
        turn = turn.opposite();
    }
}
