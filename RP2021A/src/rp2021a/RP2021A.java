/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rp2021a;
import clasificadores.KNN;
import clasificadores.MinimaDistancia;
import java.util.ArrayList;
import data.LeerDatos;
import data.Patron;
import java.io.IOException;
/**
 *
 * @author skabe
 */
public class RP2021A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MinimaDistancia a= new MinimaDistancia();
        KNN b= new KNN();
        b.KNN();
        a.MinimaDistancia();
        ArrayList<Patron> patrones= new ArrayList<>();
        double[]aux= {5.4,4.1,1.7,2.1};
        patrones.add(new Patron("", "", aux));
        Patron knn=b.clasificarPatron(new Patron("", "", aux));
        aux= new double[]{1.2,0.8,5.1,1.1};
        patrones.add(new Patron("", "", aux));
        patrones=a.clasificarArrayList(patrones);
        System.out.println(patrones.get(0).getClaseResultante());
        System.out.println(knn.getClaseResultante());
        System.out.println(patrones.get(1).getClaseResultante());
        /*ArrayList <Patron> resultante= LeerDatos.tokenizarDataSet();
        double [] vectorA= {2.4,3.3,5.6,7.8};
        double [] vectorB= {2,3.7,5.6,8.8};
        Patron A= new Patron("2.4,3.3,5.6,7.8", "base A", vectorA);
        Patron B= new Patron("2,3.7,5.6,8.8", "base B", vectorB);
        for(int i=0; i<resultante.size(); i++){
            System.out.println("Distancia euclidiana: desde A: "+A.clacularDistancia(resultante.get(i)));
        }
        for(int i=0; i<resultante.size(); i++){
            System.out.println("Distancia euclidiana: desde B: "+B.clacularDistancia(resultante.get(i)));
        }*/
    }
    
}
