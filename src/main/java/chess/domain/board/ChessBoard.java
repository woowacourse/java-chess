package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<BoardSquare, Piece> chessBoard = new HashMap<>();

    public ChessBoard() {
        chessBoard.put(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK));
        chessBoard.put(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK));

        chessBoard.put(BoardSquare.of("b1"), Knight.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("b8"), Knight.getPieceInstance(Color.BLACK));
        chessBoard.put(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK));

        chessBoard.put(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK));
        chessBoard.put(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK));

        chessBoard.put(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE));
        chessBoard.put(BoardSquare.of("e8"), Queen.getPieceInstance(Color.BLACK));
        chessBoard.put(BoardSquare.of("d8"), King.getPieceInstance(Color.BLACK));
        chessBoard.put(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE));

        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            chessBoard
                .put(BoardSquare.of(String.valueOf(file) + 2), Pawn.getPieceInstance(Color.WHITE));
            chessBoard
                .put(BoardSquare.of(String.valueOf(file) + 7), Pawn.getPieceInstance(Color.BLACK));
        }
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean movePieceWhenCanMove(List<BoardSquare> boardSquares, boolean blackTurn) {
        if (canMove(boardSquares, blackTurn)) {
            movePiece(boardSquares);
            return true;
        }
        return false;
    }

    private boolean canMove(List<BoardSquare> boardSquares, boolean blackTurn) {
        BoardSquare before = boardSquares.get(0);
        BoardSquare after = boardSquares.get(1);
        if (!chessBoard.containsKey(before) || chessBoard.get(before).isBlack() != blackTurn) {
            return false;
        }
        return chessBoard.get(before).getCheatSheet(before, chessBoard).contains(after);
    }

    public void movePiece(List<BoardSquare> boardSquares) {
        BoardSquare before = boardSquares.get(0);
        BoardSquare after = boardSquares.get(1);
        Piece currentPiece = chessBoard.remove(before);
        chessBoard.put(after, currentPiece);
    }

    public boolean isKingCaptured() {
        return !(chessBoard.containsValue(King.getPieceInstance(Color.WHITE))
                && chessBoard.containsValue(King.getPieceInstance(Color.BLACK)));
    }

    public Map<Color, Double> getTeamScore() {
        return new TeamScore(chessBoard).getTeamScore();
    }


    public List<Color> getWinners() {
        return new TeamScore(chessBoard).getWinners();
    }

}