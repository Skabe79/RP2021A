/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import data.LeerDatos;
import data.Patron;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.util.Pair;

/**
 *
 * @author skabe
 */
public class KNN implements interfaces.ClasificadorSupervisado{
        ArrayList<Patron> patrones;
        ArrayList<Pair<String,Integer>> clases=new ArrayList<>();
        int K=3;
        public void KNN() throws IOException{
            patrones= LeerDatos.tokenizarDataSet();
            entrenar(patrones);
        }

    @Override
    public void entrenar(ArrayList<Patron> patrones) {
        for(int i=0;i<patrones.size();i++){
            boolean existe=false;
            for(int j=0;j<clases.size();j++){
                if(patrones.get(i).getClaseResultante().equals(clases.get(j).getKey())){
                    existe=true;
                }
            }
            if(!existe){
                clases.add(new Pair(patrones.get(i).getClaseResultante(),0));
            }
        }
    }

    @Override
    public ArrayList clasificarArrayList(ArrayList<Patron> patrones) {
        for(int m=0;m<patrones.size();m++){
            for(int i=0; i<clases.size();i++){
                clases.set(i, new Pair(clases.get(i).getKey(),0));
            }
            ArrayList<Pair<String, Double>> tabla_distancias= sacarTabla(patrones.get(m));
            for(int i=0;i<tabla_distancias.size();i++){
                boolean clasificado=false;
                for(int j=0;j<clases.size();j++){
                    if(tabla_distancias.get(i).getKey().equals(clases.get(j).getKey())){
                        clases.set(j, new Pair(clases.get(j).getKey(),clases.get(j).getValue()+1));
                        if(clases.get(j).getValue()==this.K){
                            patrones.get(m).setClaseResultante(clases.get(j).getKey());
                            clasificado=true;
                        }
                    }
                }
                if(clasificado){
                    break;
                }
            }
            patrones.get(m).setClaseResultante("Falta DataSet");
        }
        return patrones;
    }

    @Override
    public Patron clasificarPatron(Patron patron) {
        for(int i=0; i<clases.size();i++){
            clases.set(i, new Pair(clases.get(i).getKey(),0));
        }
        ArrayList<Pair<String, Double>> tabla_distancias= sacarTabla(patron);
        for(int i=0;i<tabla_distancias.size();i++){
            for(int j=0;j<clases.size();j++){
                if(tabla_distancias.get(i).getKey().equals(clases.get(j).getKey())){
                    clases.set(j, new Pair(clases.get(j).getKey(),clases.get(j).getValue()+1));
                    if(clases.get(j).getValue()==this.K){
                        patron.setClaseResultante(clases.get(j).getKey());
                        return patron;
                    }
                }
            }
        }
        patron.setClaseResultante("Falta DataSet");
        return patron;
    }
    
    private ArrayList<Pair<String,Double>> sacarTabla(Patron patron){
        ArrayList<Pair<String,Double>> tabla_dis=new ArrayList<>();
        for(int i=0;i<patrones.size();i++){
            tabla_dis.add(new Pair(patrones.get(i).getClaseResultante(),patrones.get(i).clacularDistancia(patron)));
        }
        tabla_dis=burbuja(tabla_dis);
        return tabla_dis;
    }
    public ArrayList<Pair<String,Double>> burbuja(ArrayList<Pair<String,Double>> X){
        Pair<String,Double> AUX;
        int N, j;
        N=X.size();
        for(int i=0;i<N-1;i++){
            for(j=0;j<N-1;j++){
                if(X.get(j).getValue()>X.get(j+1).getValue()){
			AUX=X.get(j);
                        X.set(j, X.get(j+1));
                        X.set(j+1, AUX);
		}
            }    
        }   
        return X;
    }
}
