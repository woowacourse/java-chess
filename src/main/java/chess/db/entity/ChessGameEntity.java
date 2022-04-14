package chess.db.entity;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.State;
import chess.dto.StateType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameEntity {

    private final int id;
    private final String state;

    public ChessGameEntity(final int id, final String state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public State getState(final List<PieceEntity> pieceEntities) {
        Board board = getBoard(pieceEntities);
        return StateType.getState(state, board);
    }

    private Board getBoard(final List<PieceEntity> pieceEntities) {
        Map<Position, Piece> board = new HashMap<>();
        for (PieceEntity pieceEntity : pieceEntities) {
            board.put(pieceEntity.getPosition(), pieceEntity.getPiece());
        }
        return new Board(() -> board);
    }
}
