package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {
    private static final int RANK_BLACK_PAWN_INIT = 7;
    private static final int RANK_WHITE_PAWN_INIT = 2;
    private static final Map<BoardSquare, Piece> INITIAL_BOARD;

    static {
        Map<BoardSquare, Piece> initialBoard = new HashMap<>();
        initialBoard.put(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK));

        initialBoard.put(BoardSquare.of("b1"), Knight.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("b8"), Knight.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK));

        initialBoard.put(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK));

        initialBoard.put(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("e8"), Queen.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("d8"), King.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE));

        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            initialBoard.put(BoardSquare.of(String.valueOf(file) + RANK_BLACK_PAWN_INIT)
                , Pawn.getPieceInstance(Color.BLACK));
            initialBoard.put(BoardSquare.of(String.valueOf(file) + RANK_WHITE_PAWN_INIT)
                , Pawn.getPieceInstance(Color.WHITE));
        }

        INITIAL_BOARD = Collections.unmodifiableMap(initialBoard);
    }

    private Map<BoardSquare, Piece> chessBoard = new HashMap<>();
    private Color gameTurn = Color.WHITE;

    public ChessBoard() {
        for (BoardSquare boardSquare : INITIAL_BOARD.keySet()) {
            chessBoard.put(boardSquare, INITIAL_BOARD.get(boardSquare));
        }
    }

    public static boolean isInitialPoint(BoardSquare boardSquare, Piece piece) {
        return INITIAL_BOARD.containsKey(boardSquare) && INITIAL_BOARD.get(boardSquare) == piece;
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean movePieceWhenCanMove(List<BoardSquare> boardSquares) {
        if (canMove(boardSquares)) {
            movePiece(boardSquares);
            return true;
        }
        return false;
    }

    private boolean canMove(List<BoardSquare> boardSquares) {
        BoardSquare before = boardSquares.get(0);
        BoardSquare after = boardSquares.get(1);
        if (!chessBoard.containsKey(before) || !chessBoard.get(before).isSameColor(gameTurn)) {
            return false;
        }
        return chessBoard.get(before).getCheatSheet(before, chessBoard).contains(after);
    }

    private void movePiece(List<BoardSquare> boardSquares) {
        BoardSquare before = boardSquares.get(0);
        BoardSquare after = boardSquares.get(1);
        Piece currentPiece = chessBoard.remove(before);
        chessBoard.put(after, currentPiece);
        gameTurn = gameTurn.nextTurnIfEmptyMySelf();
    }

    public boolean isKingCaptured() {
        return chessBoard.values().stream()
            .filter(piece -> piece instanceof King)
            .count() != Color.values().length;
    }

    public TeamScore getTeamScore() {
        return new TeamScore(chessBoard.values(), countPawnSameFileByColor());
    }

    public Color getWinnerTurn() {
        return gameTurn.previousTurnIfEmptyMySelf();
    }

    private Map<Color, Integer> countPawnSameFileByColor() {
        Map<Color, Integer> pawnSameFileByColor = new HashMap<>();
        for (Color color : Color.values()) {
            List<BoardSquare> pawnSquare = getSquareIfSameColorPawn(color);
            pawnSameFileByColor.put(color, getCountSameFile(pawnSquare));
        }
        return pawnSameFileByColor;
    }

    private int getCountSameFile(List<BoardSquare> pawnSquare) {
        int count = 0;
        for (BoardSquare boardSquare : pawnSquare) {
            count += pawnSquare.stream()
                .filter(square -> boardSquare.isSameFile(square) && boardSquare != square)
                .count();
        }
        return count;
    }

    private List<BoardSquare> getSquareIfSameColorPawn(Color color) {
        return chessBoard.keySet().stream()
            .filter(square -> chessBoard.get(square) == Pawn.getPieceInstance(color))
            .collect(Collectors.toList());
    }
}