package chess.controller;

import chess.dao.PieceDao;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    private Map<String, PieceDto> pieces = new HashMap<>();

    @Override
    public void saveAllPieces(final Map<Position, Piece> board) {
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            pieces.put(position.toString(), new PieceDto(piece.getTeam(), piece.getName()));
        }
    }

    @Override
    public Map<String, PieceDto> findAllPieces() {
        return pieces;
    }

    @Override
    public void removePieceByPosition(final String position) {
        pieces.remove(position);
    }

    @Override
    public void updatePiece(final String position, final Piece piece) {
        pieces.put(position, new PieceDto(piece.getTeam(), piece.getName()));
    }

    @Override
    public void removeAllPieces() {
        pieces = new HashMap<>();
    }
}
