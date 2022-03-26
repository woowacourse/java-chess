package domain.chessboard;

import domain.piece.Piece;
import domain.position.Position;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    Map<Position, Piece> board;

    public ChessBoard(final BoardGenerator boardGenerator) {
        this.board = boardGenerator.generate();
    }

    public String getSymbol(final Position position) {
        Piece piece = board.get(position);
        if (piece == null) {
            return ".";
        }
        return piece.symbol();
    }

    // 이동 명령 (출발지, 목적지)
    // 기본
    public void move(final Position source, final Position target) {
        // 1. 기물별로 Target이 갈 수 있는지 검증하는 기능
        validateTargetPosition(source, target);
        // 2-1. Panw외의 기물들이 Target으로 이동할 수 있는지 검증하는 기능
        validateTargetRoute(source, target);
        // 2-2. Pawn이 Target으로 이동할 수 있는지 검증하는 기능

        movePiece(source, target);
    }

    // 1. 기물별로 Target이 갈 수 있는지 검증하는 기능
    private void validateTargetPosition(final Position source, final Position target) {
        if (!board.get(source).isAvailableMove(source, target)) {
            throw new IllegalArgumentException("[ERROR] 선택한 기물이 이동할 수 없는 위치입니다.");
        }
    }

    // 2-1. Panw외의 기물들이 Target으로 이동할 수 있는지 검증하는 기능
    private void validateTargetRoute(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        // 2-1-1. 목적지의 Piece가 다른 색이거나 공백이어야 한다.
        if (targetPiece != null && targetPiece.isSamePlayer(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] 선택한 위치는 같은 색 기물이 위치하여 이동할 수 없습니다.");
        }
        // 2-1-2. 목적지까지 가는 길은 null 이어야한다.
        List<Position> availablePosition = sourcePiece.getAvailablePositions(target);

        for (Position position : availablePosition) {
            if (position.equals(target)) {
                return;
            }
            if (board.get(position) != null) {
                throw new IllegalArgumentException("[ERROR] 선택한 위치는 다른 기물에 의해 이동할 수 없습니다.");
            }
        }
    }

    private void movePiece(Position source, Position target) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.put(source, null);
    }
}
