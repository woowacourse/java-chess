package chess.domain.board;

import chess.dao.PieceDAO;
import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Placeable;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardFactory {
    public static Board createEmpty() {
        return new Board(createEmptySource());
    }

    private static Map<Position, Placeable> createEmptySource() {
        Map<Position, Placeable> board = new LinkedHashMap<>();

        fillWithEmpty(board);

        return board;
    }

    public static Board createInitially(PieceDAO pieceDAO) {
        Map<Position, Placeable> positionAndPiece;

        try {
            positionAndPiece = createBoardSource();
            createBoardToDB(pieceDAO, positionAndPiece);
        } catch (SQLException e) {
            System.err.println("SQL 오류 : " + e.getErrorCode());
            throw new RuntimeException("SQL 오류가 발생하였습니다.");
        }

        return new Board(createBoardSource());
    }

    private static void createBoardToDB(PieceDAO pieceDAO, Map<Position, Placeable> positionAndPiece) throws SQLException {
        for (Position position : positionAndPiece.keySet()) {
            Placeable piece = positionAndPiece.get(position);

            PieceDTO pieceDTO = new PieceDTO();
            pieceDTO.setPosition(position);
            pieceDTO.setPieceType(piece.getPieceType());
            pieceDTO.setTeam(piece.getTeam());

            pieceDAO.addPiece(pieceDTO);
        }
    }

    private static Map<Position, Placeable> createBoardSource() {
        Map<Position, Placeable> board = new LinkedHashMap<>();

        fillWithEmpty(board);

        fillFirstRank(board);
        fillSecondRank(board);
        fillEighthRank(board);
        fillSeventhRank(board);

        return board;
    }

    private static void fillWithEmpty(Map<Position, Placeable> board) {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = Position.of(column, row);
                board.put(position, new Empty());
            }
        }
    }

    private static void fillEighthRank(Map<Position, Placeable> board) {
        addToBoard(board, "A8", Team.BLACK, PieceType.ROOK);
        addToBoard(board, "B8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "C8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "D8", Team.BLACK, PieceType.QUEEN);
        addToBoard(board, "E8", Team.BLACK, PieceType.KING);
        addToBoard(board, "F8", Team.BLACK, PieceType.BISHOP);
        addToBoard(board, "G8", Team.BLACK, PieceType.KNIGHT);
        addToBoard(board, "H8", Team.BLACK, PieceType.ROOK);
    }

    private static void fillSeventhRank(Map<Position, Placeable> board) {
        addToBoard(board, "A7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "B7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "C7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "D7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "E7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "F7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "G7", Team.BLACK, PieceType.PAWN);
        addToBoard(board, "H7", Team.BLACK, PieceType.PAWN);
    }

    private static void fillSecondRank(Map<Position, Placeable> board) {
        addToBoard(board, "A2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "B2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "C2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "D2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "E2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "F2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "G2", Team.WHITE, PieceType.PAWN);
        addToBoard(board, "H2", Team.WHITE, PieceType.PAWN);
    }

    private static void fillFirstRank(Map<Position, Placeable> board) {
        addToBoard(board, "A1", Team.WHITE, PieceType.ROOK);
        addToBoard(board, "B1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "C1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "D1", Team.WHITE, PieceType.QUEEN);
        addToBoard(board, "E1", Team.WHITE, PieceType.KING);
        addToBoard(board, "F1", Team.WHITE, PieceType.BISHOP);
        addToBoard(board, "G1", Team.WHITE, PieceType.KNIGHT);
        addToBoard(board, "H1", Team.WHITE, PieceType.ROOK);
    }

    private static void addToBoard(Map<Position, Placeable> board, String position, Team team, PieceType pieceType) {
        board.put(Position.of(position), new Piece(team, pieceType));
    }
}
