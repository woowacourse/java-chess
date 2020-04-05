package chess.dto;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.state.MoveState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.NullChecker;

public class ChessBoardDto {

    private final List<String> pieces;
    private final String turn;
    private final String state;

    public ChessBoardDto(ChessBoard chessBoard) {
        this(chessBoard, MoveState.EMPTY);
    }

    public ChessBoardDto(ChessBoard chessBoard, MoveState moveState) {
        NullChecker.validateNotNull(chessBoard, moveState);
        Map<BoardSquare, Piece> board = chessBoard.getChessBoard();
        List<String> pieces = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            printRankRaw(pieces, board, rank);
        }
        this.pieces = pieces;
        this.turn = chessBoard.getGameTurn().getName();
        this.state = moveState.getMessage();
    }

    private static void printRankRaw(List<String> pieces, Map<BoardSquare, Piece> board, int rank) {
        for (char file = 'a'; file <= 'h'; file++) {
            pieces.add(getLetterByFileColumn(board, rank, file));
        }
    }

    private static String getLetterByFileColumn(Map<BoardSquare, Piece> gameBoard, int rank,
        char file) {
        if (gameBoard.containsKey(BoardSquare.of(String.valueOf(file) + rank))) {
            return gameBoard.get(BoardSquare.of(String.valueOf(file) + rank)).getLetter();
        }
        return "";
    }

    public String getTurn() {
        return turn;
    }

    public String getState() {
        return state;
    }

    public List<String> getPieces() {
        return pieces;
    }

    public void clearPiece() {
        int piecesSize = pieces.size();
        pieces.clear();
        for (int i = 0; i < piecesSize; i++) {
            pieces.add("");
        }
    }
}
