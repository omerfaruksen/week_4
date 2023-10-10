import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Scanner input= new Scanner(System.in);
    ArrayList<Integer> selectLocation = new ArrayList<>();
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");//DAHA SONRA DÜZENLE!!!
        System.out.print("Lütfen bir isim giriniz : ");//DAHA SONRA DÜZENLE!!!
        String playerName=input.nextLine();
        Player gamer=new Player(playerName);
        System.out.println("Adınız :" + gamer.getName() );//DAHA SONRA DÜZENLE!!!
        System.out.println("Lütfen bir karakter seçiniz :");
        gamer.selectChar();
        Location location=null;
        while (true) {
            gamer.printInfo();
            System.out.println("/////////////////////////////////////BÖLGELER/////////////////////////////////////////////");//TEKRAR DÜZENLE BURAYI!!
            System.out.println("1 - <Güvenli Eve Git>");//TEKRAR DÜZENLE BURAYI!!
            System.out.println("2 - <Eşya Dükkanına Git>");//TEKRAR DÜZENLE BURAYI!!
            System.out.println("3 - <Mağara Gir>");//TEKRAR DÜZENLE BURAYI!!
            System.out.println("4 - <Ormana Git>");//TEKRAR DÜZENLE BURAYI!
            System.out.println("5 - <Nehire Git>");//TEKRAR DÜZENLE BURAYI!
            System.out.println("6 - <Madene Git>");
            System.out.println("0 - <Çıkış yap>");//Oyun bitti
            System.out.print("Lütfen gitmek istediğiniz yeri seçiniz : ");
            int selectLoc=input.nextInt();
            if (selectLoc!=1 && selectLoc!=2 && selectLoc!=6 && selectLocation.contains(selectLoc)) {
                System.out.println("Bu Bölümü Geçtiniz!");
                continue;
            } else {
                selectLocation.add(selectLoc);
            }
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

                    location=new Cave(gamer);
                    break;
                case 4:
                    
                    location=new Forest(gamer);
                    break;
                case 5:
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
