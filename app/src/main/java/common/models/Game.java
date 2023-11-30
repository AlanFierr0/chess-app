package common.models;

import common.logic.LegalMove;
import common.logic.WinCondition;
import common.results.MoveResult;


import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Stack<Board> boardStack = new Stack<>();
    private TurnHandler turnHandler;
    private final WinCondition winCondition;
    private final LegalMove legalMove;


    public Game(Player player1, Player player2, Board board, SideColor startingPlayer, WinCondition winCondition, LegalMove legalMove) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardStack.push(board);
        this.turnHandler = new TurnHandler(startingPlayer);
        this.winCondition = winCondition;
        this.legalMove = legalMove;
        setGame();
    }

    public void setGame() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
            player1.setColor(SideColor.White);
            player2.setColor(SideColor.Black);
        } else {
            player1.setColor(SideColor.Black);
            player2.setColor(SideColor.White);
        }
    }

    public MoveResult<Board,Boolean,SideColor> movePiece(Coordinate initial, Coordinate toSquare, Player currentPlayer) {
        Piece piece = boardStack.peek().getSquare(initial).getPiece();
        if (isNotNull(piece)) {
            return new MoveResult<>(boardStack.peek(), true,currentPlayer.getColor(), "Piece not found");
        }
        if(piece.getColor().equals(currentPlayer.getColor())) {
            MoveResult<Board,Boolean,SideColor> res = piece.movePiece(initial,toSquare, boardStack.peek(),winCondition, legalMove);
            if (res.errorResult()) {
                return new MoveResult<>(boardStack.peek(), true, currentPlayer.getColor(), res.message());
            }
            turnHandler = turnHandler.setTurn(res.nextTurn());
            boardStack.push(res.successfulResult());
            return new MoveResult<>(boardStack.peek(), false,res.nextTurn(), res.message());
        }
        else{
            return new MoveResult<>(boardStack.peek(), true, currentPlayer.getColor(),"Piece not same color as player");
        }
    }

    private static boolean isNotNull(Piece piece) {
        return Objects.equals(piece.getName(), "null");
    }


    public Board getBoard() {
        return boardStack.peek();
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public Player getCurrentPlayer() {
        if (turnHandler.turn() == player1.getColor()) {
            return player1;
        }
        return player2;
    }
}
