package domain.piece.jumper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Knight extends Jumper {
    private static final List<List<Integer>> POSSIBLE_MOVES = List.of(
        List.of(1, 2),
        List.of(1, -2),
        List.of(-1, 2),
        List.of(-1, -2),
        List.of(2, 1),
        List.of(2, -1),
        List.of(-2, 1),
        List.of(-2, -1)
    );

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare) {
        int currentFile = currentSquare.toCoordinate().get(FILE_INDEX);
        int currentRank = currentSquare.toCoordinate().get(RANK_INDEX);

        return POSSIBLE_MOVES.stream()
            .map(possibleMove -> new Square(currentFile + possibleMove.get(FILE_INDEX),
                currentRank + possibleMove.get(RANK_INDEX)))
            .collect(Collectors.toList());
    }

    @Override
    public boolean canMove(Map<Square, Piece> squares, Square targetSquare) {
        return squares.containsKey(targetSquare);
    }
}
