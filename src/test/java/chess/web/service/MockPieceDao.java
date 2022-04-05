package chess.web.service;

import chess.board.piece.Piece;
import chess.board.piece.PieceFactory;
import chess.web.dao.PieceDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MockPieceDao implements PieceDao {
    private final Map<Long, FakePiece> mockDb = new HashMap<>();

    public MockPieceDao() {

        List<FakePiece> fakePieces = initFakePieces(1L);
        saveFakePieces(fakePieces);
    }

    private List<FakePiece> initFakePieces(Long boardId) {

        return List.of(
                new FakePiece(boardId, "a1", "rook", "white"),
                new FakePiece(boardId, "b1", "knight", "white"),
                new FakePiece(boardId, "c1", "bishop", "white"),
                new FakePiece(boardId, "d1", "queen", "white"),
                new FakePiece(boardId, "e1", "king", "white"),
                new FakePiece(boardId, "f1", "bishop", "white"),
                new FakePiece(boardId, "g1", "knight", "white"),
                new FakePiece(boardId, "h1", "rook", "white"),

                new FakePiece(boardId, "a2", "pawn", "white"),
                new FakePiece(boardId, "b2", "pawn", "white"),
                new FakePiece(boardId, "c2", "pawn", "white"),
                new FakePiece(boardId, "d2", "pawn", "white"),
                new FakePiece(boardId, "e2", "pawn", "white"),
                new FakePiece(boardId, "f2", "pawn", "white"),
                new FakePiece(boardId, "g2", "pawn", "white"),
                new FakePiece(boardId, "h2", "pawn", "white"),

                new FakePiece(boardId, "a3", "empty", "none"),
                new FakePiece(boardId, "b3", "empty", "none"),
                new FakePiece(boardId, "c3", "empty", "none"),
                new FakePiece(boardId, "d3", "empty", "none"),
                new FakePiece(boardId, "e3", "empty", "none"),
                new FakePiece(boardId, "f3", "empty", "none"),
                new FakePiece(boardId, "g3", "empty", "none"),
                new FakePiece(boardId, "h3", "empty", "none"),

                new FakePiece(boardId, "a4", "empty", "none"),
                new FakePiece(boardId, "b4", "empty", "none"),
                new FakePiece(boardId, "c4", "empty", "none"),
                new FakePiece(boardId, "d4", "empty", "none"),
                new FakePiece(boardId, "e4", "empty", "none"),
                new FakePiece(boardId, "f4", "empty", "none"),
                new FakePiece(boardId, "g4", "empty", "none"),
                new FakePiece(boardId, "h4", "empty", "none"),

                new FakePiece(boardId, "a5", "empty", "none"),
                new FakePiece(boardId, "b5", "empty", "none"),
                new FakePiece(boardId, "c5", "empty", "none"),
                new FakePiece(boardId, "d5", "empty", "none"),
                new FakePiece(boardId, "e5", "empty", "none"),
                new FakePiece(boardId, "f5", "empty", "none"),
                new FakePiece(boardId, "g5", "empty", "none"),
                new FakePiece(boardId, "h5", "empty", "none"),

                new FakePiece(boardId, "a6", "empty", "none"),
                new FakePiece(boardId, "b6", "empty", "none"),
                new FakePiece(boardId, "c6", "empty", "none"),
                new FakePiece(boardId, "d6", "empty", "none"),
                new FakePiece(boardId, "e6", "empty", "none"),
                new FakePiece(boardId, "f6", "empty", "none"),
                new FakePiece(boardId, "g6", "empty", "none"),
                new FakePiece(boardId, "h6", "empty", "none"),

                new FakePiece(boardId, "a7", "pawn", "black"),
                new FakePiece(boardId, "b7", "pawn", "black"),
                new FakePiece(boardId, "c7", "pawn", "black"),
                new FakePiece(boardId, "d7", "pawn", "black"),
                new FakePiece(boardId, "e7", "pawn", "black"),
                new FakePiece(boardId, "f7", "pawn", "black"),
                new FakePiece(boardId, "g7", "pawn", "black"),
                new FakePiece(boardId, "h7", "pawn", "black"),

                new FakePiece(boardId, "a8", "rook", "black"),
                new FakePiece(boardId, "b8", "knight", "black"),
                new FakePiece(boardId, "c8", "bishop", "black"),
                new FakePiece(boardId, "d8", "queen", "black"),
                new FakePiece(boardId, "e8", "king", "black"),
                new FakePiece(boardId, "f8", "bishop", "black"),
                new FakePiece(boardId, "g8", "knight", "black"),
                new FakePiece(boardId, "h8", "rook", "black"));
    }

    @Override
    public void updatePieceByPositionAndBoardId(String type, String team, String position, Long boardId) {
        List<Piece> pieces = findAllByBoardId(boardId);
        List<FakePiece> fakePieces = convertPieceToFake(pieces, boardId);
        for (FakePiece fakePiece : fakePieces) {
            if (fakePiece.position.equals(position)) {
                fakePiece.type = type;
                fakePiece.team = team;
                break;
            }
        }
    }

    @Override
    public List<Piece> findAllByBoardId(Long boardId) {
        return mockDb.values().stream()
                .filter(fakePiece -> fakePiece.boardId.equals(boardId))
                .map(fakePiece -> PieceFactory.create(fakePiece.position, fakePiece.team, fakePiece.type))
                .collect(Collectors.toList());
    }

    @Override
    public void save(List<Piece> pieces, Long boardId) {
        List<FakePiece> fakePieces = convertPieceToFake(pieces, boardId);
        saveFakePieces(fakePieces);
    }

    private List<FakePiece> convertPieceToFake(List<Piece> pieces, Long boardId) {
        return pieces.stream()
                .map(piece -> new FakePiece(boardId, piece.getPosition().name(), piece.getType(), piece.getTeam().value()))
                .collect(Collectors.toList());
    }

    private void saveFakePieces(List<FakePiece> fakePieces) {
        long sequenceId = 1L;
        for (FakePiece fakePiece : fakePieces) {
            mockDb.put(sequenceId++, fakePiece);
        }
    }

    private static class FakePiece {
        private final Long boardId;
        private final String position;
        private String type;
        private String team;

        private FakePiece(Long boardId, String position, String type, String team) {
            this.boardId = boardId;
            this.position = position;
            this.type = type;
            this.team = team;
        }
    }
}
