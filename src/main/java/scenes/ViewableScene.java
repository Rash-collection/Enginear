/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package scenes;

import engine.View;
import funks.Disposer;
import funks.Initializer;
import funks.Locker;
import java.awt.Rectangle;
import means.Launcher;

/**
 *
 * @author rash4
 * @param <V>       Scene type
 */
public class ViewableScene <V extends ViewableScene<V>> extends engine.Scene<V>
        implements Viewer, Initializer<V>, Disposer<V>, Locker{
    public ViewableScene(String name){
        super(name);
        this.lock = new engine.Lock();
        this.view = new View(new Rectangle(Launcher.size()));
    }
    public ViewableScene(String name, Rectangle bounds){
        super(name);
        this.lock = new engine.Lock();
        if(bounds != null) this.view = new View(bounds);
        else this.view = new View(new Rectangle(Launcher.size()));
    }
    
    @Override public V setInitializer(Initializer<V> init){
        if(init != null) this.initialize = init;
        return this.self();
    }
    @Override public V setDisposer(Disposer dispose){
        this.dispose = dispose;
        return this.self();
    }
    
    public boolean isActive()  {return this.active;}
    public boolean toggleStat(){return this.active = !this.active;}
    
    public V setActive(boolean activate){
        this.active = activate;
        return this.self();
    }
    
    @Override public engine.Lock lock(){return this.lock;}
    @Override public V initialize(){return this.initialize.initialize();}
    @Override public View getView(){return this.view;}
    @Override public void dispose(){this.dispose.dispose();}
    
    protected boolean active = true;
    private final engine.Lock lock;
    private final View view;
    private Initializer<V> initialize;
    private Disposer dispose;
}