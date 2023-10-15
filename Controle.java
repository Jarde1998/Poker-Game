import java.util.Random;
import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;
public class Controle extends Baralho
{
    Jogador[] jogadores = new Jogador[5];
    int pote;
    Baralho b = new Baralho(getBaralho());
    int valorDaRodada;
    int jogadoresNaRodada;
    
    public Controle(Jogador[] jogadores){
        this.jogadores = jogadores;
        this.pote = pote;
        this.b = b;
        this.jogadoresNaRodada = 5;
    }

    public Jogador[] getJogadores(){
        return jogadores;
    }
    
    public int getPote(){
        return pote;
    }
    
    public Baralho getB(){
        return b;
    }
    
    public int getJogadoresNaRodada(){
        return jogadoresNaRodada;
    }
    
    public void setB(Baralho b){
        this.b = b;
    }
    
    public void setPote(int pote){
        this.pote = pote;
    }
    
    public void setJogadoresNaRodada(int jogadoresNaRodada){
        this.jogadoresNaRodada = jogadoresNaRodada;
    }
    
    public void distruibuirCartas(){
        int x = 0;
        while(x<1000){
            b.embaralha();
            x++;
        }
        
        for(int c = 0; c<jogadores.length; c++){
            if(jogadores[c] != null){
                for(int i = 0; i<jogadores[c].getC().length; i++){
                        Carta salvaCarta = b.daCarta();
                        jogadores[c].getC()[i] = salvaCarta;
                }
            } 
        }
    }
    
    public void apostasIniciais(){
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[i] != null){
                if(jogadores[i].getStatus().equals("Big Blind")){
                    jogadores[i].setFichas(jogadores[i].getFichas() - 10);
                    pote = pote + 10;
                }
                if(jogadores[i].getStatus().equals("Small Blind")){
                    jogadores[i].setFichas(jogadores[i].getFichas() - 5);
                    pote = pote + 5;
                }
            }
        }
    }
    
    public void trocarAsCartas(){
        int primeiroApostador = 7;
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[i] != null){
                if(jogadores[i].getStatus().equals("Dealer")){
                    primeiroApostador = i + 1;
                    if(primeiroApostador == 5){
                        primeiroApostador = 0;
                    }
                    break;
                }
            }
        }
        
        if(jogadores[0] != null){
            System.out.println("Carta *1* - " + jogadores[0].getC()[0].getNome() + "" + jogadores[0].getC()[0].getNaipe() + "\nCarta *2* - " + jogadores[0].getC()[1].getNome() + "" + jogadores[0].getC()[1].getNaipe() + "\nCarta *3* - " + jogadores[0].getC()[2].getNome() + "" + jogadores[0].getC()[2].getNaipe() + "\nCarta *4* - " + jogadores[0].getC()[3].getNome() + "" + jogadores[0].getC()[3].getNaipe() + "\nCarta *5* - " + jogadores[0].getC()[4].getNome() + "" + jogadores[0].getC()[4].getNaipe());
        }
        
        int vezDeQuem = primeiroApostador;
        while(jogadores[vezDeQuem] == null){
            vezDeQuem++;
            if(vezDeQuem == 5){
                vezDeQuem = 0;
            }
        }
        
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[vezDeQuem] != null){
                if(vezDeQuem == 0){
                    String desejaTrocar = "";
                    Carta[] cartasVelhas = new Carta[5];
                    while(!desejaTrocar.equals("sim") && !desejaTrocar.equals("não")){
                        desejaTrocar = Teclado.leString("Você deseja trocar alguma(s) carta(s) de sua mão? Digite *sim* em caso afirmativo ou *não* em caso negativo: "); 
                        if(!desejaTrocar.equals("sim") && !desejaTrocar.equals("não")){
                            System.out.println("Desculpa, não consegui entender. Por favor, digite novamente.");
                        }
                    }
                
                    if(desejaTrocar.equals("sim") ||desejaTrocar.equals("SIM") || desejaTrocar.equals("Sim")){
                        int nTrade = 0;
                        while(nTrade != 1 && nTrade != 2 && nTrade != 3 && nTrade != 4 && nTrade != 5){
                            nTrade = Teclado.leInt("Digite a quantidade de cartas que você deseja trocar de sua mão: ");
                            if(nTrade <= 0 || nTrade>5){
                                System.out.println("Valor inválido! Digite novamente.");
                            }
                        }
                
                        for(int c = 0; c<nTrade; c++){
                            int posicaoCarta = 0;
                            int x = 0;
                            while(posicaoCarta != 1 && posicaoCarta != 2 && posicaoCarta != 3 && posicaoCarta != 4 && posicaoCarta != 5){
                                posicaoCarta = Teclado.leInt("Digite o número da carta que você deseja trocar de sua mão: ");
                                if(posicaoCarta <= 0 || posicaoCarta>5){
                                    System.out.println("Valor inválido! Digite novamente.");
                                }
                            }
                            Carta salvaCarta = b.daCarta();
                            cartasVelhas[x] = jogadores[vezDeQuem].getC()[posicaoCarta - 1];
                            x++;
                            jogadores[vezDeQuem].getC()[posicaoCarta - 1] = salvaCarta;
                        }
                    
                        for(int a = 0; a<cartasVelhas.length; a++){
                            for(int l = 0; l<b.getBaralho().length; l++){
                                if(b.getBaralho()[l]== null){
                                    b.getBaralho()[l] = cartasVelhas[a];
                                    break; 
                                }
                            }
                        }
                    
                        int embaralhador = 0;
                        while(embaralhador<50){
                            b.embaralha();
                            embaralhador++;
                        }
                    
                        System.out.println("Carta *1* - " + jogadores[0].getC()[0].getNome() + "" + jogadores[0].getC()[0].getNaipe() + "\nCarta *2* - " + jogadores[0].getC()[1].getNome() + "" + jogadores[0].getC()[1].getNaipe() + "\nCarta *3* - " + jogadores[0].getC()[2].getNome() + "" + jogadores[0].getC()[2].getNaipe() + "\nCarta *4* - " + jogadores[0].getC()[3].getNome() + "" + jogadores[0].getC()[3].getNaipe() + "\nCarta *5* - " + jogadores[0].getC()[4].getNome() + "" + jogadores[0].getC()[4].getNaipe());
                    }
                }
            
                if(vezDeQuem == 1 || vezDeQuem == 2 || vezDeQuem == 3 || vezDeQuem == 4){
                    int desejaTrocar = 0;
                    Carta[] cartasVelhas = new Carta[5];
                    desejaTrocar = (int)(Math.random() * 2); 
                    if(desejaTrocar == 1){
                        int nTrade = (int) (Math.random() * 5) + 1;
                
                        for(int c = 0; c<nTrade; c++){
                            int posicaoCarta = 0;
                            int x = 0;
                            posicaoCarta = (int) (Math.random() * 5) + 1;
                            Carta salvaCarta = b.daCarta();
                            cartasVelhas[x] = jogadores[vezDeQuem].getC()[posicaoCarta - 1];
                            x++;
                            jogadores[vezDeQuem].getC()[posicaoCarta - 1] = salvaCarta;
                        }
                    
                        for(int a = 0; a<cartasVelhas.length; a++){
                            for(int l = 0; l<b.getBaralho().length; l++){
                                if(b.getBaralho()[l]== null){
                                    b.getBaralho()[l] = cartasVelhas[a];
                                    break; 
                                }
                            }
                        }
                    
                        int embaralhador = 0;
                        while(embaralhador<50){
                            b.embaralha();
                            embaralhador++;
                        }
                    }
                }
            }
            
            if(vezDeQuem >= 0){
                vezDeQuem++;
            }
                
            if(vezDeQuem > 4){
                vezDeQuem = 0;
            }
        }  
    }
    
    public void apostasJogo(){
        int primeiroApostador = 6;
        int bb = 0;
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[i] != null){
                if(jogadores[i].getStatus().equals("Big Blind")){
                    primeiroApostador = i + 1;
                    bb = i;
                    if(primeiroApostador == 5){
                        primeiroApostador = 0;
                    }
                    break;
                }                
            }
        }
        int bigBlind = bb;
        int vezDeQuem = primeiroApostador;
        int valorAPagar = 10;
        int rodadas = 0;
        int desistencias = 0;
        int novaAposta = bb;
        
        while(rodadas<5){
            if(jogadores[vezDeQuem] != null){
                if(jogadores[vezDeQuem].getParticipaDaRodada() == false){
                    rodadas++;
                }
            
                if(jogadores[vezDeQuem].getParticipaDaRodada() == true){
                    if(jogadores[vezDeQuem].getAllIn() == true && jogadores[vezDeQuem].getFichas() == 0){
                        jogadores[vezDeQuem].setParticipaDaRodada(true);
                        rodadas++;
                    }
                
                    else if(vezDeQuem == bigBlind && valorAPagar == 10){
                        if(vezDeQuem == 0){
                           int opcao = 0;
                           while(opcao<1 || opcao>2){
                               opcao = Teclado.leInt("O que você deseja fazer? Digite a opção desejada: \n1)Check \n2)Aumentar a Aposta(10)");
                               if(opcao<1 && opcao>2){
                                   System.out.println("Opção inválida! Digite novamente. \n");
                               }
                           }
                        
                           if(opcao == 1){
                               System.out.println("Aposta deu Check! Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                               rodadas++;
                               opcao = 0;
                           }
                        
                           if(opcao == 2){
                               int aumento = 0;
                               while(aumento<=valorAPagar || aumento>jogadores[vezDeQuem].getFichas()){
                                   aumento = Teclado.leInt("Digite o valor de aposta que você deseja realizar. Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                                   if(aumento<=valorAPagar || aumento>jogadores[vezDeQuem].getFichas()){
                                       System.out.println("Opção inválida! Digite novamente. \n");
                                   }
                               }
                               if(aumento == jogadores[vezDeQuem].getFichas()){
                                   System.out.println("ALL IN! \n");
                                   jogadores[vezDeQuem].setAllIn(true);
                                   jogadores[vezDeQuem].setValorAI(aumento);
                               }
                        
                               valorAPagar = aumento;
                               pote = pote + aumento;
                               jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - valorAPagar);
                               System.out.println("Aposta paga! Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                               System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + aumento + " fichas!");
                               opcao = 0;
                               if(rodadas>0){
                                   rodadas = rodadas - rodadas;  
                               }
                               if(rodadas==0){
                                   rodadas = 1; 
                               }
                           } 
                        }
                    
                        else if(vezDeQuem == 1 || vezDeQuem == 2 || vezDeQuem == 3 || vezDeQuem == 4){
                            int opcao = (int) (Math.random() * 4) + 1;
                            if(opcao == 1 || opcao == 2 || opcao == 3){
                                rodadas++;
                                opcao = 0;
                            }
                        
                            else{
                                int aumento = (int) (Math.random() * (jogadores[vezDeQuem].getFichas() - valorAPagar)) + valorAPagar + 1;
                                valorAPagar = aumento;
                                pote = pote + aumento;
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - aumento);
                                jogadores[vezDeQuem].setAllIn(true);
                                jogadores[vezDeQuem].setValorAI(aumento);
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + aumento + " fichas!");
                                opcao = 0;
                                if(rodadas>0){
                                  rodadas = rodadas - rodadas;  
                                }
                                else if(rodadas==0){
                                   rodadas = 1; 
                                }
                            }
                        }
                    }
                
                    else if(vezDeQuem == 0){
                        if(jogadores[vezDeQuem].getFichas() <= valorAPagar){
                            int opcao = 0;
                            while(opcao<1 || opcao>2){
                                opcao = Teclado.leInt("O que você deseja fazer? Digite a opção desejada: \n1)ALL IN!(" + jogadores[vezDeQuem].getFichas() + ") \n2)Desistir");
                                if(opcao<1 || opcao>2){
                                    System.out.println("Opção inválida! Digite novamente. \n");
                                }
                            }
                        
                            if(opcao == 1){
                                pote = pote + jogadores[vezDeQuem].getFichas();
                                System.out.println("Aposta paga! Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + jogadores[vezDeQuem].getFichas() + " fichas!");
                                jogadores[vezDeQuem].setValorAI(jogadores[vezDeQuem].getFichas());
                                jogadores[vezDeQuem].setAllIn(true);
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - jogadores[vezDeQuem].getFichas());
                                rodadas++;
                                opcao = 0;
                            }
                        
                            if(opcao == 2){
                                jogadores[vezDeQuem].setParticipaDaRodada(false);
                                System.out.print("Voce desistiu da rodada!");
                                rodadas++;
                                desistencias++;
                                opcao = 0;
                            }
                        }
                    
                        else{
                            int opcao = 0;
                            while(true){
                                opcao = Teclado.leInt("O que você deseja fazer? Digite a opção desejada: \n1)Pagar(" + valorAPagar + ") \n2)Aumentar a aposta \n3)Desistir");
                                if(opcao<1 || opcao>3){
                                    System.out.println("Opção inválida! Digite novamente. \n");
                                }
                                if(opcao==1 || opcao==2 || opcao==3){
                                    break;
                                }
                            }
                    
                            if(opcao == 1){
                                pote = pote + valorAPagar;
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - valorAPagar);
                                System.out.println("Aposta paga! Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + valorAPagar + " fichas!");
                                rodadas++;
                                opcao = 0;
                            }
                    
                            if(opcao == 2){
                                int aumento = 0;
                                while(aumento<=valorAPagar || aumento>jogadores[vezDeQuem].getFichas()){
                                    aumento = Teclado.leInt("Digite o valor de aposta que você deseja realizar. Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                                    if(aumento<=valorAPagar || aumento>jogadores[vezDeQuem].getFichas()){
                                        System.out.println("Opção inválida! Digite novamente. \n");
                                    }
                                }
                                if(aumento == jogadores[vezDeQuem].getFichas()){
                                    System.out.println("ALL IN! \n");
                                    jogadores[vezDeQuem].setAllIn(true);
                                    jogadores[vezDeQuem].setValorAI(aumento);
                                }
                        
                                valorAPagar = aumento;
                                pote = pote + aumento;
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - valorAPagar);
                                System.out.println("Aposta paga! Saldo de fichas: " + jogadores[vezDeQuem].getFichas());
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + aumento + " fichas!");
                                novaAposta = vezDeQuem;
                                opcao = 0;
                                if(rodadas>0){
                                  rodadas = rodadas - rodadas;  
                                }
                                if(rodadas==0){
                                   rodadas = 1; 
                                }
                            }
                    
                            if(opcao == 3){
                                jogadores[vezDeQuem].setParticipaDaRodada(false);
                                System.out.print("Voce desistiu da rodada!");
                                rodadas++;
                                desistencias++;
                                opcao = 0;
                            } 
                        }
                    } 
                
                    if(vezDeQuem == 1 || vezDeQuem == 2 || vezDeQuem == 3 || vezDeQuem == 4){
                        if(jogadores[vezDeQuem].getAllIn() == true && jogadores[vezDeQuem].getFichas() == 0){
                            jogadores[vezDeQuem].setParticipaDaRodada(true);
                            rodadas++;
                        }
                    
                        else if(jogadores[vezDeQuem].getFichas() <= valorAPagar){
                            int opcao1 = (int) (Math.random() * 4) + 1;
                            if(opcao1 == 1){
                                pote = pote + jogadores[vezDeQuem].getFichas();
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + jogadores[vezDeQuem].getFichas() + " fichas!");
                                jogadores[vezDeQuem].setValorAI(jogadores[vezDeQuem].getFichas());
                                jogadores[vezDeQuem].setAllIn(true);
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - jogadores[vezDeQuem].getFichas());
                                rodadas++;
                                opcao1 = 0;
                            }
                        
                            if(opcao1 == 2 || opcao1 == 3 || opcao1 == 4){
                                jogadores[vezDeQuem].setParticipaDaRodada(false);
                                System.out.println("O " + jogadores[vezDeQuem].getNome() + " desistiu da rodada.");
                                rodadas++;
                                desistencias++;
                                opcao1 = 0;
                            }
                        }
                    
                        else{
                            int opcao2 = (int) (Math.random() * 5) + 1;
                            if(opcao2 == 1 || opcao2 == 4 || opcao2 == 5){
                                pote = pote + valorAPagar;
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + valorAPagar + " fichas!");
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - valorAPagar);
                                rodadas++;
                                opcao2 = 0;
                            }
                    
                            if(opcao2 == 2){
                                int aumento = (int) (Math.random() * (jogadores[vezDeQuem].getFichas() - valorAPagar)) + valorAPagar + 1;
                                valorAPagar = aumento;
                                pote = pote + aumento;
                                System.out.println(jogadores[vezDeQuem].getNome() + " apostou " + aumento + " fichas!");
                                jogadores[vezDeQuem].setFichas(jogadores[vezDeQuem].getFichas() - aumento);
                                jogadores[vezDeQuem].setAllIn(true);
                                jogadores[vezDeQuem].setValorAI(aumento);
                                novaAposta = vezDeQuem;
                                opcao2 = 0;
                                if(rodadas>0){
                                  rodadas = rodadas - rodadas;  
                                }
                                if(rodadas==0){
                                   rodadas++; 
                                }
                            }
                    
                            if(opcao2 == 3){
                                jogadores[vezDeQuem].setParticipaDaRodada(false);
                                rodadas++;
                                desistencias++;
                                System.out.println(jogadores[vezDeQuem].getNome() + " desistiu da rodada.");
                                opcao2 = 0;
                            } 
                        }
                    }
                    System.out.println("");
                    
                    if(vezDeQuem >= 0){
                        vezDeQuem++;
                    }
                
                    if(vezDeQuem > 4){
                        vezDeQuem = 0;
                    }
                }
            }
            
            if(jogadores[vezDeQuem] == null){
                rodadas++;
                if(vezDeQuem >= 0){
                    vezDeQuem++;
                }
                
                if(vezDeQuem > 4){
                    vezDeQuem = 0;
                }
            }
        }
        valorDaRodada = valorAPagar;
        System.out.println("");
        System.out.println("Valor do Pote: " + pote);
        System.out.println("");
    }
    
    public void rodada(){
        int x = 0;
        int espada, copas, ouro, paus;
        int[] vC = new int[5];
        
        while(x<5){
            if(jogadores[x] == null){
                x++;
            }
            
            else{
                if(jogadores[x].getParticipaDaRodada() == true){
                    espada = 0;
                    copas = 0;
                    ouro = 0;
                    paus = 0;
                    for(int i = 0; i<jogadores[x].getC().length; i++){
                        if(jogadores[x].getC()[i].getNaipe().equals("♠")){
                            espada++;
                        }
                        if(jogadores[x].getC()[i].getNaipe().equals("♥")){
                            copas++;
                        }
                        if(jogadores[x].getC()[i].getNaipe().equals("♦")){
                            ouro++;
                        }
                        if(jogadores[x].getC()[i].getNaipe().equals("♣")){
                            paus++;
                        }
                
                        if(jogadores[x].getC()[i].getNome().equals("2")){
                            vC[i] = 2;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("3")){
                            vC[i] = 3;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("4")){
                            vC[i] = 4;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("5")){
                            vC[i] = 5;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("6")){
                            vC[i] = 6;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("7")){
                            vC[i] = 7;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("8")){
                            vC[i] = 8;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("9")){
                            vC[i] = 9;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("J")){
                            vC[i] = 10;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("Q")){
                            vC[i] = 11;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("K")){
                            vC[i] = 12;
                        }
                        if(jogadores[x].getC()[i].getNome().equals("Ás")){
                            vC[i] = 13;
                        }
                    }
            
                    Arrays.sort(vC);
            
                    if(espada == 5 || copas == 5 || ouro == 5 || paus == 5){
                        if(vC[0] == 9 && vC[1] == 10 && vC[2] == 11 && vC[3] == 12 && vC[4] == 13){
                            jogadores[x].setPontosNaRodada(1000);
                        }
                        else if(vC[0]+1 == vC[1] && vC[1]+1 == vC[2] && vC[2]+1 == vC[3] && vC[3]+1 == vC[4]){
                            jogadores[x].setPontosNaRodada(900 + vC[4]);
                        }
                        else{
                            jogadores[x].setPontosNaRodada(600 + vC[4]);
                        }
                    }
                    else if(vC[0]+1 == vC[1] && vC[1]+1 == vC[2] && vC[2]+1 == vC[3] && vC[3]+1 == vC[4]){
                       jogadores[x].setPontosNaRodada(500 + vC[4]); 
                    }
                    else if(vC[0] == vC[1] && vC[1] == vC[2] && vC[2] == vC[3] || vC[1] == vC[2] && vC[2] == vC[3] && vC[3] == vC[4]){
                        if(vC[0] == vC[1] && vC[1] == vC[2] && vC[2] == vC[3]){
                            jogadores[x].setPontosNaRodada(800 + vC[3]); 
                        }
                        else if(vC[1] == vC[2] && vC[2] == vC[3] && vC[3] == vC[4]){
                            jogadores[x].setPontosNaRodada(800 + vC[4]); 
                        }
                    }
                    else if(vC[0] == vC[1] && vC[1] == vC[2] || vC[1] == vC[2] && vC[2] == vC[3] || vC[2] == vC[3] && vC[3] == vC[4]){
                        if(vC[0] == vC[1] && vC[1] == vC[2] && vC[3] == vC[4] || vC[1] == vC[2] && vC[2] == vC[3] && vC[0] == vC[4] || vC[2] == vC[3] && vC[3] == vC[4] && vC[0] == vC[1]){
                            if(vC[0] == vC[1] && vC[1] == vC[2] && vC[3] == vC[4]){
                                jogadores[x].setPontosNaRodada(700 + vC[0]); 
                            }
                            else if(vC[1] == vC[2] && vC[2] == vC[3] && vC[0] == vC[4]){
                                jogadores[x].setPontosNaRodada(700 + vC[1]); 
                            }
                            else if(vC[2] == vC[3] && vC[3] == vC[4] && vC[0] == vC[1]){
                                jogadores[x].setPontosNaRodada(700 + vC[2]); 
                            }
                        }
                        else{
                            if(vC[0] == vC[1] && vC[1] == vC[2]){
                                jogadores[x].setPontosNaRodada(400 + vC[0]); 
                            }
                            else if(vC[1] == vC[2] && vC[2] == vC[3]){
                                jogadores[x].setPontosNaRodada(400 + vC[1]); 
                            }
                            else if(vC[2] == vC[3] && vC[3] == vC[4]){
                                jogadores[x].setPontosNaRodada(400 + vC[2]); 
                            }
                        }
                    }
                    else if(vC[0] == vC[1] || vC[1] == vC[2] || vC[2] == vC[3] || vC[3] == vC[4]){
                        if(vC[0] == vC[1] && vC[2] == vC[3] || vC[0] == vC[1] && vC[3] == vC[4]){
                           jogadores[x].setPontosNaRodada(300 + vC[3]);  
                        }
                        else if(vC[1] == vC[2] && vC[3] == vC[4]){
                            jogadores[x].setPontosNaRodada(300 + vC[4]); 
                        }
                        else if(vC[0] == vC[1] && vC[2] != vC[3] && vC[3] != vC[4] && vC[2] != vC[4]){
                            jogadores[x].setPontosNaRodada(200 + vC[1]); 
                        }
                        else if(vC[1] == vC[2] && vC[0] != vC[3] && vC[3] != vC[4] && vC[0] != vC[4]){
                            jogadores[x].setPontosNaRodada(200 + vC[2]); 
                        }
                        else if(vC[2] == vC[3] && vC[0] != vC[1] && vC[0] != vC[4] && vC[1] != vC[4]){
                            jogadores[x].setPontosNaRodada(200 + vC[3]); 
                        }
                        else if(vC[3] == vC[4] && vC[0] != vC[1] && vC[0] != vC[2] && vC[1] != vC[2]){
                            jogadores[x].setPontosNaRodada(200 + vC[4]); 
                        }
                    }
            
                    else{
                        jogadores[x].setPontosNaRodada(100 + vC[4]);
                    } 
                }
                System.out.println(jogadores[x].getNome() + " Pontos = " + jogadores[x].getPontosNaRodada());
                x++;
            }
        }
        
        int posicao = -1;
        int pontuacao = 0;
        
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[i] != null){
                if(jogadores[i].getPontosNaRodada() > pontuacao){
                    pontuacao = jogadores[i].getPontosNaRodada();
                    posicao = i;
                }
            }
        }
        
        int casoDeEmpate = 0;
        for(int e = 0; e<jogadores.length; e++){
            if(jogadores[e] != null){
                if(jogadores[e].getPontosNaRodada() == pontuacao){
                    casoDeEmpate++;
                } 
            }
        }
        
        for(int h = 0; h<jogadores.length; h++){
            if(jogadores[h] != null){
                if(jogadores[h].getParticipaDaRodada() == true){
                    System.out.println("Mão de cartas do jogador " + jogadores[h].getNome());
                    for(int k = 0; k<jogadores[h].getC().length; k++){
                        System.out.println(jogadores[h].getC()[k].getNome() + jogadores[h].getC()[k].getNaipe());
                    }
                }
            }
            System.out.println("");
        }
        
        if(jogadores[posicao].getAllIn() == true && jogadores[posicao].getValorAI() < valorDaRodada){
                double doubleValorAI = (double) jogadores[posicao].getValorAI();
                double doubleValorDaRodada = (double) valorDaRodada;
                double proporcao = doubleValorAI/doubleValorDaRodada;
                double doublepote = (double) pote;
                double poteProporcional = doublepote * proporcao;
                int pp = (int) poteProporcional;
                jogadores[posicao].setFichas(jogadores[posicao].getFichas() + pp);
                System.out.print("O jogador " + jogadores[posicao].getNome() + " ganhou a rodada e levou " + pp + " fichas!");
                pote = pote - pote;
        }
        else{
            pote = pote/casoDeEmpate;
            for(int i = 0; i<jogadores.length; i++){
                if(jogadores[i] != null){
                    if(jogadores[i].getPontosNaRodada() == pontuacao){
                        System.out.print("O jogador " + jogadores[posicao].getNome() + " ganhou a rodada e levou " + pote + " fichas!");
                        jogadores[posicao].setFichas(jogadores[posicao].getFichas() + pote);
                    }
                }
            }
            pote = 0;
        }       
        
        for(int i = 0; i<jogadores.length; i++){
            if(jogadores[i] != null){
               jogadores[i].setParticipaDaRodada(true);
               jogadores[i].setPontosNaRodada(0);
               jogadores[i].setAllIn(false);
               jogadores[i].setValorAI(0);
               for(int c = 0; c<jogadores[i].getC().length; c++){
                   jogadores[i].getC()[c] = null;
               } 
            }
        }
        
        for(int i = 0; i<jogadores.length; i++){
                if(jogadores[i] != null){
                    if(jogadores[i].getFichas() < 1){
                        System.out.println("");
                        System.out.println("O JOGADOR " + jogadores[i].getNome() + " ACABOU FICANDO SEM FICHAS E SAINDO DA MESA DE JOGO!");
                        jogadores[i] = null;
                        jogadoresNaRodada = jogadoresNaRodada - 1;
                    }
                }
            }
        
        Baralho c = new Baralho(getBaralho());
        
        b.setBaralho(c.getBaralho());
        
        int embaralhador = 0;
        while(embaralhador<100){
            b.embaralha();
            embaralhador++;
        }
    }
}