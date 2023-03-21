package chess.service;

import chess.dao.board.BoardDao;
import chess.dao.board.BoardDaoImpl;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.factory.BoardFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardService {

    private final BoardDao boardDao = new BoardDaoImpl();

    public void save(final int boardId, final Map<Position, Piece> board, final boolean isLowerTeamTurn) {
        Map<String, String> boardToSave = parseBoardToSavingIntoDatabase(board);
        boardDao.save(boardId, boardToSave, isLowerTeamTurn);
    }

    private Map<String, String> parseBoardToSavingIntoDatabase(final Map<Position, Piece> board) {
        Map<String, String> boardToSave = new HashMap<>();

        for (Entry<Position, Piece> pieceWithPosition : board.entrySet()) {
            boardToSave.put(pieceWithPosition.getKey().toString(), pieceWithPosition.getValue().getName());
        }

        return boardToSave;
    }

    public Board findById(final int boardId) {
        Map<String, String> databaseBoard = boardDao.findById(boardId);

        if (databaseBoard.isEmpty()) {
            return BoardFactory.createBoard();
        }

        Map<Position, Piece> board = parseBoardToJava(databaseBoard);
        return new Board(board);
    }

    public boolean isLowerTeamTurnByBoardId(final int boardId) {
        return boardDao.isLowerTeamTurnByBoardId(boardId);
    }

    private Map<Position, Piece> parseBoardToJava(final Map<String, String> databaseBoard) {
        Map<Position, Piece> board = new HashMap<>();

        for (Entry<String, String> pieceWithPosition : databaseBoard.entrySet()) {
            board.put(Position.from(pieceWithPosition.getKey()), parsePiece(pieceWithPosition.getValue()));
        }

        return board;
    }

    private Piece parsePiece(final String name) {
        if (name.equals("r") || name.equals("R")) {
            return new Rook(new Name(name));
        }

        if (name.equals("n") || name.equals("N")) {
            return new Knight(new Name(name));
        }

        if (name.equals("b") || name.equals("B")) {
            return new Bishop(new Name(name));
        }

        if (name.equals("q") || name.equals("Q")) {
            return new Queen(new Name(name));
        }

        if (name.equals("p") || name.equals("P")) {
            return new Pawn(new Name(name));
        }

        if (name.equals("k") || name.equals("K")) {
            return new King(new Name(name));
        }

        return new Place();
    }

    public void delete(final int boardId) {
        boardDao.remove(boardId);
    }
}
