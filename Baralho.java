import java.util.Random;
public class Baralho
{
    private Carta[] baralho = new Carta[52];

    public Baralho()
    {
        for(int i = 0; i<baralho.length; i++){
            if(i==0){
                baralho[i] = new Carta("Ás", " ♥");
            }
            
            if(i>=1 && i<10){
                baralho[i] = new Carta((i+1)+"", " ♥");
            }
            
            if(i==10){
                baralho[i] = new Carta("J", " ♥");
            }
            
            if(i==11){
                baralho[i] = new Carta("Q", " ♥");
            }
            
            if(i==12){
                baralho[i] = new Carta("K", " ♥");
            }
            
            if(i==13){
                baralho[i] = new Carta("Ás", " ♣");
            }
            
            if(i>=14 && i<22){
                baralho[i] = new Carta((i+1)-13+"", " ♣");
            }
            
            if(i==23){
                baralho[i] = new Carta("J", " ♣");
            }
            
            if(i==24){
                baralho[i] = new Carta("Q", " ♣");
            }
            
            if(i==25){
                baralho[i] = new Carta("K", " ♣");
            }
            
            if(i==26){
                baralho[i] = new Carta("Ás", " ♠");
            }
            
            if(i>=27 && i<35){
                baralho[i] = new Carta((i+1)-26+"", " ♠");
            }
            
            if(i==36){
                baralho[i] = new Carta("J", " ♠");
            }
            
            if(i==37){
                baralho[i] = new Carta("Q", " ♠");
            }
            
            if(i==38){
                baralho[i] = new Carta("K", " ♠");
            }
            
            if(i==39){
                baralho[i] = new Carta("Ás", " ♦");
            }
            
            if(i>=40 && i<49){
                baralho[i] = new Carta((i+1)-39+"", " ♦");
            }
            
            if(i==49){
                baralho[i] = new Carta("J", " ♦");
            }
            
            if(i==50){
                baralho[i] = new Carta("Q", " ♦");
            }
            
            if(i==51){
                baralho[i] = new Carta("K", " ♦");
            }
        }
    }
    
    public Baralho(Carta[] baralho){
        this.baralho = baralho;
    }
    
    public void setBaralho(Carta[] baralho){
        this.baralho = baralho;
    }
    
    public Carta[] getBaralho(){
        return baralho;
    }
    
    public void embaralha(){
        int numRandom1 = (int)(Math.random() * baralho.length);
        int numRandom2 = (int)(Math.random() * baralho.length);
        
        Carta salva1 = baralho[numRandom1];
        
        baralho[numRandom1] = baralho[numRandom2];
        baralho[numRandom2] = salva1;
    }
    
    public Carta daCarta(){
        int x = 0;
        while(baralho[x] == null){
            x++;
        }
        String nomeCarta = baralho[x].getNome();
        String naipeCarta = baralho[x].getNaipe();
        Carta c1 = new Carta(nomeCarta,naipeCarta);
        baralho[x] = (null);
        return c1;
    }
    
    public void imprimeBaralho()
    {   
        for(int i = 0; i<baralho.length; i++){
            if(baralho[i]!= null){
                System.out.println(baralho[i].getNome() + "" + baralho[i].getNaipe());
            }
        }
        System.out.println("");
    }
}