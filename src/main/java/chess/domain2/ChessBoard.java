package chess.domain2;

import chess.domain2.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();
    private Color turn;

    public ChessBoard() {
        for (File file : File.values()) {
            initPiecesLocation(file);
        }
        this.turn = Color.WHITE;
    }

    private void initPiecesLocation(File file) {
        initPawnLocation(file);
        initRookLocation(file);
        initKnightLocation(file);
        initBishopLocation(file);
        initQueenLocation(file);
        initKingLocation(file);
    }

    private void initQueenLocation(File file) {
        if (file.equals(File.D)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Queen.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Queen.of(Color.BLACK));
        }
    }

    private void initKingLocation(File file) {
        if (file.equals(File.E)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), King.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), King.of(Color.BLACK));
        }
    }

    private void initBishopLocation(File file) {
        if (file.equals(File.C) || file.equals(File.F)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Bishop.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Bishop.of(Color.BLACK));
        }
    }

    private void initKnightLocation(File file) {
        if (file.equals(File.B) || file.equals(File.G)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Knight.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Knight.of(Color.BLACK));
        }
    }

    private void initRookLocation(File file) {
        if (file.equals(File.A) || file.equals(File.H)) {
            chessBoard.put(Square.of(file.getName(), Rank.ONE.getName()), Rook.of(Color.WHITE));
            chessBoard.put(Square.of(file.getName(), Rank.EIGHT.getName()), Rook.of(Color.BLACK));
        }
    }

    private void initPawnLocation(File file) {
        chessBoard.put(Square.of(file.getName(), Rank.TWO.getName()), Pawn.of(Color.WHITE));
        chessBoard.put(Square.of(file.getName(), Rank.SEVEN.getName()), Pawn.of(Color.BLACK));
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    boolean canMove(Square beforeSquare, Square afterSquare) {
        // 이동전에 말이 없는경우
        // 이동전 컬러가 순서와 안맞는경우
        // 이동후로 이동 못하는 경우
        Piece beforePiece = chessBoard.get(beforeSquare);
        if (!chessBoard.containsKey(beforeSquare)) {
            return false;
        }
        if (!beforePiece.getColor().equals(turn)) {
            return false;
        }
        if (!beforePiece.getMovableSquares(beforeSquare, chessBoard).contains(afterSquare)) {
            return false;
        }
        changeTurn();
        return true;
    }

    void changeTurn() {
        this.turn = this.turn.changeColor(turn);
    }

    public boolean movePiece(List<Square> squares) {
        Square beforeSquare = squares.get(0);
        Square afterSquare = squares.get(1);
        if (!canMove(beforeSquare, afterSquare)) {
            return false;
        }
        Piece currentPiece = chessBoard.remove(beforeSquare);
        chessBoard.put(afterSquare, currentPiece);
        return true;
    }
}
