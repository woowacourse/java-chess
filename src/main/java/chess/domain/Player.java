package chess.domain;

import chess.domain.piece.ChessPiece;

import java.util.Map;

public class Player {

    private final Color color;

    public Player(Color color) {
        validateBlank(color);
        this.color = color;
    }

    private void validateBlank(Color white) {
        if (white.equals(Color.BLANK)) {
            throw new IllegalArgumentException("[ERROR] Blank로는 Player를 만들 수 없습니다.");
        }
    }

    public ChessPiece findChessPiece(ChessBoard chessBoard, Position position) {
        ChessPiece chessPiece = chessBoard.getChessPiece(position);
        validateColor(chessPiece);
        return chessPiece;
    }

    private void validateColor(ChessPiece chessPiece) {
        if (!chessPiece.getColor().equals(color)) {
            throw new IllegalArgumentException("[ERROR 상대편 기물은 이동할 수 없습니다.]");
        }
    }
    public Movement findMovement(ChessPiece chessPiece, Position sourcePosition, Position targetPosition) {
        Direction direction = chessPiece.findMovableDirection(sourcePosition, targetPosition);
        int distance = chessPiece.findDistance(direction, sourcePosition, targetPosition);
        return new Movement(sourcePosition, direction, distance);
    }

    public boolean canMoveSourcePiece(ChessBoard chessBoard, ChessPiece chessPiece, Movement movement) {
        return chessPiece.isMovable(movement, chessBoard);
    }

    public Map<Position, ChessPiece> findPlayerPieces(ChessBoard chessBoard) {
        return chessBoard.getChessPiecesByColor(this.color);
    }

    public Result calculateResult(Map<Position, ChessPiece> pieces) {
        return Result.of(pieces, this.color);
    }
}
