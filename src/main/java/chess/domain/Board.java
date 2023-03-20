package chess.domain;

import chess.domain.dto.PieceResponse;
import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final int RANK_SIZE = 8;
    private final Map<Position, Piece> piecePosition;

    public Board(Map<Position, Piece> pieces) {
        piecePosition = new HashMap<>(pieces);
    }

    public void movePiece(Position origin, Position destination) {
        validateMoveRequest(origin, destination);
        Piece targetPiece = piecePosition.get(origin);
        if (!targetPiece.canMove(origin, destination, piecePosition.get(destination))) {
            throw new IllegalPieceMoveException();
        }
        piecePosition.put(destination, targetPiece);
        piecePosition.put(origin, EmptyPiece.getInstance());
    }

    private void validateMoveRequest(Position origin, Position destination) {
        if (piecePosition.get(origin) == EmptyPiece.getInstance()) {
            throw new IllegalPieceMoveException();
        }
        if (!piecePosition.get(origin).canJump()) {
            checkPath(origin, destination);
        }
    }

    private void checkPath(Position origin, Position destination) {
        List<Position> straightPath = origin.createStraightPath(destination);
        for (Position position : straightPath) {
            if (piecePosition.get(position) != EmptyPiece.getInstance()) {
                throw new IllegalPieceMoveException();
            }
        }
    }

    public List<List<PieceResponse>> getPiecePosition() {
        List<List<PieceResponse>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceResponse> pieceResponses = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(piecePosition::get)
                    .map(PieceResponse::from)
                    .collect(Collectors.toList());
            response.add(pieceResponses);
        }
        return response;
    }
}
