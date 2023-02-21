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
        if(this.turn.isComplete()){
            this.playerView.putToken(this.turn.getActivePlayer());
		} else {
			this.playerView.moveToken(this.turn.getActivePlayer());
		}
        this.turn.setNextPlayer();    
    } 
    
    public void writeWinner() {
		this.playerView.writeWinner(this.turn.getActivePlayer());
	}
}
