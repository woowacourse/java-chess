package chess.web.service;

import chess.board.piece.Piece;
import chess.board.piece.PieceFactory;
import chess.web.dao.PieceDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockPieceDao implements PieceDao {
    private final Map<Long, MockPiece> mockDb = new HashMap<>();

    public MockPieceDao() {

        List<MockPiece> mockPieces = initFakePieces(1L);
        saveFakePieces(mockPieces);
    }

    private List<MockPiece> initFakePieces(Long boardId) {

        return List.of(
                new MockPiece(boardId, "a1", "rook", "white"),
                new MockPiece(boardId, "b1", "knight", "white"),
                new MockPiece(boardId, "c1", "bishop", "white"),
                new MockPiece(boardId, "d1", "queen", "white"),
                new MockPiece(boardId, "e1", "king", "white"),
                new MockPiece(boardId, "f1", "bishop", "white"),
                new MockPiece(boardId, "g1", "knight", "white"),
                new MockPiece(boardId, "h1", "rook", "white"),

                new MockPiece(boardId, "a2", "pawn", "white"),
                new MockPiece(boardId, "b2", "pawn", "white"),
                new MockPiece(boardId, "c2", "pawn", "white"),
                new MockPiece(boardId, "d2", "pawn", "white"),
                new MockPiece(boardId, "e2", "pawn", "white"),
                new MockPiece(boardId, "f2", "pawn", "white"),
                new MockPiece(boardId, "g2", "pawn", "white"),
                new MockPiece(boardId, "h2", "pawn", "white"),

                new MockPiece(boardId, "a3", "empty", "none"),
                new MockPiece(boardId, "b3", "empty", "none"),
                new MockPiece(boardId, "c3", "empty", "none"),
                new MockPiece(boardId, "d3", "empty", "none"),
                new MockPiece(boardId, "e3", "empty", "none"),
                new MockPiece(boardId, "f3", "empty", "none"),
                new MockPiece(boardId, "g3", "empty", "none"),
                new MockPiece(boardId, "h3", "empty", "none"),

                new MockPiece(boardId, "a4", "empty", "none"),
                new MockPiece(boardId, "b4", "empty", "none"),
                new MockPiece(boardId, "c4", "empty", "none"),
                new MockPiece(boardId, "d4", "empty", "none"),
                new MockPiece(boardId, "e4", "empty", "none"),
                new MockPiece(boardId, "f4", "empty", "none"),
                new MockPiece(boardId, "g4", "empty", "none"),
                new MockPiece(boardId, "h4", "empty", "none"),

                new MockPiece(boardId, "a5", "empty", "none"),
                new MockPiece(boardId, "b5", "empty", "none"),
                new MockPiece(boardId, "c5", "empty", "none"),
                new MockPiece(boardId, "d5", "empty", "none"),
                new MockPiece(boardId, "e5", "empty", "none"),
                new MockPiece(boardId, "f5", "empty", "none"),
                new MockPiece(boardId, "g5", "empty", "none"),
                new MockPiece(boardId, "h5", "empty", "none"),

                new MockPiece(boardId, "a6", "empty", "none"),
                new MockPiece(boardId, "b6", "empty", "none"),
                new MockPiece(boardId, "c6", "empty", "none"),
                new MockPiece(boardId, "d6", "empty", "none"),
                new MockPiece(boardId, "e6", "empty", "none"),
                new MockPiece(boardId, "f6", "empty", "none"),
                new MockPiece(boardId, "g6", "empty", "none"),
                new MockPiece(boardId, "h6", "empty", "none"),

                new MockPiece(boardId, "a7", "pawn", "black"),
                new MockPiece(boardId, "b7", "pawn", "black"),
                new MockPiece(boardId, "c7", "pawn", "black"),
                new MockPiece(boardId, "d7", "pawn", "black"),
                new MockPiece(boardId, "e7", "pawn", "black"),
                new MockPiece(boardId, "f7", "pawn", "black"),
                new MockPiece(boardId, "g7", "pawn", "black"),
                new MockPiece(boardId, "h7", "pawn", "black"),

                new MockPiece(boardId, "a8", "rook", "black"),
                new MockPiece(boardId, "b8", "knight", "black"),
                new MockPiece(boardId, "c8", "bishop", "black"),
                new MockPiece(boardId, "d8", "queen", "black"),
                new MockPiece(boardId, "e8", "king", "black"),
                new MockPiece(boardId, "f8", "bishop", "black"),
                new MockPiece(boardId, "g8", "knight", "black"),
                new MockPiece(boardId, "h8", "rook", "black"));
    }

    @Override
    public void updatePieceByPositionAndBoardId(String type, String team, String position, Long boardId) {
        List<Piece> pieces = findAllByBoardId(boardId);
        List<MockPiece> mockPieces = convertPieceToFake(pieces, boardId);
        for (MockPiece mockPiece : mockPieces) {
            if (mockPiece.position.equals(position)) {
                mockPiece.type = type;
                mockPiece.team = team;
                break;
            }
        }
    }

    @Override
    public List<Piece> findAllByBoardId(Long boardId) {
        return mockDb.values().stream()
                .filter(mockPiece -> mockPiece.boardId.equals(boardId))
                .map(mockPiece -> PieceFactory.create(mockPiece.position, mockPiece.team, mockPiece.type))
                .collect(Collectors.toList());
    }

    @Override
    public void save(List<Piece> pieces, Long boardId) {
        List<MockPiece> mockPieces = convertPieceToFake(pieces, boardId);
        saveFakePieces(mockPieces);
    }

    @Override
    public void deleteByBoardId(Long boardId) {
        mockDb.clear();
    }

    private List<MockPiece> convertPieceToFake(List<Piece> pieces, Long boardId) {
        return pieces.stream()
                .map(piece -> new MockPiece(boardId, piece.getPosition().name(), piece.getType(), piece.getTeam().value()))
                .collect(Collectors.toList());
    }

    private void saveFakePieces(List<MockPiece> mockPieces) {
        long sequenceId = 1L;
        for (MockPiece mockPiece : mockPieces) {
            mockDb.put(sequenceId++, mockPiece);
        }
    }

    private static class MockPiece {
        private final Long boardId;
        private final String position;
        private String type;
        private String team;

        private MockPiece(Long boardId, String position, String type, String team) {
            this.boardId = boardId;
            this.position = position;
            this.type = type;
            this.team = team;
        }
    }
}
