package chess.domain;

import chess.domain.piece.ChessPiece;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color color;
    private boolean isPlaying;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.color = Color.WHITE;
    }

    public void startGame() {
        this.isPlaying = true;
    }

    public void endGame() {
        this.isPlaying = false;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        validateCanStart();
        ChessPiece chessPiece = chessBoard.getChessPiece(sourcePosition);
        validateColor(chessPiece);
        movePiece(sourcePosition, targetPosition, chessPiece);
        changeColor();
        validateCanFinish();
    }

    private void validateCanStart() {
        if (!isPlaying) {
            throw new IllegalArgumentException("[ERROR] 우선 게임을 시작해야 합니다.");
        }
    }

    private void validateColor(ChessPiece chessPiece) {
        if (!this.color.equals(chessPiece.getColor())) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 이동할 수 없습니다.");
        }
    }

    private void movePiece(Position sourcePosition, Position targetPosition, ChessPiece chessPiece) {
        Movement movement = findMovement(chessPiece, sourcePosition, targetPosition);
        if (chessPiece.isMovable(movement, chessBoard)) {
            chessBoard.movePiece(chessPiece, targetPosition);
            chessBoard.removePiece(sourcePosition);
        }
    }

    private Movement findMovement(ChessPiece chessPiece, Position sourcePosition, Position targetPosition) {
        Direction direction = chessPiece.findMovableDirection(sourcePosition, targetPosition);
        int distance = chessPiece.findDistance(direction, sourcePosition, targetPosition);
        return new Movement(sourcePosition, direction, distance);
    }

    private void changeColor() {
        this.color = color.getOppositeColor();
    }

    private void validateCanFinish() {
        if (!chessBoard.isWhiteKing() || !chessBoard.isBlackKing()) {
            this.isPlaying = false;
        }
    }

    public List<Result> calculateResults() {
        Result whiteResult = Result.of(chessBoard.getChessPiecesByColor(Color.WHITE), Color.WHITE);
        Result blackResult = Result.of(chessBoard.getChessPiecesByColor(Color.BLACK), Color.BLACK);
        return List.of(whiteResult, blackResult);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
