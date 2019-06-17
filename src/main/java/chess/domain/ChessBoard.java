package chess.domain;

import chess.exception.AllyExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static chess.domain.PieceType.*;


public class ChessBoard {
    private final List<List<ChessPiece>> boardState;

    public ChessBoard(List<List<ChessPiece>> initialBoardState) {
        this.boardState = initialBoardState;
    }

    private List<List<PieceType>> initChessBoard() {
        List<List<PieceType>> initState = new ArrayList<>();
//        initState.addAll(initBlackState());
//        initState.addAll(initNoneState());
//        initState.addAll(initWhiteState());

        return initState;
    }

//    private List<List<PieceType>> initWhiteState() {
//        return Arrays.asList(
//                Arrays.asList(PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE, PAWN_WHITE),
//                Arrays.asList(ROOK_WHITE, KNIGHT_WHITE, BISHOP_WHITE, QUEEN_WHITE, KING_WHITE, BISHOP_WHITE, KNIGHT_WHITE, ROOK_WHITE)
//        );
//    }

//    private List<List<PieceType>> initNoneState() {
//        return Arrays.asList(
//                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
//                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
//                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE),
//                Arrays.asList(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE)
//        );
//    }

//    private List<List<PieceType>> initBlackState() {
//        return Arrays.asList(
//                Arrays.asList(ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK, KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK),
//                Arrays.asList(PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK)
//        );
//
//    }

    public List<List<PieceType>> getBoard() {
        return boardState.stream()
                .map(row -> row.stream()
                        .map(ChessPiece::getType)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public void move(ChessCoordinate from, ChessCoordinate to) {
        ChessPiece srcState = this.boardState.get(from.getY().getIndex())
                .get(from.getX().getIndex());
        ChessPiece toState = this.boardState.get(to.getY().getIndex())
                .get(to.getX().getIndex());

        List<ChessCoordinate> movableList = srcState.getMovableCoordinates(this, from);

        movableList.stream()
                .filter(coord -> coord.getX() == to.getX())
                .filter(coord -> coord.getY() == to.getY())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다"));

        boardState.get(to.getY().getIndex()).set(to.getX().getIndex(), srcState);
        boardState.get(from.getY().getIndex()).set(from.getX().getIndex(), EmptyCell.getInstance());

        assertNotAlly(srcState, toState);
    }

    private void assertNotAlly(ChessPiece srcState, ChessPiece toState) {
        if (srcState.getType().getTeam() == toState.getType().getTeam()) {
            throw new AllyExistException("같은편의 위치로는 이동할 수 없습니다.");
        }
    }

    Team getTeamAt(ChessXCoordinate x, ChessYCoordinate y) {
        return this.boardState.get(y.getIndex())
                .get(x.getIndex()).getType().getTeam();
    }
}
