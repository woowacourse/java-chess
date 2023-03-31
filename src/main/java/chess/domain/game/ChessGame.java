package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.domain.piece.type.Piece;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessGame {

    private static final Color teamBlack = Color.BLACK;
    private static final Color teamWhite = Color.WHITE;

    private final ChessBoard chessBoard;
    private Color turn;
    private StateOfChessGame stateOfChessGame;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.turn = Color.WHITE;
        this.stateOfChessGame = StateOfChessGame.STARTED;
    }

    public void move(Position start, Position end) {
        if(!stateOfChessGame.isStarted()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        checkCorrectTurnByColor(start);
        Piece pieceToBeCaught = chessBoard.findPieceInBoardByPosition(end);
        chessBoard.move(start, end);
        if(pieceToBeCaught.getPieceType() == PieceType.KING) {
            finishGame();
            return;
        }
        turn = turn.getOpponent();
    }

    private void checkCorrectTurnByColor(Position start) {
        Piece startPiece = chessBoard.findPieceInBoardByPosition(start);
        if(!startPiece.isSameColor(turn)) {
            throw new IllegalArgumentException("상대편의 기물을 움직일 수 없습니다");
        }
    }

    public  Map<Color, Double> status() {
        Map<Color, Double> status = new LinkedHashMap<>();
        status.put(teamBlack, findScoreByColor(teamBlack));
        status.put(teamWhite, findScoreByColor(teamWhite));
        return status;
    }

    private double findScoreByColor(Color color) {
        return chessBoard.findScoreOfPiecesByColor(color) - findOverloadPawnScore(color);
    }
    private double findOverloadPawnScore(Color color) {
        Map<Column, Long> pawnCountByColumn = chessBoard.findPieceCountOfColumn(color, PieceType.PAWN);
        return pawnCountByColumn.values().stream()
                .filter(pawnCountOfColumn -> pawnCountOfColumn > 1)
                .mapToDouble(pawnCountOfColumn -> pawnCountOfColumn *0.5)
                .sum();
    }

    public void finishGame() {
        stateOfChessGame = StateOfChessGame.FINISHED;
    }

    public void end() {
        stateOfChessGame = StateOfChessGame.STARTED;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard.getChessBoard();
    }

    public StateOfChessGame getStatus() {
        return stateOfChessGame;
    }

    public Color getTurn() {
        return turn;
    }
}