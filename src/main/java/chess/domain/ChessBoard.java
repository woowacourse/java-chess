package chess.domain;

import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.MovementDto;
import chess.domain.dto.PieceDto;
import chess.domain.piece.*;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.AlphaColumns;
import chess.domain.position.NumberRows;
import chess.domain.position.Position;
import chess.domain.state.GameState;
import chess.domain.state.Running;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {

    private GameState gameState;

    public ChessBoard() {
        final Map<Position, Piece> chessBoard = initBoard();
        this.gameState = new Running(chessBoard, TeamColor.WHITE);
    }

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.gameState = new Running(chessBoard, TeamColor.WHITE);
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
        chessBoard.put(Position.valueOf("a8"), new Rook(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("b8"), new Knight(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("c8"), new Bishop(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("d8"), new Queen(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("e8"), new King(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("f8"), new Bishop(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("g8"), new Knight(TeamColor.BLACK));
        chessBoard.put(Position.valueOf("h8"), new Rook(TeamColor.BLACK));
        for (AlphaColumns alpha : AlphaColumns.values()) {
            chessBoard.put(Position.valueOf(alpha, NumberRows.SEVEN), new Pawn(TeamColor.BLACK));
        }
    }

    private void setWhitePieces(Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.valueOf("a1"), new Rook(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("b1"), new Knight(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("c1"), new Bishop(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("d1"), new Queen(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("e1"), new King(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("f1"), new Bishop(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("g1"), new Knight(TeamColor.WHITE));
        chessBoard.put(Position.valueOf("h1"), new Rook(TeamColor.WHITE));
        for (AlphaColumns alpha : AlphaColumns.values()) {
            chessBoard.put(Position.valueOf(alpha, NumberRows.TWO), new Pawn(TeamColor.WHITE));
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
        return gameState.result();
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

