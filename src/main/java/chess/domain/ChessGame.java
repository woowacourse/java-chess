package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import chess.dto.ChessStatusDto;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private ChessBoard chessBoard;

    private ChessGame() {
        this.chessBoard = ChessBoard.initialize();
    }

    public static ChessGame create() {
        return new ChessGame();
    }

    public ChessBoardDto getChessBoardInformation() {
        return ChessBoardDto.of(chessBoard.getMapInformation());
    }

    public boolean isGameEnd() {
        return chessBoard.isKingDie();
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        chessBoard.move(sourcePosition, targetPosition);
    }

    public ChessStatusDto getStatusInformation() {
        return ChessStatusDto.of(chessBoard);
    }

    public void initialze() {
        this.chessBoard = ChessBoard.initialize();
    }
}
