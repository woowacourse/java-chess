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

    // TODO: boardId, pieceId 들고 있는 클래스로 분리
    private Map<Map<Integer, Integer>, PieceDto> piece = new HashMap<>();

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

    @Override
    public void updatePieceByPosition(final int boardId, final String from, final PieceDto dto) {
        final Map<Integer, Integer> key = piece.entrySet().stream()
                .filter(entry -> entry.getValue().getPosition().equals(from))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        piece.put(key, dto);

    }

    @Override
    public void deletePieceByPosition(final int boardId, final String to) {
        final Map<Integer, Integer> key = piece.entrySet().stream()
                .filter(entry -> entry.getValue().getPosition().equals(to))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        piece.remove(key);
    }

    public Map<Map<Integer, Integer>, PieceDto> getPiece() {
        return piece;
    }

    public PieceDto getPieceDtoByKey(String position) {
        final Map<Integer, Integer> key = piece.entrySet().stream()
                .filter(entry -> entry.getValue().getPosition().equals(position))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return piece.get(key);
    }

    private Piece getPiece(final String name) {
        return Pieces.valueOf(name);
    }

    private Position getPosition(final String value) {
        return Position.of(value);
    }
}
