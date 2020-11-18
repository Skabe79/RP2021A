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

/**
 *
 * @author skabe
 */
public class MinimaDistancia implements interfaces.ClasificadorSupervisado {
    ArrayList<Patron> patrones;
    ArrayList<Patron> representativos;
    
    public void MinimaDistancia() throws IOException{
        patrones= LeerDatos.tokenizarDataSet();
        entrenar(patrones);
    }
    @Override
    public void entrenar(ArrayList<Patron> patrones) {
        ArrayList<Patron> representativos= new ArrayList<>();
        ArrayList<Integer> cantidades= new ArrayList<>();
        for(int i=0;i<patrones.size();i++){
            boolean existe=false;
            for(int j=0; j<representativos.size();j++){
                if(patrones.get(i).getClaseResultante().equals(representativos.get(j).getClaseResultante())){
                    existe=true;
                    double [] aux= representativos.get(j).getVectorC();
                    int b=cantidades.get(j);
                    b+=1;
                    cantidades.set(j, b);
                    for(int k=0;k<patrones.get(i).getVectorC().length;k++){
                        aux[k]+=patrones.get(i).getVectorC()[k];
                    }
                }
            }
            if(!existe){
            Patron p= new Patron("", patrones.get(i).getClaseResultante(), patrones.get(i).getVectorC());
            representativos.add(p);
            cantidades.add(1);
            }
        }
        for(int i=0; i<representativos.size();i++){
            for(int j=0; j<representativos.get(i).getVectorC().length; j++){
                double prom=representativos.get(i).getVectorC()[j];
                prom= prom/cantidades.get(i);
                representativos.get(i).getVectorC()[j]=prom;
            }
        }
        this.representativos=representativos;
    }

    @Override
    public ArrayList clasificarArrayList(ArrayList<Patron> patrones) {
        for(int i=0;i<patrones.size();i++){
            ArrayList<Double> distancias= new ArrayList<>();
            for(int j=0;j<this.representativos.size();j++){
                distancias.add(this.representativos.get(j).clacularDistancia(patrones.get(i)));   
            }
            int clasificacion=0;
            double menor=distancias.get(0);
            for(int j=0;j<distancias.size();j++){
                if(distancias.get(j)<menor){
                    menor=distancias.get(j);
                    clasificacion=j;
                }
            }
            patrones.get(i).setClaseResultante(this.representativos.get(clasificacion).getClaseResultante());
            
        }
        return patrones;
    }

    @Override
    public Patron clasificarPatron(Patron patron) {
        ArrayList<Double> distancias= new ArrayList<>();
        for(int j=0;j<this.representativos.size();j++){
                distancias.add(this.representativos.get(j).clacularDistancia(patron));   
            }
            int clasificacion=0;
            double menor=distancias.get(0);
            for(int j=0;j<distancias.size();j++){
                if(distancias.get(j)<menor){
                    menor=distancias.get(j);
                    clasificacion=j;
                }
            }
            patron.setClaseResultante(this.representativos.get(clasificacion).getClaseResultante());
            return patron;
    }
}
