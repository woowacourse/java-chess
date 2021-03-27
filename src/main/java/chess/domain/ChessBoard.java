package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.player.BlackSet;
import chess.domain.player.PieceSet;
import chess.domain.player.WhiteSet;
import chess.domain.position.AlphaColumn;
import chess.domain.position.NumberRow;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Running;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    public static final int BLACK_NOT_PAWN_LINE = 7;
    public static final int BLACK_PAWN_LINE = 8;
    public static final int WHITE_NOT_PAWN_LINE = 1;
    public static final int WHITE_PAWN_LINE = 2;

    private final PieceSet whitePieces;
    private final PieceSet blackPieces;
    private GameState gameState;

    public ChessBoard() {
        this.whitePieces = new WhiteSet();
        this.blackPieces = new BlackSet();
        final Map<Position, Piece> chessBoard = initBoard();
        this.gameState = new Running(chessBoard);
    }

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.whitePieces = new WhiteSet();
        this.blackPieces = new BlackSet();
        this.gameState = new Running(chessBoard);
    }

    private Map<Position, Piece> initBoard() {
        Map<Position, Piece> chessBoard = new LinkedHashMap<>();
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
        setPieces(chessBoard);
        return chessBoard;
    }

    private void setPieces(Map<Position, Piece> chessBoard) {
        setWhitePieces(chessBoard);
        setBlackPieces(chessBoard);
    }

    private void setBlackPieces(Map<Position, Piece> chessBoard) {
        Iterator<Piece> blacks = blackPieces.values();
        for (int i = BLACK_PAWN_LINE; i >= BLACK_NOT_PAWN_LINE; i--) {
            for (AlphaColumn alpha : AlphaColumn.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRow.valueOf(i)), blacks.next());
            }
        }
    }

    private void setWhitePieces(Map<Position, Piece> chessBoard) {
        Iterator<Piece> whites = whitePieces.values();
        for (int i = WHITE_NOT_PAWN_LINE; i <= WHITE_PAWN_LINE; i++) {
            for (AlphaColumn alpha : AlphaColumn.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRow.valueOf(i)), whites.next());
            }
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return gameState.getChessBoard();
    }

    public void move(String source, String target) {
        validatePosition(source, target);
        gameState = gameState.move(Position.valueOf(source), Position.valueOf(target));
    }

    private void validatePosition(String source, String target) {
        validateSamePosition(source, target);
        if (gameState.containsKey(Position.valueOf(source)) &&
                gameState.containsKey(Position.valueOf(target))) {
            return;
        }
        throw new IllegalArgumentException("잘못된 좌표입니다.");
    }

    private void validateSamePosition(String source, String target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 좌표는 불가능합니다.");
        }
    }

    public Result result() {
        return gameState.result(blackPieces, whitePieces);
    }

    public boolean isPlaying() {
        return gameState instanceof Running;
    }

    public void terminate() {
        gameState = gameState.terminate();
    }

}

