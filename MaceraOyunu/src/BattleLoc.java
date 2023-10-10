import javax.sound.midi.spi.SoundbankReader;
import java.sql.SQLOutput;
import java.util.Random;

public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private String locName;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award,int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle=maxObstacle;
        this.locName=name;
    }

    @Override
    public boolean onLocation() {
        int obsNumber=this.randomObstacleNumber();
        System.out.println("Şuan Buradasınız : "+this.getName());
        System.out.println("Dikkatli ol Burada "+obsNumber+" tane "+this.getObstacle().getName()+" yaşıyor. ");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase=input.nextLine().toUpperCase();


            if (selectCase.equals("S") && combat(obsNumber)){
                System.out.println(this.getName()+" bölgesindeki tüm düşmanları öldürdünüz! ");
                if (this.locName == "Mağara") {
                    this.getPlayer().getInventory().setFood(true);
                    System.out.println("YEMEK KAZANDINIZ!");
                }
                if (this.locName == "Orman") {
                    this.getPlayer().getInventory().setFirewood(true);
                    System.out.println("ODUN KAZANDINIZ!");
                }
                if (this.locName == "Nehir") {
                    this.getPlayer().getInventory().setWater(true);
                    System.out.println("SU KAZANDINIZ!");
                }

                return true;
            }

        if (this.getPlayer().getHealth()<=0){
            System.out.println("GAME OVER!");
            return false;
        }
        return true;
    }
    public boolean combat(int obsNumber){
        for(int i=1 ; i<=obsNumber ; i++){
            System.out.println("///////////////////////////////////SAVAŞ ALANI////////////////////////////////////////////");

            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            gamerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0){

                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat=input.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
                    if (whoStart()<50){
                        System.out.println("Siz vurdunuz: ");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth()>0){
                            System.out.println("Canavar size vurdu : ");
                            int obstacleDamage=this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage<0){
                                obstacleDamage=0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                            afterHit();
                        }
                    }else{
                        if (this.getObstacle().getHealth()>0){
                            System.out.println("Canavar size vurdu : ");
                            int obstacleDamage=this.getObstacle().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage<0){
                                obstacleDamage=0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth()-obstacleDamage);
                            afterHit();
                        System.out.println("Siz vurdunuz: ");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();

                        }
                    }

                }else{
                    return false;
                }
            }
            if (getObstacle().getName() == "Yılan") {
                anything();
            }else if (this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println("Düşmanı Yendiniz!");
                System.out.println(this.getObstacle().getAward()+" $ kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+getObstacle().getAward());
                System.out.println("Güncel Bakiye : "+this.getPlayer().getMoney()+"$");

            }else {
                return false;
            }
        }

        return true;
    }
    public void  anything() {
        Random random = new Random();
        int rand = random.nextInt(1, 100);
        if (rand <= 15) {
            Random randWeapon = new Random();
            int randomWeapon = random.nextInt(1, 100);
            if (randomWeapon <= 20) {
                System.out.println("Tüfek Kazandınız! ");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
            } else if (randomWeapon <= 50 && randomWeapon > 20) {
                System.out.println("Kılıç Kazandınız! ");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));

            } else {
                System.out.println("Tabanca Kazandınız! ");
                getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
            }


        } else if (rand > 15 && rand < 30) {
            Random randArmor = new Random();
            int randomArmor = random.nextInt(1, 100);
            if (randomArmor <= 20) {
                System.out.println("Ağır Zırh Kazandınız! ");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
            } else if (randomArmor > 20 && randomArmor <= 50) {
                System.out.println("Orta Zırh Kazandınız! ");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));

            } else {
                System.out.println("Hafif Zırh Kazandınız! ");
                getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));

            }
        } else if (rand > 30 && rand <= 55) {
            Random randMoney = new Random();
            int randomMoney = random.nextInt(1, 100);
            if (randomMoney <= 20) {
                System.out.println("10$ Para Kazandınız! ");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            } else if (randomMoney > 20 && randomMoney <= 50) {
                System.out.println("5$ Para Kazandınız! ");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                System.out.println("1$ Para Kazandınız! ");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
            System.out.println("Güncel Paranız :" + this.getPlayer().getMoney());
        } else
            System.out.println("Hiçbir şey kazanamadınız. ");


    }

    public void afterHit(){
        System.out.println("Canınız : "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+" canı : "+this.getObstacle().getHealth());
        System.out.println("-------------");
    }
    public void gamerStats(){
        System.out.println("Oyuncu değerleri : ");
        System.out.println("------------------------------");
        System.out.println("Sağlık : "+this.getPlayer().getHealth());
        System.out.println("Silah : "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : "+this.getPlayer().getTotalDamage());
        System.out.println("Zırh : "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : "+this.getPlayer().getMoney());

    }
    public void obstacleStats(int i){
        System.out.println(i+"."+"Canavar : "+this.getObstacle().getName());
        System.out.println("--------------------------------");
        System.out.println("Sağlık : "+this.getObstacle().getHealth());
        System.out.println("Hasar : "+this.getObstacle().getDamage());
        System.out.println("Ödül : "+this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r=new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public int whoStart(){
        Random start=new Random();
        return start.nextInt(100);
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }


}
