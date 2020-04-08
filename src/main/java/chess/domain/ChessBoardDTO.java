package chess.domain;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardDTO {

    private final List<String> squares = new ArrayList<>();
    private String turn;
    private String id;
    private List<String> pieces = new ArrayList<>();

    public ChessBoardDTO(ChessBoard chessBoard) {
        this(chessBoard, chessBoard.getTurn());
    }

    private ChessBoardDTO(ChessBoard chessBoard, Turn turn) {
        for (Square square : chessBoard.getChessBoard().keySet()) {
            squares.add(square.toString());
        }
        for (Piece piece : chessBoard.getChessBoard().values()) {
            squares.add(piece.toString());
        }
        this.turn = turn.getTurn().getName();
        this.id = chessBoard.getPlayer().getPlayerId();
    }

    public String getTurn() {
        return turn;
    }

    public String getId() {
        return id;
    }

    public List<String> getPieces() {
        return pieces;
    }

    public List<String> getSquares() {
        return squares;
    }
}
