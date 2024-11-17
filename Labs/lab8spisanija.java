package Labs;
import java.util.Scanner;
import java.util.Arrays;
/* Дадена е низа од научни списанија, при што за секоје списание се знае местото кое го зафаќа на полица по 
широчина и височина. Дополнително, се знае должината на полиците на кои треба да се сместат списанијата. 
Кога со списанија ќе пополни едно ниво од полиците, се минува на следното ниво полици, при што висината 
на една полица зависи од највисокото списание поставено на полицата. Да се одреди минималната можна 
височина на полиците, откако на неа ќе бидат сместени сите списанија од низата */

class Spisanie{
    int visina;
    int shirina;

    Spisanie(int visina, int shirina){
        this.visina=visina;
        this.shirina=shirina;   
    }
    public String toString(){
        return "Sirina: " + shirina + ", Visina: "+ visina; 
    }
}
public class lab8spisanija {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            Spisanie[] spisanija={
            new Spisanie(7, 5),
            new Spisanie(4, 3),
            new Spisanie(2, 7),
            new Spisanie(8, 5),
            new Spisanie(6, 4)
        };
        System.out.print("Spisanijata so nivnite dimenzii: ");
        for(Spisanie s : spisanija){
            System.out.print(s + ", ");
        }
        System.out.println("Vnesi dolzina na policata: ");
        int dolzina= scanner.nextInt();

        System.out.print("Minimalnata visina na polici: " + minHeight(spisanija,dolzina));
        }
        
            private static int minHeight(Spisanie[] spisanija, int dolzina) {
                //Arrays.sort(spisanija, (a,b)->Integer.compare(b.visina,a.visina));
                for(int i=0;i<spisanija.length - 1;i++){
                    for(int j=0; j<spisanija.length - i - 1;j++){
                        if(spisanija[j].visina<spisanija[j+1].visina){
                            Spisanie temp=spisanija[j];
                            spisanija[j]=spisanija[j+1];
                            spisanija[j+1]=temp;
                        }
                } 
            }
                int momdolzina=0;
                int momvisina=0;
                int vk_visina=0;
                
                for(Spisanie s : spisanija){ //za sekoe s vo nizata spisanija
                    if(momdolzina + s.shirina > dolzina){
                        vk_visina+= momvisina;
                        momdolzina=0;
                        momvisina=0;    
                        
                    }
                    momdolzina+=s.shirina;
                    momvisina = Math.max(momvisina, s.visina);
                }
                vk_visina+=momvisina;
                return vk_visina;
            }
        }
