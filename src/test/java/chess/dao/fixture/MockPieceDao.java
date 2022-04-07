package chess.dao.fixture;

import chess.dao.PieceDao;
import chess.dto.PieceDto;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MockPieceDao implements PieceDao {

    private final Map<Map<Integer, Integer>, PieceDto> piece = new HashMap<>();

    @Override
    public void save(final int boardId, final PieceDto pieceDto) {
        piece.put(Map.of(1, boardId), pieceDto);
    }

    @Override
    public void saveAll(final int boardId, final Map<Position, Piece> board) {
        int pieceId = 1;
        for (Map.Entry<Position, Piece> entry : board.entrySet()) {
            final PieceDto pieceDto = PieceDto.toDto(entry.getValue(), entry.getKey());
            this.piece.put(Map.of(boardId, pieceId), pieceDto);
            pieceId++;
        }
    }

    @Override
    public Map<Position, Piece> findAllByBoardId(final int boardId) {
        final Map<String, String> rawBoard = piece.keySet().stream()
                .filter(key -> key.containsKey(boardId))
                .map(piece::get)
                .collect(Collectors.toMap(
                        PieceDto::getPosition,
                        PieceDto::getName
                ));

        return rawBoard.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> getPosition(e.getKey()),
                        e -> getPiece(e.getValue())
                ));
    }

    @Override
    public void deleteAllById(final int boardId) {
        piece.clear();
    }

    @Override
    public boolean isExist() {
        return !piece.isEmpty();
    }

    @Override
    public Map<Position, Piece> load() {
        final Map<String, String> rawBoard = piece.values().stream()
                .collect(Collectors.toMap(
                        PieceDto::getPosition,
                        PieceDto::getName
                ));

        return rawBoard.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> getPosition(e.getKey()),
                        e -> getPiece(e.getValue())
                ));
    }

    public Map<Map<Integer, Integer>, PieceDto> getPiece() {
        return piece;
    }

    private Piece getPiece(final String name) {
        return Pieces.valueOf(name);
    }

    private Position getPosition(final String value) {
        return Position.of(value);
    }
}
