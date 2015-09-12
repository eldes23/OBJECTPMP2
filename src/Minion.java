
public class Minion {
	String name;
	int dmg;
	int hp;
	int manaCost;
	private boolean canAttack;

	public Minion(String name, int dmg, int hp, int manaCost, boolean canAttack) {
		this.name = name;
		this.dmg = dmg;
		this.hp = hp;
		this.manaCost = manaCost;
		this.canAttack = false;
	}

	public static void minionAttack(int dmg, int hpPlayer2) {
		hpPlayer2 -= dmg;
	}

	public boolean getcanAttack() {
		return canAttack;
	}

	public void setcanAttack(boolean canAttack){
		this.canAttack = canAttack;
		this.canAttack = true;
	}
}
