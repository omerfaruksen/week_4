import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Scanner input= new Scanner(System.in);
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName=input.nextLine();
        Player gamer=new Player(playerName);
        System.out.println("Adınız :" + gamer.getName() );
        System.out.println("Lütfen bir karakter seçiniz :");
        gamer.selectChar();
        Location location=null;

        while (true) {
            gamer.printInfo();
            if (gamer.getInventory().isWater() && gamer.getInventory().isFirewood() && gamer.getInventory().isFood()){
                System.out.println("********************************************************************************************");
                System.out.println("****************************TEBRİKLER ADADAN KURTULDUNUZ !**********************************");
                System.out.println("********************************************************************************************");
                break;
            }
            System.out.println("/////////////////////////////////////BÖLGELER/////////////////////////////////////////////");//TEKRAR DÜZENLE BURAYI!!
            System.out.println("1 - <Güvenli Eve Git>");
            System.out.println("2 - <Eşya Dükkanına Git>");
            System.out.println("3 - <Mağara Gir>");
            System.out.println("4 - <Ormana Git>");
            System.out.println("5 - <Nehire Git>");
            System.out.println("6 - <Madene Git>");
            System.out.println("0 - <Çıkış yap>");//Oyun bitti
            System.out.print("Lütfen gitmek istediğiniz yeri seçiniz : ");
            int selectLoc=input.nextInt();

            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    location=new SafeHouse(gamer);
                    break;
                case 2:
                    location=new ToolStore(gamer);
                    break;
                case 3:
                    if (gamer.getInventory().isFood()){
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("Bu bölüm ödülünü daha önce kazandınız ! Güvenli eve gidiyorsunuz !");
                        location=new SafeHouse(gamer);
                        break;
                    }
                    location=new Cave(gamer);
                    break;
                case 4:
                    if (gamer.getInventory().isFirewood()){
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("Bu bölüm ödülünü daha önce kazandınız ! Güvenli eve gidiyorsunuz !");
                        location=new SafeHouse(gamer);
                        break;
                    }
                    location=new Forest(gamer);
                    break;
                case 5:
                    if (gamer.getInventory().isWater()){
                        System.out.println("--------------------------------------------------------------------------------------------");
                        System.out.println("Bu bölüm ödülünü daha önce kazandınız ! Güvenli eve gidiyorsunuz !");
                        location=new SafeHouse(gamer);
                        break;
                    }
                    location=new Riwer(gamer);
                    break;
                case 6:
                    location=new Maden(gamer);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir ifade giriniz.");

            }
            if (location==null){
                System.out.println("Oyun Bitti");//Düzenle tekrar
                break;
            }
            if (!location.onLocation()){
                //System.out.println("Öldünüz.");
                break;
            }
        }
    }

}
