package chess.model.dto;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.ChessGame;
import chess.model.domain.piece.Piece;
import chess.model.domain.state.MoveState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.NullChecker;

public class ChessGameDto {

    private final List<String> pieces;
    private final String turn;
    private final String state;

    public ChessGameDto(ChessGame chessGame) {
        this(chessGame, MoveState.EMPTY);
    }

    public ChessGameDto(ChessGame chessGame, MoveState moveState) {
        NullChecker.validateNotNull(chessGame, moveState);
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        List<String> pieces = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            printRankRaw(pieces, board, rank);
        }
        this.pieces = pieces;
        this.turn = chessGame.getGameTurn().getName();
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
