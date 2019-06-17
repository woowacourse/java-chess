package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static chess.domain.BoardCellState.*;


public class ChessBoard {
    private final List<List<BoardCellState>> boardState;

    public ChessBoard() {
        this.boardState = initChessBoard();
    }

    private List<List<BoardCellState>> initChessBoard() {
        List<List<BoardCellState>> initState = new ArrayList<>();
        initState.addAll(initBlackState());
        initState.addAll(initNoneState());
        initState.addAll(initWhiteState());

        return initState;
    }

    private List<List<BoardCellState>> initWhiteState() {
        return Arrays.asList(
                Arrays.asList(PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE),
                Arrays.asList(ROOK_WHITE, KNIGHT_WHITE, BISHOP_WHITE, QUEEN_WHITE, KING_WHITE, BISHOP_WHITE, KNIGHT_WHITE, ROOK_WHITE)
        );
    }

    private List<List<BoardCellState>> initNoneState() {
        return Arrays.asList(
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE)
        );
    }

    private List<List<BoardCellState>> initBlackState() {
        return Arrays.asList(
                Arrays.asList(ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK, KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK),
                Arrays.asList(PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK)
        );

    }

    public List<List<BoardCellState>> getBoard() {
        return boardState.stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }
}
