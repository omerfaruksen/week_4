import java.util.Random;

public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award,int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle=maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber=this.randomObstacleNumber();
        System.out.println("Şuan Buradasınız : "+this.getName());
        System.out.println("Dikkatli ol Burada "+obsNumber+" tane "+this.getObstacle().getName()+" yaşıyor. ");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase=input.nextLine().toUpperCase();

            if (selectCase.equals("S") && combat(obsNumber)){
                System.out.println(this.getName()+" tüm düşmanları yendiniz. ");
                return true;
            }

        if (this.getPlayer().getHealth()<=0){
            System.out.println("Öldünüz");
            return false;
        }
        return true;
    }
    public boolean combat(int obsNumber){
        for(int i=1 ; i<=obsNumber ; i++){

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
            if (this.getObstacle().getHealth()<this.getPlayer().getHealth()){
                System.out.println("Düşmanı Yendiniz!");
                System.out.println(this.getObstacle().getAward()+" Para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+getObstacle().getAward());
                System.out.println("Güncel Bakiye : "+this.getPlayer().getMoney());
            }else {
                return false;
            }
        }

        return true;
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
