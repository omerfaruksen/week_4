public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean isWater=false;
    private boolean isFood=false;
    private boolean isFirewood=false;


    public Inventory() {
        this.weapon=new Weapon("Yumruk",-1,0,0);
        this.armor=new Armor(-1,"Paçavra",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }



    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean water) {
        isWater = water;
    }

    public boolean isFood() {
        return isFood;
    }

    public void setFood(boolean food) {
        isFood = food;
    }

    public boolean isFirewood() {
        return isFirewood;
    }

    public void setFirewood(boolean firewood) {
        isFirewood = firewood;
    }
}
