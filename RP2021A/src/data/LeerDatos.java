/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 *
 * @author skabe
 */
public class LeerDatos {
    public static ArrayList tokenizarDataSet() throws FileNotFoundException, IOException{
        String cadena;
        String archivo="C:/Users/skabe/OneDrive - Instituto Politecnico Nacional/UPIIZ/5to semestre/Reconocimiento de patrones/iris.data";
        ArrayList <Patron> patrones= new ArrayList<>();
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            StringTokenizer st= new StringTokenizer(cadena, ",");
            Patron aux= new Patron(4);
            aux.setClase(cadena);
            ArrayList <String> listaseparada=new ArrayList<>();
            double[] vectores= new double[4];
            while (st.hasMoreTokens()) {
                listaseparada.add(st.nextToken());
            }
            for(int i=0; i<listaseparada.size()-1;i++){
                vectores[i]= Double.parseDouble(listaseparada.get(i));
            }
            aux.setVectorC(vectores);
            aux.setClaseResultante(listaseparada.get(listaseparada.size()-1));
            patrones.add(aux);
        }
        b.close();
        return patrones;
    }
    
}

