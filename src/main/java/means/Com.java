/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package means;

/**
 *
 * @author rash4
 */
sealed interface Com permits Command, SubCommand {
    /**for the list display in console.*/
    default boolean hasParam(){return false;}
}