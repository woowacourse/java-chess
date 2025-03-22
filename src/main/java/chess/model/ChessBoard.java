package chess.model;

import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Position;

import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    Map<Position, Piece> positionByPieceBoard;

    public ChessBoard(Map<Position, Piece> boardData) {
        this.positionByPieceBoard = boardData;
    }

    public void updatePosition(final Position source, final Position destination, final Color teamType) {
        validatePositionAndTeam(source, teamType);
        validatePieceCanMove(source, destination);

        movePieceToDestination(source, destination);
    }

    private void validatePositionAndTeam(final Position source, final Color color) {
        if (!positionByPieceBoard.containsKey(source) || !positionByPieceBoard.get(source).getColor().equals(color)) {
            throw new IllegalArgumentException("scr 좌표에 기물이 존재하지 않거나, 해당 팀의 기물이 아닙니다.");
        }
    }

    private void validatePieceCanMove(final Position source, final Position destination) {
        final Piece piece = positionByPieceBoard.get(source);

        if (!piece.canMove(source, destination, this)) {
            throw new IllegalArgumentException("해당 기물은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void movePieceToDestination(final Position source, final Position destination) {
        positionByPieceBoard.put(destination, positionByPieceBoard.get(source));
        positionByPieceBoard.remove(source);
    }


    public boolean isExistPosition(Position position) {
        return positionByPieceBoard.containsKey(position);
    }

    public Optional<Piece> findPieceByPosition(final Position position) {
        return Optional.ofNullable(positionByPieceBoard.get(position));
    }

    public Map<Position, Piece> getPositionByPieceBoard() {
        return positionByPieceBoard;
    }
}
