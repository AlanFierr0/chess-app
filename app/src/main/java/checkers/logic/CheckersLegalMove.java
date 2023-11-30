package checkers.logic;

import common.logic.LegalMove;
import common.logic.PieceMover;
import common.logic.PossibleMovements;
import common.logic.WinCondition;
import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.moves.Move;
import common.results.MoveResult;

import java.util.ArrayList;
import java.util.List;

public class CheckersLegalMove implements LegalMove {
    private final PieceMover pieceMover = new PieceMover();
    private final PossibleMovements possibleMovements = new PossibleMovements();

    @Override
    public MoveResult<Board, Boolean> movePiece(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition) {
        return loopUntilCantEat(piece, toSquare, board, initial, moves, winCondition);
    }

    private MoveResult<Board, Boolean> loopUntilCantEat(Piece piece, Coordinate toSquare, Board board, Coordinate initial, List<Move> moves, WinCondition winCondition) {
        if (canEatRule(piece, board, toSquare)) {
            return new MoveResult<>(board, true, "always eat Rule unfollowed");
        }
        MoveResult<Board, Boolean> move = pieceMover.check(board, initial, toSquare, moves, piece, board.getSquare(toSquare).getPiece());
        if (move.errorResult())
            return move;
        else {
            if (canContinueToEat(piece,move.successfulResult(),toSquare) != toSquare)
                return loopUntilCantEat(piece, canContinueToEat(piece,move.successfulResult(),toSquare), move.successfulResult(), move.successfulResult().getSquareOfPiece(piece).successfulResult().get(), moves, winCondition);

            return winCondition.checkWin(board, piece, move, toSquare);
        }
    }

    private Coordinate canContinueToEat(Piece piece, Board board, Coordinate toSquare) {
        List<Coordinate> possibleMoves = new ArrayList<>(possibleMovements.getPossibleMovements(board, piece, board.getSquareOfPiece(piece).successfulResult().get()));
        for (Coordinate possibleMove : possibleMoves) {
            if (checkIfPossible(piece, board, possibleMove)) {
                return possibleMove;
            }
        }
        return toSquare;
    }

    private boolean canEatRule(Piece piece, Board board, Coordinate toSquare) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (Piece piece1 : board.getCurrentPieces()) {
            if (piece1.getColor() == piece.getColor())
                possibleMoves.addAll(possibleMovements.getPossibleMovements(board, piece1, board.getSquareOfPiece(piece1).successfulResult().get()));
        }
        for (Coordinate possibleMove : possibleMoves) {
            if (isNotEatingWhenPossible(piece, board, toSquare, possibleMove)) {
                return true;
            }
        }
        return false;
    }
    private boolean isNotEatingWhenPossible(Piece piece, Board board, Coordinate toSquare, Coordinate possibleMove) {
        boolean canEat = checkIfPossible(piece, board, possibleMove);
        boolean position = possibleMove.column() == toSquare.column() && possibleMove.row() == toSquare.row();
        return canEat && !position;
    }

    private boolean checkIfPossible(Piece piece, Board board, Coordinate possibleMove) {
        for (Move move : piece.getEatMovements()) {
            if (move.checkMove(board.getSquareOfPiece(piece).successfulResult().get(), possibleMove, board, piece.getColor()).outputResult() && board.checkForPieceInSquare(possibleMove)) {
                return true;
            }
        }
        return false;
    }
}
