package Semaforo;
import java.util.ArrayList;
import java.util.Collections;


public class Semaforo {
    public static void main(String[] args) {
        final int tamReg = 1000;
        final int qtdPorProc = 10;
        final int qtdIteracoes = 200;

        
        ArrayList<String> regiaoCritica = new ArrayList<>();
        
        for (int i = 0; i < qtdIteracoes; i++) {
            ArrayList<Processo> lista = criarProcessos();
            
            for(int j = 0; j < lista.size(); j++) {
                for(int k = 0; k < qtdPorProc; k++) {
                    if(regiaoCritica.size() < tamReg && lista.get(j).tipo == 0) {
                        print(i, lista.get(j), regiaoCritica.size(), true);
                        regiaoCritica.add("incluido");
                    } else if (regiaoCritica.size() > 0 && lista.get(j).tipo == 1) {
                        print(i, lista.get(j), 0, true);
                        regiaoCritica.remove(0);
                    } else {
                        print(i, lista.get(j), 0, false);
                    }
                }
            }
            
        }
        
        System.out.println("Tamanho final região critica:" + regiaoCritica.size());
        
    }
    
    public static ArrayList criarProcessos() {
        ArrayList<Processo> lista = new ArrayList<>();
        
        for(int i = 1; i <= 5; i++) {
            lista.add((new Processo(i,0)));
        }
        
        for(int i = 1; i <= 5; i++) {
            lista.add((new Processo(i+5,1)));
        }
        
        Collections.shuffle(lista);
        return lista;
    }
    
    public static void print(int id, Processo processo, int insid, boolean ok) {
        if(ok) {
            System.out.print("Iteração[" + id + "]: <");
            if(processo.tipo == 0) {
                System.out.print("Include");
            } else if (processo.tipo == 1) {
                System.out.print("Exclude");
            }
            System.out.print(" " + processo.idprocesso + "> ");
            if(processo.tipo == 0) {
                System.out.print("incluiu ");
            } else if (processo.tipo == 1) {
                System.out.print("excluiu ");
            }
            System.out.println("em posição [" + insid + "]");
        } else {
            System.out.print("Iteração[" + id + "]: <");
            if(processo.tipo == 0) {
                System.out.print("Include");
            } else if (processo.tipo == 1) {
                System.out.print("Exclude");
            }
            System.out.print(" " + processo.idprocesso + ">");
            System.out.println(" Não foi possível excluir, região crítica vazia. Outro processo tomou seu lugar.");
        }
    }
}