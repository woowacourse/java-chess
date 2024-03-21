package chess.model;

import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final List<String> INITIAL_BOARD = List.of(
            "RNBQKBNR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rnbqkbnr"
    );

    private final Map<Position, Piece> board;
    private int turnCount;

    private Board(Map<Position, Piece> board, int turnCount) {
        this.board = board;
        this.turnCount = turnCount;
    }

    public static Board createInitialBoard() {
        return new Board(generateBoard(INITIAL_BOARD), 0);
    }

    public static Board createCustomBoard(List<String> customBoard) {
        return new Board(generateBoard(customBoard), 0);
    }

    private static Map<Position, Piece> generateBoard(List<String> customBoard) {
        Map<Position, Piece> board = new HashMap<>();
        for (int i = 0; i < customBoard.size(); i++) {
            String row = customBoard.get(i);
            putPiecesInRow(board, row, i);
        }
        return board;
    }

    private static void putPiecesInRow(Map<Position, Piece> board, String row, int rowIndex) {
        for (int j = 0; j < row.length(); j++) {
            Position position = new Position(rowIndex, j);
            char pieceName = row.charAt(j);
            PieceType pieceType = PieceType.findPieceTypeByName(String.valueOf(pieceName));
            Piece piece = Piece.from(pieceType);
            board.put(position, piece);
        }
    }

    public void move(String sourceCoordinate, String targetCoordinate) {
        Position source = Position.from(sourceCoordinate);
        Position target = Position.from(targetCoordinate);
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);

        validatePiecesPosition(sourcePiece, targetPiece);
        validatePieceCanMove(sourcePiece, source, target);
        validatePieceRoute(sourcePiece, source, target);

        board.put(target, sourcePiece);
        board.put(source, Piece.from(PieceType.NONE));
        turnCount++;
    }

    private void validatePiecesPosition(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isNone()) {
            throw new IllegalArgumentException("source위치에 기물이 존재하지 않습니다.");
        }
        if (targetPiece.isSameColorBy(turnCount)) {
            throw new IllegalArgumentException("target위치에 내 기물이 존재합니다.");
        }
    }

    private void validatePieceCanMove(Piece sourcePiece, Position source, Position target) {
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException("target위치로 기물을 이동할 수 없습니다.");
        }
    }

    private void validatePieceRoute(Piece sourcePiece, Position source, Position target) {
        if (sourcePiece.isKnight()) {
            return;
        }
        int rowDifference = target.getRow() - source.getRow();
        int columnDifference = target.getColumn() - source.getColumn();

        while (Math.abs(rowDifference) > 1 || Math.abs(columnDifference) > 1) {
            if (rowDifference > 0) {
                rowDifference--;
            }
            if (rowDifference < 0) {
                rowDifference++;
            }
            if (columnDifference > 0) {
                columnDifference--;
            }
            if (columnDifference < 0) {
                columnDifference++;
            }
            Position position = new Position(source.getRow() + rowDifference, source.getColumn() + columnDifference);
            if (!board.get(position).isNone()) {
                throw new IllegalArgumentException("경로 상에 다른 기물이 존재합니다.");
            }
        }
    }

    public Piece findPiece(Position position) {
        return board.get(position);
    }
}
