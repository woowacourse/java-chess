package chess.factory;

import chess.domain.board.Row;
import chess.domain.chesspiece.*;
import chess.domain.game.Team;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    public static List<Row> createBoard() {
        List<Row> board = new ArrayList<>();
        
        addTeamRow(board, Team.WHITE);
        addBlankRows(board);
        addTeamRow(board, Team.BLACK);
        return board;
    }

    private static void addTeamRow(List<Row> board, Team team) {
        if(team == Team.WHITE){
            addMainPieces(board, team);
            addPawn(board, team);
            return;
        }
        addPawn(board, team);
        addMainPieces(board, team);
    }

    private static void addMainPieces(List<Row> board, Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        chessPieces.add(new Rook(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Queen(team));
        chessPieces.add(new King(team));
        chessPieces.add(new Bishop(team));
        chessPieces.add(new Knight(team));
        chessPieces.add(new Rook(team));
        board.add(new Row(chessPieces));
    }

    private static void addPawn(List<Row> board, Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        for(int i=0;i<8;i++){
            chessPieces.add(new Pawn(team));
        }
        board.add(new Row(chessPieces));
    }

    private static void addBlankRows(List<Row> board) {
        for(int i=0;i<4;i++){
            addBlankRow(board);
        }
    }

    private static void addBlankRow(List<Row> board) {
        List<ChessPiece> chessPieces = new ArrayList<>();

        for(int i=0;i<8;i++){
            chessPieces.add(new Blank(Team.BLANK));
        }
        board.add(new Row(chessPieces));
    }
}
