package chess.game;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.ScoreCalculator;
import chess.domain.Status;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class WebChessGame {

    private ChessBoard chessBoard;
    private Color turn;

    public WebChessGame() {
        this.turn = Color.WHITE;
    }

    private WebChessGame(ChessBoard chessBoard, Color turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    public static WebChessGame of(ChessBoard chessBoard, String turnValue) {
        return new WebChessGame(chessBoard, Color.of(turnValue));
    }

    public void start() {
        initChessBoard();
    }

    private void initChessBoard() {
        PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
        chessBoard = new ChessBoard(piecesGenerator);
        turn = Color.WHITE;
    }

    public void end() {
        turn = Color.EMPTY;
    }

    public Status status() {
        return new Status(calculateScoreByColor(Color.WHITE), calculateScoreByColor(Color.BLACK));
    }

    private double calculateScoreByColor(Color color) {
        List<List<Piece>> piecesOnColumns = chessBoard.getPiecesOnColumns(color);
        ScoreCalculator calculator = ScoreCalculator.getInstance();
        return calculator.calculateColumns(piecesOnColumns);
    }

    public void move(String source, String target) {
        validateForStatusAndMove();

        GameCommand moveCommand = new GameCommand("move", source, target);
        validatePlayerTurn(moveCommand);
        chessBoard.move(moveCommand);
        checkEndAndDo();
        changeTurn();
    }

    private void validateForStatusAndMove() {
        validateInitBoard();
        validateEndChessBoard();
    }

    private void validateInitBoard() {
        if (chessBoard == null) {
            throw new IllegalStateException("체스판이 초기화되지 않았습니다.");
        }
    }

    private void validateEndChessBoard() {
        if (turn == Color.EMPTY) {
            throw new IllegalArgumentException("현재 판이 종료되서 더 이상 진행할 수 없습니다.");
        }
    }

    private void validatePlayerTurn(GameCommand gameCommand) {
        if (chessBoard.getPositionColor(gameCommand.getFromPosition()) != turn) {
            throw new IllegalArgumentException("당신의 차례가 아닙니다.");
        }
    }

    private void checkEndAndDo() {
        if (chessBoard.isEnd()) {
            turn = Color.EMPTY;
        }
    }

    private void changeTurn() {
        turn = turn.getReverseColor();
    }

    public Map<Position, Piece> getBoard() {
        return chessBoard.getPieces();
    }

    public String getTurn() {
        return turn.getName();
    }
}
