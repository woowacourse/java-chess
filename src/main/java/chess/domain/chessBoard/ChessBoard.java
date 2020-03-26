package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {
    private final Map<Position, ChessPiece> chessPieces;

    public ChessBoard(Map<Position, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public void move(Position source, Position target) {
        ChessPiece sourcePiece = chessPieces.get(source);
        if (Objects.isNull(chessPieces.get(source))) {
            throw new IllegalArgumentException("해당 위치에 체스 피스가 존재하지 않습니다.");
        }

        ChessPiece targetPiece = chessPieces.get(target);
        if (sourcePiece.canMove(source, target)) {
            checkTargetEmptyOrSamePieceColor(sourcePiece, targetPiece);
            moveFrom(source, target, sourcePiece);
        }
        throw new IllegalArgumentException("체스 피스가 이동할 수 없는 경로입니다.");
    }

    private void moveFrom(Position source, Position target, ChessPiece sourcePiece) {
        if (!sourcePiece.canLeap()) {
            MoveDirection sourceToTargetDirection = findDirectionOf(source, target);
            Position nextCheckPosition = sourceToTargetDirection.move(source);
            checkSourceToTargetPath(target, sourceToTargetDirection, nextCheckPosition);
        }
        chessPieces.put(target, sourcePiece);
        chessPieces.remove(source);
    }

    private void checkSourceToTargetPath(Position target, MoveDirection sourceToTargetDirection, Position nextCheckPosition) {
        while (!nextCheckPosition.equals(target) && checkNextPositionIsEmpty(nextCheckPosition)) {
            nextCheckPosition = sourceToTargetDirection.move(nextCheckPosition);
        }
    }

    private boolean checkNextPositionIsEmpty(Position nextCheckPosition) {
        if (chessPieces.containsKey(nextCheckPosition)) {
            throw new IllegalArgumentException("이동 경로에 체스 피스가 존재합니다.");
        }
        return true;
    }

    private void checkTargetEmptyOrSamePieceColor(ChessPiece sourcePiece, ChessPiece targetPiece) {
        if (Objects.isNull(targetPiece)) {
            return;
        }
        if (sourcePiece.isSamePieceColor(targetPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 같은 색의 체스 피스가 존재합니다.");
        }
    }

    private MoveDirection findDirectionOf(Position source, Position target) {
        return Arrays.stream(MoveDirection.values())
                .filter(moveDirection -> moveDirection.isSameDirection(source, target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("체스 피스가 갈 수 없는 위치를 입력하였습니다."));
    }
}
