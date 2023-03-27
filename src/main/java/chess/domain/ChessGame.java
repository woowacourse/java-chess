package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;

import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void moveWhitePiece(Player player, Position sourcePosition, Position targetPosition) {
        ChessPiece chessPiece = player.findChessPiece(chessBoard, sourcePosition);
        Movement movement = player.findMovement(chessPiece, sourcePosition, targetPosition);
        if (player.canMoveSourcePiece(chessBoard, chessPiece, movement)) {
            chessBoard.movePiece(chessPiece, targetPosition);
            chessBoard.removePiece(sourcePosition);
        }
    }

    public void moveBlackPiece(Player player, Position sourcePosition, Position targetPosition) {
        ChessPiece chessPiece = player.findChessPiece(chessBoard, sourcePosition);
        Movement movement = player.findMovement(chessPiece, sourcePosition, targetPosition);
        if (player.canMoveSourcePiece(chessBoard, chessPiece, movement)) {
            chessBoard.movePiece(chessPiece, targetPosition);
            chessBoard.removePiece(sourcePosition);
        }
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public Result calculateWhiteResult(Player whitePlayer) {
        Map<Position, ChessPiece> playerPieces = whitePlayer.findPlayerPieces(chessBoard);
        return whitePlayer.calculateResult(playerPieces);
    }

    public Result calculateBlackResult(Player blackPlayer) {
        Map<Position, ChessPiece> playerPieces = blackPlayer.findPlayerPieces(chessBoard);
        return blackPlayer.calculateResult(playerPieces);
    }

    public boolean canEndGame(Player whitePlayer, Player blackPlayer) {
        return !whitePlayer.findPlayerPieces(chessBoard).containsValue(new King(Color.WHITE))
                || !blackPlayer.findPlayerPieces(chessBoard).containsValue(new King(Color.BLACK));
    }
}
