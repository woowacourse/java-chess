package chess.domain.player;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.domain.command.Command;
import chess.domain.command.Ready;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import chess.domain.position.Source;
import chess.domain.position.Target;
import chess.domain.state.State;
import chess.domain.state.StateFactory;
import chess.service.dto.GameStatusRequestDto;
import chess.service.dto.ScoreDto;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessGame {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private Command command;
    private boolean isGameOver = false;

    public ChessGame(final State white, final State black, final Command command) {
        this(new WhitePlayer(white), new BlackPlayer(black), command);
    }

    public ChessGame(final Player whitePlayer, final Player blackPlayer, final Command command) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.command = command;
    }

    public static ChessGame newGame() {
        return new ChessGame(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), new Ready());
    }

    public ChessBoard getBoard() {
        ChessBoard chessBoard = ChessBoardFactory.initializeBoard();

        Pieces whitePieces = whitePlayer.getState().pieces();
        Pieces blackPieces = blackPlayer.getState().pieces();

        chessBoard.addPieces(whitePieces);
        chessBoard.addPieces(blackPieces);

        return chessBoard;
    }

    public boolean isEnd() {
        return command.isEnd();
    }

    public boolean isGameOver(){
        return isGameOver;
    }

    public void move(final Queue<String> commands) {
        this.command = this.command.execute(commands.poll());
        if (this.command.isMove()) {
            this.command = command.ready();
            Position sourcePosition = Position.find(commands.poll());
            Position targetPosition = Position.find(commands.poll());
            moveByTurn(sourcePosition, targetPosition);
        }
    }

    public double calculateScore() {
        Player currentPlayer = currentPlayer();
        return score(currentPlayer.getState().pieces());
    }

    public ScoreDto calculateScoreWeb() {
        return new ScoreDto(score(whitePlayer.getState().pieces()), score(blackPlayer.getState().pieces()));
    }

    public String currentPlayerName() {
        return currentPlayer().getName();
    }

    public boolean isStatus() {
        return command.isStatus();
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void moveByTurn(final Position sourcePosition, final Position targetPosition) {
        if (whitePlayer.isFinish()) {
            Source source = Source.valueOf(sourcePosition, blackPlayer.getState());
            Target target = Target.valueOf(source, targetPosition, blackPlayer.getState());
            blackPlayer.move(source, target, whitePlayer.getState());
            whitePlayer.toRunningState(blackPlayer.getState());
            checkPieces(whitePlayer.getState(), target);
            return;
        }
        Source source = Source.valueOf(sourcePosition, whitePlayer.getState());
        Target target = Target.valueOf(source, targetPosition, whitePlayer.getState());
        whitePlayer.move(source, target, blackPlayer.getState());
        blackPlayer.toRunningState(whitePlayer.getState());
        checkPieces(blackPlayer.getState(), target);
    }

    private void checkPieces(final State state, final Target target) {
        if (state.isKing(target.getPosition())) {
            this.command = this.command.end();
            this.isGameOver = true;
        }
        if (state.findPiece(target.getPosition()).isPresent()) {
            state.removePiece(target.getPosition());
        }
    }

    private double score(final Pieces pieces) {
        return pieces.calculateScore();
    }

    private Player currentPlayer() {
        if (blackPlayer.isFinish()) {
            return blackPlayer;
        }
        return whitePlayer;
    }

    public Pieces getAllPieces() {
        List<Piece> whitePieces = whitePlayer.getState().pieces().getPieces();
        List<Piece> blackPieces = blackPlayer.getState().pieces().getPieces();

        List<Piece> allPieces = Stream.concat(whitePieces.stream(), blackPieces.stream())
                .collect(Collectors.toList());
        return new Pieces(allPieces);
    }

    public void changeStatus(final GameStatusRequestDto requestDto) {
        if (requestDto.isGameOver()) {
            this.isGameOver = requestDto.isGameOver();
            this.command = this.command.end();
        }
    }
}
