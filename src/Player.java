import java.util.ArrayList;

public class Player {
	String name;
	private int hp; // 30 is DEFAULT
	int mp;
	private int deck; // Counter to, MAX is 20
	private int mpCap;
	ArrayList<Minion> onBoard = new ArrayList<Minion>();
	ArrayList<Minion> cardHand = new ArrayList<Minion>();
	private int[] minionCount = new int[7];
	
	public Player (int hp, int mp, int deck) {
		this.hp = hp;
		this.mp = mp;	
		this.deck = deck;
	}
	
	public int getmpCap() {
		return mpCap;
	}

	public void setmpCap(int newmpCap) {
		mpCap = (newmpCap > 9) ? newmpCap = 10 : newmpCap;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setDeck(int deck){
		this.deck = deck;
	}
	
	public int getDeck(){
		return this.deck;
	}
	
	public int[] getminionCount(){
		return minionCount;
	}
	
	public void setminionCount (int[] minionCount, int i){
		if (minionCount[i] < 4){
			minionCount[i]++;
		}
	}
}
