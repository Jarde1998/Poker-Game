public class Teste
{
    public static void mains(){
        System.out.println("----------------------------------------------------------------------------------------------------------------\nBem vindo(a), ao jogo de Poker! Aqui você mostrará suas habilidades. Jogando contra robôs que realizam ações aleatórias, você deve conseguir superá-los e se tornar o grande campeão!\n----------------------------------------------------------------------------------------------------------------\n");
    
        String nome = Teclado.leString("Para começar, digite seu nome: ");
        
        Jogador j1 = new Jogador(nome, "");
        Jogador j2 = new Jogador("J2", "");
        Jogador j3 = new Jogador("J3", "");
        Jogador j4 = new Jogador("J4", "");
        Jogador j5 = new Jogador("J5", "");
        
        Jogador[] j0 = {j1, j2, j3, j4, j5};
        
        Controle teste = new Controle(j0);
        int x = 0;
        
        int statusDealer = (int)(Math.random() * 5);
        teste.getJogadores()[statusDealer].setStatus("Dealer");
        
        int statusSmallBlind = statusDealer + 1;
        if(statusSmallBlind == 5){
            statusSmallBlind = 0;
        }
        teste.getJogadores()[statusSmallBlind].setStatus("Small Blind");
        
        int statusBigBlind = statusSmallBlind + 1;
        if(statusBigBlind == 5){
            statusBigBlind = 0;
        }
        teste.getJogadores()[statusBigBlind].setStatus("Big Blind");
        
        int jogadoresNaRodada = 5;
        while(jogadoresNaRodada>1){
            Baralho baralhonovo = new Baralho();
             while(x<10000){
                teste.getB().embaralha();
                x++;
            }
            System.out.println("");
            System.out.println("Próxima Rodada!");
            System.out.println("");
            System.out.println("DEALER DA RODADA: " + teste.getJogadores()[statusDealer].getNome());
            System.out.println("");
            
            teste.distruibuirCartas();
        
            teste.apostasIniciais();
        
            teste.trocarAsCartas();
            
            System.out.println("");
            System.out.println("Apostas da Rodada!");
            System.out.println("");
            
            teste.apostasJogo();
        
            teste.rodada();
            
            teste.setB(baralhonovo);
            
            jogadoresNaRodada = teste.getJogadoresNaRodada();
            
            if(jogadoresNaRodada>2){
                statusDealer++;
                if(statusDealer == 5){
                    statusDealer = 0;
                }
                while(teste.getJogadores()[statusDealer] == null){
                    statusDealer++;
                    if(statusDealer == 5){
                        statusDealer = 0;
                    }
                }
                teste.getJogadores()[statusDealer].setStatus("Dealer");
                
                statusSmallBlind++;
                if(statusSmallBlind == 5){
                    statusSmallBlind = 0;
                }
                while(teste.getJogadores()[statusSmallBlind] == null){
                    statusSmallBlind++;
                    if(statusSmallBlind == 5){
                        statusSmallBlind = 0;
                    }
                }
                teste.getJogadores()[statusSmallBlind].setStatus("Small Blind");
                
                statusBigBlind++;
                if(statusBigBlind == 5){
                    statusBigBlind = 0;
                }
                while(teste.getJogadores()[statusBigBlind] == null){
                    statusBigBlind++;
                    if(statusBigBlind == 5){
                        statusBigBlind = 0;
                    }
                }
                teste.getJogadores()[statusBigBlind].setStatus("Big Blind");
            }
        }
    }
}