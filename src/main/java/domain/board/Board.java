package domain.board;

import domain.piece.Empty;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Position;
import java.util.Map;

public class Board {
    private Color turn;
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.turn = Color.WHITE;
        this.squares = squares;
    }

    public void move(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
//        validateNoPieceExistBetween(source, target);
//        validateTurn(currentPiece);

        if (currentPiece.isReachable(source, target, targetPiece)) {
            updateBoard(source, target, currentPiece);
            switchTurn();
        }
    }

//    private void validateNoPieceExistBetween(final Position source, final Position target) {
//        final List<Position> path = source.findPathWithOutEndPoints(target);
//        final boolean isPieceExistOnPath = path.stream()
//                .map(squares::get)
//                .anyMatch(Piece::isNotNone);
//
//        if (isPieceExistOnPath) {
//            throw new IllegalArgumentException("이동할 수 없는 경로입니다");
//        }
//    }

//    private void validateTurn(final Piece currentPiece) {
//        if (currentPiece.isSameColor(turn)) {
//            throw new IllegalArgumentException("현재 차례가 아닙니다.");
//        }
//    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        squares.remove(target);

        if (currentPiece.isInitPawn()) {
            squares.put(target, new Pawn(turn));
        } else {
            squares.put(target, currentPiece);
        }

        squares.put(source, Empty.INSTANCE);
    }

    private void switchTurn() {
        turn = turn.reverse();
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
