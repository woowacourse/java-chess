package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.state.MoveOrder;
import chess.domain.state.MoveSquare;
import chess.exceptions.ChangePawnException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<BoardSquare, Piece> chessBoard = new HashMap<>();
    private Color gameTurn = Color.WHITE;

    public ChessBoard() {
        for (ChessInitialSetting chessInitialSetting : ChessInitialSetting.values()) {
            chessBoard.put(chessInitialSetting.getBoardSquare(), chessInitialSetting.getPiece());
        }
    }

    public static boolean isInitialPoint(BoardSquare boardSquare, Piece piece) {
        return ChessInitialSetting.contains(boardSquare, piece);
    }

    public Map<BoardSquare, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean movePieceWhenCanMove(MoveSquare moveSquare) throws ChangePawnException {
        BoardSquare moveSquareBefore = moveSquare.get(MoveOrder.before);
        BoardSquare moveSquareAfter = moveSquare.get(MoveOrder.after);
        if (canMove(moveSquareBefore, moveSquareAfter)) {
            movePiece(moveSquareBefore, moveSquareAfter);
            checkChangePawnWhenReachFinish();
            gameTurn = gameTurn.nextTurnIfEmptyMySelf();
            return true;
        }
        return false;
    }

    private void checkChangePawnWhenReachFinish() throws ChangePawnException {
        if (isReachFinishPawn()) {
            BoardSquare finishPawnBoard = getFinishPawnBoard();
            throw new ChangePawnException(finishPawnBoard + "자리의 폰을 변경해야 합니다.");
        }
    }

    private BoardSquare getFinishPawnBoard() {
        return chessBoard.keySet().stream()
            .filter(boardSquare -> chessBoard.get(boardSquare) instanceof Pawn)
            .filter(BoardSquare::isStartRank)
            .findFirst()
            .orElseThrow(IllegalAccessError::new);
    }

    private boolean isReachFinishPawn() {
        return chessBoard.keySet().stream()
            .filter(boardSquare -> chessBoard.get(boardSquare) instanceof Pawn)
            .anyMatch(BoardSquare::isStartRank);
    }

    public boolean changeFinishPawn(Type hopeType) {
        if (isReachFinishPawn()) {
            chessBoard.put(getFinishPawnBoard(), getHopePiece(hopeType));
            gameTurn = gameTurn.nextTurnIfEmptyMySelf();
            return true;
        }
        return false;
    }

    private Piece getHopePiece(Type hopeType) {
        return Arrays.stream(ChessInitialSetting.values())
            .map(ChessInitialSetting::getPiece)
            .filter(piece -> piece.isSameType(hopeType))
            .filter(piece -> piece.isSameColor(gameTurn))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    private boolean canMove(BoardSquare moveSquareBefore, BoardSquare moveSquareAfter) {
        Piece movePieceBefore = chessBoard.get(moveSquareBefore);
        if (!chessBoard.containsKey(moveSquareBefore) || !movePieceBefore.isSameColor(gameTurn)) {
            return false;
        }
        return movePieceBefore.getCheatSheet(moveSquareBefore, chessBoard)
            .contains(moveSquareAfter);
    }

    private void movePiece(BoardSquare moveSquareBefore, BoardSquare moveSquareAfter) {
        Piece currentPiece = chessBoard.remove(moveSquareBefore);
        chessBoard.put(moveSquareAfter, currentPiece);
    }

    public boolean isKingCaptured() {
        return chessBoard.values().stream()
            .filter(piece -> piece instanceof King)
            .count() != Color.values().length;
    }

    public TeamScore getTeamScore() {
        return new TeamScore(chessBoard.values(), countPawnSameFileByColor());
    }

    public Color getWinnerTurn() {
        return gameTurn.previousTurnIfEmptyMySelf();
    }

    private Map<Color, Integer> countPawnSameFileByColor() {
        Map<Color, Integer> pawnSameFileCountByColor = new HashMap<>();
        for (Color color : Color.values()) {
            List<BoardSquare> pawnSquare = getSquareIfSameColorPawn(color);
            pawnSameFileCountByColor.put(color, getCountSameFile(pawnSquare));
        }
        return pawnSameFileCountByColor;
    }

    private int getCountSameFile(List<BoardSquare> pawnSquare) {
        int count = 0;
        for (BoardSquare boardSquare : pawnSquare) {
            count += pawnSquare.stream()
                .filter(square -> boardSquare.isSameFile(square) && boardSquare != square)
                .count();
        }
        return count;
    }

    private List<BoardSquare> getSquareIfSameColorPawn(Color color) {
        return chessBoard.keySet().stream()
            .filter(square -> chessBoard.get(square) == Pawn.getPieceInstance(color))
            .collect(Collectors.toList());
    }

    public boolean isNoPiece(MoveSquare MoveSquares) {
        return !chessBoard.containsKey(MoveSquares.get(MoveOrder.before));
    }

    public Color getGameTurn() {
        return gameTurn;
    }

    public boolean isNotMyTurn(MoveSquare MoveSquares) {
        if (isNoPiece(MoveSquares)) {
            return true;
        }
        return !chessBoard.get(MoveSquares.get(MoveOrder.before)).isSameColor(gameTurn);
    }
}