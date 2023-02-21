package es.usantatecla.tictactoe_v2.main.views;

import es.usantatecla.tictactoe_v2.main.models.Turn;

public class TurnView {

    private Turn turn;
    private PlayerView playerView;

    public TurnView(Turn turn){
        this.turn = turn;
        this.playerView = new PlayerView();
    }

    public void play(){
        this.playerView.play(this.turn.getActivePlayer());
        if(!this.turn.isTicTacToe()){
            this.turn.changeTurn();
        }      
    } 
    
    public void writeWinner() {
		this.playerView.writeWinner(this.turn.getActivePlayer());
	}
}
