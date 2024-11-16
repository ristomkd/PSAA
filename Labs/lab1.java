package Labs;

/* Да се креира хиерархија на класи за водење на евиденција за лаборатории во еден факултет. За секоја лабораторија е познато името на лабораторијата, името на институтот на кој припаѓа и бројот на работни места во неа. Почетната вредност на лабораторијата е 120000 денари, а се зголемува за 5% за секое работно место. Лабораториите може да бидат софтверски или хардверски.

За софтверска лабораторија дополнително се чува бројот на софтверите кои се дел од лабораторијата (максимум 15), низа со насловите на секој софтвер и низа од индикатори која кажува дали софтверот е лиценциран или слободен. Вкупната вредност на софтверската лабораторија се одредува со тоа што почетната вредност лабораторијата се зголемува 5000 денари по работно место ако софтверот е лиценциран и 5 денари по работно место ако софтверот е слободен.

За хардверска лабораторија дополнително се чува информација за типот на хардверска лабораторија (високострујна или нискострујна), бројот на парчиња хардвер кои се дел од лабораторијата (максимум 30) и низа со цените на секое парче хардвер. Вредноста на лабораторијата со алати се добива со тоа што на почетната вредност и се додава 100000 ако е високострујна или 2000 ако е нискострујна а потоа на сумата се додава цената на секое од парчињата хардвер што се дел од лабораторијата.

Во секоја од класите да се дефинира конструктор со параметри, методи за печатење на сите информации за објект од соодветната класа и метода за пресметка на вредноста на објект од соодветната класа. */

class Labaratorii{
    protected String imeLab;
    protected String imeInstitut;
    protected int brRab;
    protected double vred = 120000;
    
    public Labaratorii(){
        this.imeLab=null;
        this.imeInstitut=null;
        this.brRab=0;
        this.vred=0;
    }
    
    public Labaratorii(String imeL, String ImeI, int br ){
        this.imeLab=imeL;
        this.imeInstitut=ImeI;
        this.brRab=br;
    }
    
    public Labaratorii(Labaratorii l){
        this.brRab=l.brRab;
        this.imeInstitut=l.imeInstitut;
        this.imeLab=l.imeLab;
    }
    
    private double vrednost(){
        vred = vred + (0.05 * brRab);
        return vred;
    }
    
    public void pechati(){
        System.out.println("Ime na labaratorijata e: " + imeLab);
        System.out.println("Ime na institut e: " + imeInstitut);
        System.out.println("Broj vraboteni: " + brRab);
        System.out.println("Vrednost: " + vrednost());
    }
}

class Software extends Labaratorii{
    protected int brSoft; //max 15
    protected String[] niza;
    protected boolean indikator;
    protected double vrednostS;
    
    public Software(){
        this.brSoft=0;
        this.niza=null;
        this.indikator=false;
        this.vrednostS=0;
    }
    
    public Software (int brsoft, String[] niza, boolean indikator, Labaratorii L){
        super(L);
        this.brSoft=brsoft;
        this.niza=niza;
        this.indikator=indikator;
    }
    
    private double vrednostS(){
        vrednostS= super.vred;
        if(indikator == true) { vrednostS=vrednostS + 5; }
        else { vrednostS=vrednostS + 5000;}
        return vrednostS;
    }
    
    public void printS(){
        super.pechati();
        int i=0;
        System.out.println("Broj software: " + brSoft);
        System.out.println("Vrednost" + vrednostS());
        if(indikator == true) { 
        System.out.println("Softverot e sloboden");
        } else {
        System.out.println("Softverot e licenciran ");
        }
        for(i=0;i<niza.length-1;i++){
            System.out.print(niza[i]+",");
        }
        System.out.print(niza[i]);
        }
    }

class Hardware extends Labaratorii{
    protected boolean tip; //true - visokostrujna, false - niskostrujna
    protected int parchinja; //max 30
    protected int[] niza;
    protected double vrednostH;
    
    private double vrednostH(){
        vrednostH = super.vred;
        if(tip == true ) { vrednostH += 100000; }
        else { vrednostH += 2000; }
        for(int i =0; i< niza.length;i++){
            vrednostH += niza[i];
        }
        return vrednostH;
    }


}

public class lab1 {
    public static void main(String[] args) {
        Labaratorii Li = new Labaratorii ("Lab1", "FEIT", 5);
        Li.pechati();
        String[] niza={"ime1", "ime2", "ime3"};
        Software s= new Software(6,niza,false,Li);
        s.printS();
    }
}
