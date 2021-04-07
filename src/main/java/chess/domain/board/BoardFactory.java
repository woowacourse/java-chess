package chess.domain.board;

import chess.domain.piece.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class BoardFactory {

    public static Board loadSavedBoardInfo(Map<String, String> boardInfo) {
        Map<Position, Piece> board = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : boardInfo.entrySet()) {
            String position = entry.getKey();
            String uniCode = entry.getValue();
            board.put(Position.convertStringToPosition(position), PieceFactory.createPieceByUniCode(uniCode));
        }
        return new Board(board);
    }

    public Board create() {
        return new Board(createBoard());
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = initialize();

        board.put(Position.of(Horizontal.A, Vertical.EIGHT), new Rook(Team.BLACK));
        board.put(Position.of(Horizontal.B, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Horizontal.C, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Horizontal.D, Vertical.EIGHT), new King(Team.BLACK));
        board.put(Position.of(Horizontal.E, Vertical.EIGHT), new Queen(Team.BLACK));
        board.put(Position.of(Horizontal.F, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Position.of(Horizontal.G, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Position.of(Horizontal.H, Vertical.EIGHT), new Rook(Team.BLACK));

        board.put(Position.of(Horizontal.A, Vertical.ONE), new Rook(Team.WHITE));
        board.put(Position.of(Horizontal.B, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Horizontal.C, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Horizontal.D, Vertical.ONE), new King(Team.WHITE));
        board.put(Position.of(Horizontal.E, Vertical.ONE), new Queen(Team.WHITE));
        board.put(Position.of(Horizontal.F, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Position.of(Horizontal.G, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Position.of(Horizontal.H, Vertical.ONE), new Rook(Team.WHITE));
        setPawn(board);

        return board;
    }

    private Map<Position, Piece> initialize() {
        Map<Position, Piece> board = new LinkedHashMap<>();

        for (Position position : Position.getPositions()) {
            board.put(position, null);
        }

        return board;
    }

    private void setPawn(Map<Position, Piece> board) {
        for (Horizontal horizontal : Horizontal.values()) {
            board.put(Position.of(horizontal, Vertical.SEVEN), new Pawn(Team.BLACK));
            board.put(Position.of(horizontal, Vertical.TWO), new Pawn(Team.WHITE));
        }
    }
}
