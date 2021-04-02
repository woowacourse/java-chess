package chess.domain;

import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.MovementDto;
import chess.domain.dto.PieceDto;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.AlphaColumns;
import chess.domain.position.NumberRows;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Running;
import chess.domain.team.BlackSet;
import chess.domain.team.PieceSet;
import chess.domain.team.WhiteSet;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        this.gameState = new Running(chessBoard, TeamColor.WHITE);
    }

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.whitePieces = initWhiteSet(chessBoard);
        this.blackPieces = initBlackSet(chessBoard);
        this.gameState = new Running(chessBoard, TeamColor.WHITE);
    }

    private PieceSet initBlackSet(Map<Position, Piece> chessBoard) {
        List<Piece> blacks = chessBoard.values().stream()
                .filter(piece -> piece.getColor() == TeamColor.BLACK)
                .collect(Collectors.toList());
        return new BlackSet(blacks);

    }

    private PieceSet initWhiteSet(Map<Position, Piece> chessBoard) {
        List<Piece> whites = chessBoard.values().stream()
                .filter(piece -> piece.getColor() == TeamColor.WHITE)
                .collect(Collectors.toList());
        return new WhiteSet(whites);
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
            for (AlphaColumns alpha : AlphaColumns.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRows.getInstance(i)), blacks.next());
            }
        }
    }

    private void setWhitePieces(Map<Position, Piece> chessBoard) {
        Iterator<Piece> whites = whitePieces.values();
        for (int i = WHITE_NOT_PAWN_LINE; i <= WHITE_PAWN_LINE; i++) {
            for (AlphaColumns alpha : AlphaColumns.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRows.getInstance(i)), whites.next());
            }
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return gameState.getChessBoard();
    }

    public void move(MovementDto movementDto) {
        move(movementDto.getSource(), movementDto.getTarget());
    }

    public void move(String source, String target) {
        gameState = gameState.move(Position.valueOf(source), Position.valueOf(target));
    }

    public Result result() {
        return gameState.result(blackPieces, whitePieces);
    }

    public boolean isPlaying() {
        return gameState.isRunning();
    }

    public void terminate() {
        gameState = gameState.terminate();
    }

    public Map<String, PieceDto> getChessBoardDto() {
        return new ChessBoardDto(gameState.getChessBoard()).getChessBoard();
    }

}

