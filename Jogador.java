public class Jogador
{
    private String nome, status;
    private int fichas;
    private Carta[] c = new Carta[5];
    private boolean participaDaRodada;
    private int pontosNaRodada;
    private boolean allIn;
    private int valorAI;

    public Jogador(String nome, String status){
        this.nome = nome;
        this.fichas = 200;
        this.status = status;
        this.c = c;
        this.participaDaRodada = true;
        this.pontosNaRodada = 0;
        this.allIn = false;
        this.valorAI = 0;
    }
        
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setFichas(int fichas){
        this.fichas = fichas;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public void setC(Carta[] c){
        this.c = c;
    }
    
    public void setParticipaDaRodada(boolean participaDaRodada){
        this.participaDaRodada = participaDaRodada;
    }
    
    public void setPontosNaRodada(int pontosNaRodada){
        this.pontosNaRodada = pontosNaRodada;
    }
    
    public void setAllIn(boolean allIn){
        this.allIn = allIn;
    }
    
    public void setValorAI(int valorAI){
        this.valorAI = valorAI;
    }
    
    public String getNome(){
        return nome;
    }
    
    public int getFichas(){
        return fichas;
    }
    
    public String getStatus(){
        return status;
    }
    
    public Carta[] getC(){
        return c;
    }
    
    public boolean getParticipaDaRodada(){
        return participaDaRodada;
    }
    
    public int getPontosNaRodada(){
        return pontosNaRodada;
    }
    
    public boolean getAllIn(){
        return allIn;
    }
    
    public int getValorAI(){
        return valorAI;
    }
}