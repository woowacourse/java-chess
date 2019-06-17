package chess.domain;

import chess.exception.AllyExistException;

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

    public void move(ChessCoordinate from, ChessCoordinate to) {
        BoardCellState srcState = this.boardState.get(from.getY().getIndex())
                .get(from.getX().getIndex());
        BoardCellState toState = this.boardState.get(to.getY().getIndex())
                .get(to.getX().getIndex());

        assertNotAlly(srcState, toState);

        this.boardState.get(to.getY().getIndex())
                .set(to.getX().getIndex(), srcState);

        this.boardState.get(from.getY().getIndex())
                .set(from.getX().getIndex(), NONE);
    }

    private void assertNotAlly(BoardCellState srcState, BoardCellState toState) {
        if (srcState.getGroup() == toState.getGroup()) {
            throw new AllyExistException("같은편의 위치로는 이동할 수 없습니다.");
        }
    }
}
