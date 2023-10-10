import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private int originalHealth;
    private String  avard;
    private Scanner input= new Scanner(System.in);


    public Player(String name){
        this.name=name;
        this.inventory=new Inventory();
    }

    public void selectChar(){
        GameChar[] charList={new Samurai(),new Knight(),new Archer()};
        System.out.println("///////////////////////////////////KARAKTERLER///////////////////////////////////////////");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (GameChar gameChar:charList){
            System.out.println("ID : "+gameChar.getId()+
                    "\t Karakter :" +gameChar.getName()+
                    "\t Hasar : "+gameChar.getDamage()+
                    "\t Health : "+gameChar.getHealth()+
                    "\t Para : "+gameChar.getMoney()+"$");
        }
        System.out.println("-----------------------------------------------------------------------------------------");

        System.out.print("Lütfen bir karakter giriniz : ");
        int selectChar=input.nextInt();
        switch (selectChar){
            case 1:
                initGamer(new Samurai());
                break;
            case 2:
                initGamer(new Knight());
                break;
            case 3:
                initGamer(new Archer());
                break;
            default:
                initGamer(new Samurai());
        }
        System.out.println("Karakter : "+this.getCharName()+//TEKRAR DÜZENLE BURAYI !!!
                "\tHasar : " +this.getDamage()+
                "\tSalık : "+this.getDamage()+
                "\tPara  : "+this.getMoney()+"$");
    }

    public void initGamer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println("********************************  "+this.getCharName()+"  **********************************************");
        System.out.println("Silahınız : "+this.getInventory().getWeapon().getName()+
                "\tZırh : "+this.getInventory().getArmor().getName()+
                "\tHasarınız : "+ this.getTotalDamage()+
                "\tBloklama : "+this.getInventory().getArmor().getBlock()+
                "\tSağlık : "+ this.getHealth()+
                "\tPara : "+this.getMoney()+"$");
        System.out.println("*****************************************************************************************");
    }

    public int getTotalDamage(){
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health<0){
            health=0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

}
