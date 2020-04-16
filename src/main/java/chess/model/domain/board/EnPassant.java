package chess.model.domain.board;

import chess.model.domain.piece.Color;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.state.MoveOrder;
import chess.model.domain.state.MoveSquare;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EnPassant {

    private Map<BoardSquare, BoardSquare> enPassantsToAfterSquares;

    public EnPassant() {
        this(new HashMap<>());
    }

    public EnPassant(Map<BoardSquare, BoardSquare> enPassantsToAfterSquares) {
        this.enPassantsToAfterSquares = enPassantsToAfterSquares;
    }

    public void removeEnPassant(MoveSquare moveSquare) {
        enPassantsToAfterSquares.remove(moveSquare.get(MoveOrder.AFTER));
        BoardSquare boardSquareBefore = moveSquare.get(MoveOrder.BEFORE);
        if (enPassantsToAfterSquares.containsValue(boardSquareBefore)) {
            enPassantsToAfterSquares.remove(enPassantsToAfterSquares.keySet().stream()
                .filter(
                    boardSquare -> enPassantsToAfterSquares.get(boardSquare) == boardSquareBefore)
                .findFirst()
                .orElseThrow(IllegalAccessError::new));
        }
    }

    public void addIfPawnSpecialMove(Piece piece, MoveSquare moveSquare) {
        if (isPawnSpecialMove(piece, moveSquare)) {
            BoardSquare betweenWhenJumpRank = moveSquare.getBetweenWhenJumpRank();
            BoardSquare afterSquare = moveSquare.get(MoveOrder.AFTER);
            enPassantsToAfterSquares.put(betweenWhenJumpRank, afterSquare);
        }
    }

    public static boolean isPawnSpecialMove(Piece piece, MoveSquare moveSquare) {
        return piece instanceof Pawn && moveSquare.isJumpRank();
    }

    public Map<BoardSquare, Piece> getEnPassantBoard(Color color) {
        if (enPassantsToAfterSquares.isEmpty()) {
            return new HashMap<>();
        }
        return enPassantsToAfterSquares.keySet().stream()
            .filter(boardSquare -> !getRankByPawn(boardSquare).isSameColor(color))
            .collect(Collectors.toMap(boardSquare -> boardSquare, this::getRankByPawn));
    }

    private Piece getRankByPawn(BoardSquare boardSquare) {
        if (boardSquare.isSameRank(Rank.THIRD)) {
            return Pawn.getPieceInstance(Color.WHITE);
        }
        if (boardSquare.isSameRank(Rank.SIXTH)) {
            return Pawn.getPieceInstance(Color.BLACK);
        }
        throw new IllegalArgumentException("인자 오류");
    }

    public Set<BoardSquare> getEnPassants() {
        return enPassantsToAfterSquares.keySet();
    }

    public boolean hasOtherEnpassant(BoardSquare boardSquare, Color gameTurn) {
        return enPassantsToAfterSquares.containsKey(boardSquare)
            && !getRankByPawn(boardSquare).isSameColor(gameTurn);
    }

    public BoardSquare getAfterSquare(BoardSquare enPassantSquare) {
        return enPassantsToAfterSquares.get(enPassantSquare);
    }

}
