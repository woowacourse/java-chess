package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeBoardDAO implements BoardDAO {
    private Map<Position, Piece> board = new HashMap<>();

    public static FakeBoardDAO initialFakeBoardDAO() {
        FakeBoardDAO fakeBoardDAO = new FakeBoardDAO();
        for (Piece piece : Piece.getPieces()) {
            placeInitial(fakeBoardDAO, piece);
        }
        return fakeBoardDAO;
    }

    private static void placeInitial(FakeBoardDAO fakeBoardDAO, Piece piece) {
        for (Position position : piece.initialPositions()) {
            fakeBoardDAO.placePieceOn(position, piece);
        }
    }

    @Override
    public void placePieceOn(Position position, Piece piece) {
        board.put(position, piece);
    }

    @Override
    public Optional<Piece> findPieceOn(Position position) {
        return Optional.ofNullable(board.get(position));
    }

    @Override
    public Map<Position, Piece> findAllPieces() {
        return board;
    }

    @Override
    public void removePieceOn(Position position) {
        board.remove(position);
    }
}
