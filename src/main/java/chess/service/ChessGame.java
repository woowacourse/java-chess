package chess.service;

import chess.domain.board.*;
import chess.domain.piece.Piece;
import chess.domain.result.Judge;
import chess.domain.result.Score;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private Board board;
    private GameStatus gameStatus;

    public ChessGame() {
        gameStatus = GameStatus.NOT_START;
    }

    public void initializeBoard() {
        if (gameStatus != GameStatus.NOT_START) {
            throw new IllegalStateException("게임이 이미 시작되었습니다.");
        }
        board = BoardFactory.createInitial();
        gameStatus = GameStatus.STARTED;
    }

    public void move(final String sourceSquareInput, final String targetSquareInput) {
        if (gameStatus != GameStatus.STARTED) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }
        Square sourceSquare = Square.from(sourceSquareInput);
        Square targetSquare = Square.from(targetSquareInput);
        board.makeMove(sourceSquare, targetSquare);
        board.turnSwitch();
        Side sideKingDied = Judge.findSideKingDied(board);
        if (sideKingDied != Side.from(Color.NOTHING)) {
            gameStatus = GameStatus.STOP;
        }
    }

    public Map<Side, Score> stauts() {
        return Judge.calculateScore(board);
    }

    public void forceClose() {
        gameStatus = GameStatus.STOP;
    }

    public List<List<Piece>> findChessBoard() {
        /*if (gameStatus != GameStatus.STARTED) {
            throw new IllegalStateException("게임이 시작되지 않았습니다.");
        }*/
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> piecesByRank = new ArrayList<>();
            for (File file : files) {
                piecesByRank.add(board.findPiece(file, rank));
            }
            pieces.add(piecesByRank);
        }
        return pieces;
    }

    public boolean isContinue() {
        return gameStatus != GameStatus.STOP;
    }
}
