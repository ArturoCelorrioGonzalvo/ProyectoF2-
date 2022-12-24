import java.util.*;
import java.io.*;

/**
 * SETNOBUOSTN
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaAristas{
    private Arista aristas[];
    private Random azar = new Random();
    private int numDatos;

    public ListaAristas(int num){
        this.aristas = new Arista[num];
        for(int i=0; i<this.aristas.length; i++){
            aristas[i]=new Arista();
        }
        this.numDatos=0;
    }

    private boolean listaLlena(){
        return this.numDatos==this.aristas.length;
    }

    private boolean listaVacia(){
        return this.numDatos==0;
    }

    public void ordenarLista(){
        Arista apoyo;
        for(int i=1; i<this.numDatos; i++){
            apoyo=this.aristas[i];
            int j=i;
            while(j>0&&!this.aristas[j-1].ordenadoRespA(apoyo)){
                this.aristas[j]=this.aristas[j-1];
                j-=1;
            }
            this.aristas[j]=apoyo;
        }
    }

    public boolean elemOrdenados(int pos1, int pos2){
        return this.aristas[pos1].ordenadoRespA(this.aristas[pos2]);
    }

    private boolean listaOrdenada(){
        boolean ordenada=false;
        int i=0;
        do{
            ordenada=elemOrdenados(i,i+1);
            i++;
        }while(ordenada&&i+1<this.aristas.length);
        return ordenada;
    }

    public void escribirLista(){
        for(int i=0; i<this.numDatos; i++){
            System.out.println(this.aristas[i].toString());
            System.out.println();
        }
    }

    public static ListaAristas leerLista(){
        int n;
        ListaAristas res;
        do n=Teclado.leerEntero("¿Número de elementos?");while(n<=0);
        res=new ListaAristas(n);
        for(int i=0; i<n; i++){
            res.aristas[i]=Arista.leerArista();
            res.numDatos+=1;
        }
        return res;
    }

    public static ListaAristas azarLista(){
        int n,nMax;
        ListaAristas res;
        do n=Teclado.leerEntero("¿Número de elementos?");while(n<=0);
        res=new ListaAristas(n);
        do nMax=Teclado.leerEntero("¿Cuántos quieres que haya? Menos de "+n);while(nMax>=1000||nMax>n);
        for(int i=0; i<nMax; i++){
            res.aristas[i]=Arista.inicioArista();
            res.numDatos+=1;
        }
        return res;
    }

    public void anadeArista(Arista ar){
        int i=0;
        if(!this.listaLlena()){
            while(this.aristas[i].ordenadoRespA(ar)&&i<this.numDatos){
                i++;
            }
            for(int j=this.numDatos; j>i; j--){
                this.aristas[j+1]=this.aristas[j];
            }
            this.aristas[i+1]=ar;
            this.numDatos++;
        }
    }

    private int posicionPrimeraArista(Arista ar){
        int indice=-1;
        boolean existe=true;
        if(!this.listaVacia()){
            indice=0;
            while(existe&&indice<this.numDatos){
                existe=ar.ordenadoRespA(this.aristas[indice]);
            }
        }
        return indice;
    }

    public double existeAristaEntre(int n1, int n2){
        Arista apoyo = new Arista(n1, n2, 0.0);
        double res =-1.0;
        int pos=-1;
        pos=this.posicionPrimeraArista(apoyo);
        if(pos!=-1)res=this.aristas[pos].verPeso();
        return res;
    }

    private void modificarPeso(Arista ar, double pesoN){
        int pos=this.posicionPrimeraArista(ar);
        if(pos!=-1)this.aristas[pos].setPeso(pesoN);
    }

    private int numeroBucles(){
        int res=-1;
        if(!this.listaVacia()){
            res=0;
            for(int i=0; i<this.numDatos; i++){
                if(this.aristas[i].verN1()==this.aristas[i].verN2())res++;
            }
        }
        return res;
    }

    private int gradoNodo(int N1){
        int res=-1, posN=0;
        if(!this.listaVacia()){
            while(this.aristas[posN].verN1()!=N1&&posN<this.numDatos){
                posN++;
            }
            res=0;
            if(posN+1!=this.numDatos){
                while(this.aristas[posN].verN1()==N1){
                    res++;
                }
            }
        }
        return res;
    }
}
