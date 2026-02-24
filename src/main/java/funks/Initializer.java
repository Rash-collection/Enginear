/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package funks;

/**
 *
 * @author rash4
 */
@FunctionalInterface
public interface Initializer <T>{
    public T initialize();
    default T setInitializer(Initializer<T> init){
        throw new UnsupportedOperationException();
//        return (T)this;
    }
}