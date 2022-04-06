package web.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePieceDao implements PieceDao {

    private static Map<String, Piece> pieceStore = new HashMap<>();
    Color turn = Color.WHITE;

    @Override
    public List<Piece> load() {
        return new ArrayList<>(pieceStore.values());
    }

    @Override
    public String findTurn() {
        return turn.name();
    }

    @Override
    public Map<Position, Piece> findPieces() {
        Map<Position, Piece> board = new HashMap<>();
        for (Piece value : pieceStore.values()) {
            board.put(value.getPosition(), value);
        }
        return board;
    }

    @Override
    public void savePiece(String position, Piece piece) {
        if (pieceStore.containsKey(position)) {
            throw new IllegalArgumentException("기물의 위치는 중복될 수 없습니다.");
        }

        pieceStore.put(position, piece);
    }

    @Override
    public void deletePiece(String position) {
        if (!pieceStore.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }

        pieceStore.remove(position);
    }

    @Override
    public void updateTurn(Color color) {
        turn = color;
    }

    @Override
    public void removeAll() {
        pieceStore = new HashMap<>();
    }

    @Override
    public void initTurn() {
        turn = Color.WHITE;
    }
}
