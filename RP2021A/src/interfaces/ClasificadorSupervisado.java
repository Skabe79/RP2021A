/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import data.Patron;
import java.util.ArrayList;

/**
 *
 * @author skabe
 */
public interface ClasificadorSupervisado {
    public abstract void entrenar(ArrayList <Patron> patrones);
    public abstract ArrayList clasificarArrayList(ArrayList <Patron> patrones);
    public abstract Patron clasificarPatron(Patron patron);
}
