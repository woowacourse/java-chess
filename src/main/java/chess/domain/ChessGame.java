package chess.domain;

import chess.domain.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color color;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.color = Color.WHITE;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        ChessPiece chessPiece = chessBoard.getChessPiece(sourcePosition);
        validateColor(chessPiece);
        Movement movement = findMovement(chessPiece, sourcePosition, targetPosition);
        if (chessPiece.isMovable(movement, chessBoard)) {
            chessBoard.movePiece(chessPiece, targetPosition);
            chessBoard.removePiece(sourcePosition);
        }
        this.color = color.getOppositeColor();
    }

    private void validateColor(ChessPiece chessPiece) {
        if (!this.color.equals(chessPiece.getColor())) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 이동할 수 없습니다.");
        }
    }

    private Movement findMovement(ChessPiece chessPiece, Position sourcePosition, Position targetPosition) {
        Direction direction = chessPiece.findMovableDirection(sourcePosition, targetPosition);
        int distance = chessPiece.findDistance(direction, sourcePosition, targetPosition);
        return new Movement(sourcePosition, direction, distance);
    }

    public List<Result> calculateResults() {
        Result whiteResult = Result.of(chessBoard.getChessPiecesByColor(Color.WHITE), Color.WHITE);
        Result blackResult = Result.of(chessBoard.getChessPiecesByColor(Color.BLACK), Color.BLACK);
        return List.of(whiteResult, blackResult);
    }

    public boolean canEndGame() {
        return (!chessBoard.isWhiteKing() || !chessBoard.isBlackKing());
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard.getChessBoard();
    }
}
